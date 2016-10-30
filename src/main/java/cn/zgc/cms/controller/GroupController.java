package cn.zgc.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zgc.cms.service.IGroupService;
import cn.zgc.cms.service.IUserService;

@Controller
@RequestMapping("admin/group")
public class GroupController {
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	
}
