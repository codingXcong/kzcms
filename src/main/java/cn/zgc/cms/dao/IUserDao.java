package cn.zgc.cms.dao;

import java.util.List;

import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Role;
import cn.zgc.cms.model.User;


public interface IUserDao extends IBasicHibernateDao<User>{
	
	/**
	 * 用户的更新，如果rids中的角色在用户中已经存在，就不做操作
	 * 如果rids中的角色在用户中不存在就要添加，如果用户中的角色不存在于rids中需要进行删除
	 * 对于group而已同样要做这个操作
	 * @param user
	 * @param rids
	 * @param gids
	 */
	public void update(User user,Integer[] rids,Integer[] gids);
	
	/**
	 * 列表用户
	 */
	public Pager<User> findUser();

	
	/**
	 * 根据用户名获取用户对象
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username);
	
	/**
	 * 添加用户角色对象
	 * @param user
	 * @param role
	 */
	public void addUserRole(User user,Role role);
	/**
	 * 添加用户组对象
	 * @param user
	 * @param group
	 */
	public void addUserGroup(User user,Group group);
	
	/**
	 * 删除用户角色对象
	 * @param uid
	 * @param rid
	 */
	public void deleteUserRole(int uid,int rid);
	/**
	 * 删除用户组对象
	 * @param uid
	 * @param gid
	 */
	public void deleteUserGroup(int uid,int gid);
	/**
	 * 删除某个用户的用户组对象
	 * @param uid 用户ID
	 */
	public void deleteUserGroups(int uid);
	/**
	 * 删除某个用户的用户角色对象
	 * @param uid 用户id
	 */
	public void deleteUserRoles(int uid);
	/**
	 * 通过用户名查找用户
	 * @param username
	 */
	public User findByUserName(String username);
	/**
	 * 列出用户的所有角色信息
	 * @param uid
	 * @return
	 */
	public List<Role> listUserRoles(int uid);
	
	public List<Group> listUserGroups(int userId);
	
	
}
