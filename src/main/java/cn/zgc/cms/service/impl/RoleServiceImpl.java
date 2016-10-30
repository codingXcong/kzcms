package cn.zgc.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IRoleDao;
import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.Role;
import cn.zgc.cms.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;
	
	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
