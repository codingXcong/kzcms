package cn.zgc.cms.service;

import java.util.List;

import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.Pager;

public interface IGroupService {
	
	/**
	 * 获取所有的组信息
	 * @return
	 */
	List<Group> listGroup();
	
	/**
	 * 分页展示组信息
	 * @return
	 */
	Pager<Group> findGroup();
	
	/**
	 * 添加用户组
	 * @param group
	 */
	void add(Group group);
	
	/**
	 * 根据主键获取用户组
	 * @param id
	 * @return
	 */
	Group load(int id);
	
	/**
	 * 更新用户组
	 * @param gp
	 */
	void update(Group gp);
	
	/**
	 * 根据用户组id删除用户组
	 * @param id
	 */
	void delete(int id);
	
	/**
	 * 删除某个用户组下所有的用户
	 * @param gid
	 */
	void deleteGroupUsers(int gid);

}
