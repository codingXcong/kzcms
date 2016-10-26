package cn.zgc.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.User;
import cn.zgc.cms.service.IUserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/users")
	public String list(Model model) {
		Pager<User> users =  userService.findUser();
		model.addAttribute("datas",users);
		return "user/list";
	}
}
