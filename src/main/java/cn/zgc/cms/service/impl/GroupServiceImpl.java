package cn.zgc.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IGroupDao;
import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.service.IGroupService;
@Service("groupService")
public class GroupServiceImpl implements IGroupService{
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IUserDao userDao;
	
	
}
