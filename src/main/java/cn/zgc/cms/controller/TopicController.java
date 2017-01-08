package cn.zgc.cms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.zgc.cms.dto.TopicDto;
import cn.zgc.cms.model.Topic;
import cn.zgc.cms.model.User;
import cn.zgc.cms.service.IChannelService;
import cn.zgc.cms.service.ITopicService;

/** 
 * @author gczhang  
 * 
 */
@Controller
@RequestMapping("/admin/topic")
public class TopicController {
	@Resource
	private ITopicService topicService;
	@Resource
	private IChannelService channelService;
	
	@RequestMapping("/audits")
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,1);
		return "topic/list";
	}
	
	private void initList(String con, Integer cid, Model model, HttpSession session, int status) {
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) {
			model.addAttribute("datas",topicService.find(cid, con, status));
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", topicService.find(loginUser.getId(),cid, con, status));
		}
		model.addAttribute("cs",channelService.listPublishChannel());
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		Topic t = new Topic();
		t.setPublishDate(new Date());
		TopicDto td = new TopicDto(t); 
		model.addAttribute("topicDto",td);
		return "topic/add";
	}
}
