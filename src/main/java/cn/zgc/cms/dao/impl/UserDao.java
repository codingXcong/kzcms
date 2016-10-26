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

	public void add(User user, Integer[] rids, Integer[] gids) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public void update(User user, Integer[] rids, Integer[] gids) {
		// TODO Auto-generated method stub
		
	}

	public void updateStatus(int id) {
		// TODO Auto-generated method stub
		
	}

	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
