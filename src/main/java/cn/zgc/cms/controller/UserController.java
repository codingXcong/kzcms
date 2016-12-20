package cn.zgc.cms.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.User;
import cn.zgc.cms.service.IGroupService;
import cn.zgc.cms.service.IRoleService;
import cn.zgc.cms.service.IUserService;
import cn.zgc.cms.web.vo.UserDto;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/users")
	public String list(Model model) {
		Pager<User> users =  userService.findUser();
		model.addAttribute("datas",users);
		return "user/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("userDto", new UserDto());
		initAddUser(model); 
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,@Valid UserDto userDto,BindingResult br) {
		if(br.hasErrors()){
			initAddUser(model);
			return "user/add";
		}
		userService.add(userDto.getUser(), userDto.getRoleIds(), userDto.getGroupIds());
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		User u = userService.load(id);
		model.addAttribute("userDto",new UserDto(u,
				userService.listUserRoleIds(id),
				userService.listUserGroupIds(id)));//user,user
		initAddUser(model);
		return "user/update";
	}
	
	private void initAddUser(Model model) {
		model.addAttribute("roles",roleService.listRole());
		model.addAttribute("groups", groupService.listGroup());
	}

	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid UserDto userDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			System.out.println(br.hasErrors());
			initAddUser(model);
			return "user/update";
		}
		User ou = userService.load(id);
		ou.setNickname(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		ou.setEmail(userDto.getEmail());
		ou.setStatus(userDto.getStatus());
		userService.update(ou, userDto.getRoleIds(), userDto.getGroupIds());
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/updateStatus/{id}",method=RequestMethod.GET)
	public String updateStatus(@PathVariable int id) {
		userService.updateStatus(id);
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(userService.load(id));
		model.addAttribute("gs",userService.listUserGroups(id));
		model.addAttribute("rs",userService.listUserRoles(id));
		return "user/show";
	}
	
	@RequestMapping("/showSelf")
	public String showSelf(HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		model.addAttribute(user);
		model.addAttribute("gs",userService.listUserGroups(user.getId()));
		model.addAttribute("rs",userService.listUserRoles(user.getId()));
		return "user/show";
	}
	
	@RequestMapping(value="/updateSelf",method=RequestMethod.GET)
	public String updateSelf(Model model,HttpSession session){
		User u = (User) session.getAttribute("user");
		if(u!=null)
			model.addAttribute(new UserDto(u));
		return "user/updateSelf";
	}
	
	@RequestMapping(value="/updateSelf",method=RequestMethod.POST)
	public String updateSelf(@Valid UserDto userDto,BindingResult br,Model model,HttpSession session){
		if(br.hasErrors()){
			return "user/updateSelf";
		}
		User ou = userService.load(userDto.getId());
		ou.setNickname(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		ou.setEmail(userDto.getEmail());
		userService.update(ou);
		session.setAttribute("user", ou);
		return "redirect:/admin/user/showSelf";
	}
	
	@RequestMapping(value="/updatePwd",method=RequestMethod.GET)
	public String updatePwd(Model model,HttpSession session){
		User u = (User) session.getAttribute("user");
		if(u!=null)
			model.addAttribute(new UserDto(u));
		return "user/updatePwd";
	}
	
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public String updatePwd(int id,String oldPwd,String password){
		userService.updatePwd(id, oldPwd, password);
		return "redirect:/admin/user/showSelf";
	}
	
}
