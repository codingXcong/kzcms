package cn.zgc.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zgc.cms.service.IRoleService;
import cn.zgc.cms.service.IUserService;

@Controller
@RequestMapping("admin/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	
	
}
