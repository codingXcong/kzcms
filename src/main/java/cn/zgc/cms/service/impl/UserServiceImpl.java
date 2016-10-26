package cn.zgc.cms.service.impl;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.CmsException;
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


	public void add(User user, Integer[] rids, Integer[] gids) {
		User tu = userDao.loadByUsername(user.getUsername());
		if(tu!=null)throw new CmsException("添加的用户对象已经存在，不能添加");
		user.setCreateDate(new Date());
		userDao.add(user);
		//添加角色对象
		for(Integer rid:rids) {
			//this.addUserRole(user, rid);
		}
		//添加用户组对象
		for(Integer gid:gids) {
			//addUserGroup(user, gid);
		}
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

}
