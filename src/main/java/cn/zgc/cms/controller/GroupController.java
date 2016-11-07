package cn.zgc.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zgc.cms.model.Group;
import cn.zgc.cms.service.IGroupService;
import cn.zgc.cms.service.IUserService;

@Controller
@RequestMapping("admin/group")
public class GroupController {
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("datas", groupService.findGroup());
		return "group/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new Group()); 
		return "group/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Group group,BindingResult br){
		if(br.hasErrors()){
			return "group/add";
		}
		groupService.add(group);
		return "redirect:/admin/group/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		model.addAttribute(groupService.load(id));
		return "group/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated Group group,BindingResult br) {
		if(br.hasErrors()){
			return "group/update";
		}
		Group gp = groupService.load(id);
		gp.setName(group.getName());
		gp.setDescr(group.getDescr());
		groupService.update(gp);
		return "redirect:/admin/group/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		groupService.delete(id);
		return "redirect:/admin/group/list";
	}
	
	@RequestMapping("{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(groupService.load(id));
		model.addAttribute("us", userService.listGroupUsers(id));
		return "group/show";
	}
	
	@RequestMapping("/clearUsers/{id}")
	public String clearGroupUsers(@PathVariable int id){
		groupService.deleteGroupUsers(id);
		return "redirect:/admin/group/list";
	}
}
