package cn.zgc.cms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.User;
import cn.zgc.cms.service.IUserService;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	
	
	public Pager<User> findUser() {
		return userDao.findUser();
	}

}
