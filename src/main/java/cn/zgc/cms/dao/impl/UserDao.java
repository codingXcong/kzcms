package cn.zgc.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Role;
import cn.zgc.cms.model.User;
import cn.zgc.cms.model.UserGroup;
import cn.zgc.cms.model.UserRole;

@Repository("userDao")
public class UserDao extends BasicHibernateDaoImpl<User> implements IUserDao {


	public Pager<User> findUser() {
		return this.find("from User");
	}

	public void update(User user, Integer[] rids, Integer[] gids) {
		// TODO Auto-generated method stub
		
	}


	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addUserRole(User user, Role role) {
		//添加之前需要先判断一下是否已经存在
		UserRole ur = this.loadUserRole(user.getId(), role.getId());
		if(ur!=null){
			return ;
		}
		ur = new UserRole();
		ur.setUser(user);
		ur.setRole(role);
		this.getSession().save(ur);
	}
	
	public UserRole loadUserRole(int userId, int roleId) {
		String hql = "select ur from UserRole ur left join fetch ur.user u left join fetch ur.role r where u.id=? and r.id=?";
		UserRole userRole = (UserRole)this.getSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, roleId).uniqueResult();
		return userRole;
	}
	
	public void addUserGroup(User user, Group group) {
		//添加之前需要先判断一下是否已经存在
		UserGroup ug = this.loadUserGroup(user.getId(), group.getId());
		if(ug!=null) return ;
		ug = new UserGroup();
		ug.setUser(user);
		ug.setGroup(group);
	}

	private UserGroup loadUserGroup(int userId, int groupId) {
		String hql = "select ug from UserGroup ug left join fetch ug.user u left join fetch ug.group g where u.id=? and g.id=?";
		UserGroup userGroup = (UserGroup) this.getSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, groupId).uniqueResult();
		return userGroup;
	}

	@Override
	public void deleteUserRole(int uid, int rid) {
		String hql = "delete form UserRole ur where ur.user.id = ? and ur.role.id = ?";
		this.updateObject(hql,new Object[]{uid,rid});
	}

	@Override
	public void deleteUserGroup(int uid, int gid) {
		String hql = "delete from UserGroup ug where ug.user.id = ? and ug.group.id = ?";
		this.updateObject(hql, new Object[]{uid,gid});
	}

	@Override
	public void deleteUserGroups(int uid) {
		String hql = "delete from UserGroup ug where ug.user.id = ?";
		this.updateObject(hql, uid);
	}

	@Override
	public void deleteUserRoles(int uid) {
		String hql = "delete from UserRole ur where ur.user.id = ?";
		this.updateObject(hql, uid);
	}

	@Override
	public User findByUserName(String username) {
		String hql = "from User u where u.username = ?";
		return (User) this.queryObject(hql, username);
	}

	@Override
	public List<Role> listUserRoles(int uid) {
		String hql = "select ur.role from UserRole ur where ur.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0,uid).list();
	}

	@Override
	public List<Group> listUserGroups(int userId) {
		String hql = "select ug.group from UserGroup ug where ug.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}
	
}
