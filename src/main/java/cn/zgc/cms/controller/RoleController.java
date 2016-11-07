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
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Role;
import cn.zgc.cms.service.IRoleService;
import cn.zgc.cms.service.IUserService;

@Controller
@RequestMapping("admin/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/list")
	public String list(Model model){
		Pager<Role> roles = roleService.findRole();
		model.addAttribute("datas", roles);
		return "role/list";
	}
/*	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new Role()); 
		//TODO types
		return "role/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Group group){
		roleService.add(group);
		return "redirect:/admin/role/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		model.addAttribute(roleService.load(id));
		return "role/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Role role) {
		Role r = roleService.load(id);
		r.setName(role.getName());
		r.setRoleType(role.getRoleType());
		roleService.update(r);
		return "redirect:/admin/role/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		roleService.delete(id);
		return "redirect:/admin/role/list";
	}
	
	@RequestMapping("{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(groupService.load(id));
		model.addAttribute("us", userService.listGroupUsers(id));
		return "role/show";
	}
	
	@RequestMapping("/clearUsers/{rid}")
	public String clearRoleUsers(@PathVariable int rid){
		roleService.deleteRoleUsers(rid);
		return "redirect:/admin/role/list";
	}
*/
}
