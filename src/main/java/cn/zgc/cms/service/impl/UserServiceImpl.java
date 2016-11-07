package cn.zgc.cms.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IGroupDao;
import cn.zgc.cms.dao.IRoleDao;
import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.dao.impl.RoleDao;
import cn.zgc.cms.model.CmsException;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Role;
import cn.zgc.cms.model.User;
import cn.zgc.cms.service.IUserService;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IGroupDao groupDao;
	
	
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
			this.addUserRole(user, rid);
		}
		//添加用户组对象
		for(Integer gid:gids) {
			this.addUserGroup(user, gid);
		}
	}


	private void addUserGroup(User user, Integer gid) {
		Group group = groupDao.getByPk(gid);
		if(group == null){
			throw new CmsException("添加的用户组不存在");
		}
		userDao.addUserGroup(user, group);
	}


	private void addUserRole(User user, Integer rid) {
		Role role = roleDao.getByPk(rid);
		if(role == null)
			throw new CmsException("添加的角色不存在");
		userDao.addUserRole(user, role);
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


	public User load(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer[] listUserRoleIds(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer[] listUserGroupIds(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Role> listUserRoles(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Group> listUserGroups(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<User> listGroupUsers(int gid) {
		String hql = "select ug.user from UserGroup ug where ug.group.id=?";
		return userDao.list(hql, gid);
	}

}
