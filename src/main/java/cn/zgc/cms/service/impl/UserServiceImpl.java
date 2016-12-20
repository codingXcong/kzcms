package cn.zgc.cms.service.impl;
import java.security.NoSuchAlgorithmException;
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
import cn.zgc.cms.util.SecurityUtil;
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
		String md5Passwd = "";
		try {
			md5Passwd = SecurityUtil.md5(user.getUsername(), user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setPassword(md5Passwd);
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
		//TODO 需要进行用户是否有文章的判断

		//1、删除用户管理的角色对象
		userDao.deleteUserGroups(id);
		//2、删除用户管理的组对象
		userDao.deleteUserRoles(id);
		userDao.delete(id);
	}


	public void update(User user, Integer[] rids, Integer[] gids) {
		// TODO Auto-generated method stub
		
	}


	public void updateStatus(int id) {
		User user = userDao.getByPk(id);
		if(user==null) throw new CmsException("用户不存在");
		if(user.getStatus()==0) user.setStatus(1);
		else user.setStatus(0);
		userDao.update(user);
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


	public List<Role> listUserRoles(int uid) {
		List<Role> roles = userDao.listUserRoles(uid);
		return roles;
	}


	public List<Group> listUserGroups(int id) {
		List<Group> groups = userDao.listUserGroups(id);
		return groups;
	}


	public List<User> listGroupUsers(int gid) {
		String hql = "select ug.user from UserGroup ug where ug.group.id=?";
		return userDao.list(hql, gid);
	}


	@Override
	public User login(String username, String password) {
		User user = userDao.findByUserName(username);
		if(user==null) throw new CmsException("用户名或者密码不正确");
		try {
			if(!SecurityUtil.md5(username, password).equals(user.getPassword()))
				throw new CmsException("用户名或密码错误");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if(user.getStatus()==0) throw new CmsException("用户已经停用，请与管理员联系");
		return user;
	}


	@Override
	public void update(User ou) {
		userDao.update(ou);
	}


	@Override
	public void updatePwd(int uid, String oldPwd, String newPwd) {
		User user = userDao.getByPk(uid);
		try {
			if(!SecurityUtil.md5(user.getUsername(),oldPwd).equals(user.getPassword())) {
				throw new CmsException("原始密码输入不正确");
			}
			user.setPassword(SecurityUtil.md5(user.getUsername(), newPwd));
			userDao.update(user);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new CmsException("更新密码失败:"+e.getMessage());
		}
		
	}

}
