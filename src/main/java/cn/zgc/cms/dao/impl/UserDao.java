package cn.zgc.cms.dao.impl;

import org.springframework.stereotype.Repository;

import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.User;

@Repository("userDao")
public class UserDao extends BasicHibernateDaoImpl<User> implements IUserDao {


	public Pager<User> findUser() {
		return this.find("from User");
	}
	
}
