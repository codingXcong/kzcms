package cn.zgc.cms.dao;

import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.User;


public interface IUserDao extends IBasicHibernateDao<User>{

	
	public Pager<User> findUser();
}
