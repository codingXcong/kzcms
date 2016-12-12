package cn.zgc.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zgc.cms.dto.TreeDto;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.ChannelType;
import cn.zgc.cms.service.IChannelService;
import cn.zgc.cms.util.EnumUtil;

@Controller
@RequestMapping("/admin/channel")
public class ChannelController {
	@Autowired
	private IChannelService channelService;
	
	@RequestMapping("/list")
	public String list(){
		return "channel/list";
	}
	
	@RequestMapping("/treeAll")
	@ResponseBody
	public  List<ChannelTree> tree() {
		return channelService.generateTree();
	}
	
	@RequestMapping(value="/treeAs",method=RequestMethod.POST)
	public @ResponseBody List<TreeDto> tree(@Param Integer pid) {
		List<TreeDto> tds = new ArrayList<TreeDto>();
		if(pid==null||pid<=0) {
			tds.add(new TreeDto(0,"网站根栏目",1));
			return tds;
		}
		List<ChannelTree> cts = channelService.generateTreeByParent(pid);
		for(ChannelTree ct:cts) {
			tds.add(new TreeDto(ct.getId(),ct.getName(),1));
		}
		return tds;
	}
	
	@RequestMapping("/channels/{pid}")
	public String listChild(@PathVariable Integer pid,@Param Integer refresh,Model model) {
		Channel pc = null;
		if(pid==null||pid<=0) {
			pc = new Channel();
			pc.setName(Channel.ROOT_NAME);
			pc.setId(Channel.ROOT_ID);
		} else
			pc = channelService.load(pid);
		model.addAttribute("pc", pc);
		model.addAttribute("channels",channelService.listByParent(pid));
		return "channel/list_child";
	}
	
	@RequestMapping(value="/add/{pid}",method=RequestMethod.GET)
	public String add(@PathVariable Integer pid,Model model){
		initAdd(pid,model);
		// 页面上spring标签需要
		//<sf:form method="post" modelAttribute="channel" id="addForm">
		model.addAttribute(new Channel());
		return "channel/add";
	}
	
	@RequestMapping(value="/add/{pid}",method=RequestMethod.POST)
	public String add(@PathVariable Integer pid,Channel channel,Model model,BindingResult br){
		if(br.hasErrors()){
			initAdd(pid,model);
			return "channel/add";
		}
		channelService.add(channel, pid);
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	private void initAdd(Integer pid, Model model) {
		if(pid==null) pid=0;
		Channel pc = null;
		if(pid==0){
			pc = new Channel();
			pc.setId(Channel.ROOT_ID);
			pc.setName(Channel.ROOT_NAME);
		}else {
			pc = channelService.load(pid);
		}
		model.addAttribute("pc", pc);
		model.addAttribute("types", EnumUtil.enumProp2Map(ChannelType.class, "name"));
	}
	
	@RequestMapping("/delete/{pid}/{id}")
	public String delete(Model model,@PathVariable Integer pid,@PathVariable Integer id){
		channelService.delete(id);
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model){
		Channel c = channelService.load(id);
		model.addAttribute("channel", c);
		Channel pc = null;
		if(c.getParent()==null) {
			pc = new Channel();
			pc.setId(Channel.ROOT_ID);
			pc.setName(Channel.ROOT_NAME);
		} else {
			pc = c.getParent();
		}
		model.addAttribute("pc",pc);
		model.addAttribute("types", EnumUtil.enumProp2Map(ChannelType.class, "name"));
		return "channel/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,Channel channel,Model model,BindingResult br){
		if(br.hasErrors()){
			return "channel/update";
		}
		Channel tc = channelService.load(id);
		int pid = 0;
		if(tc.getParent()!=null) pid = tc.getParent().getId();
		tc.setCustomLink(channel.getCustomLink());
		tc.setCustomLinkUrl(channel.getCustomLinkUrl());
		tc.setIsIndex(channel.getIsIndex());
		tc.setIsTopNav(channel.getIsTopNav());
		tc.setName(channel.getName());
		tc.setNavOrder(channel.getNavOrder());
		tc.setOrders(channel.getOrders());
		tc.setRecommend(channel.getRecommend());
		tc.setStatus(channel.getStatus());
		tc.setType(channel.getType());
		channelService.update(tc);
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	
	
}
