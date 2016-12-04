package cn.zgc.cms.service;

import java.util.List;

import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.GroupChannel;
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
	
	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	public void addGroupChannel(int gid,int cid);
	/**
	 * 加载GroupChannel对象
	 * @param gid
	 * @param cid
	 * @return
	 */
	public GroupChannel loadGroupChannel(int gid,int cid);
	/**
	 * 清空组所管理的栏目
	 * @param gid
	 */
	public void clearGroupChannel(int gid);
	/**
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
	/**
	 * 获取某个组的所有管理栏目的id
	 * @param gid
	 * @return
	 */
	public List<Integer> listGroupChannelIds(int gid);
	/**
	 * 获取某个组的栏目树
	 * @param gid
	 * @return
	 */
	public List<ChannelTree> generateGroupChannelTree(int gid);
	/**
	 * 获取某个用户的栏目树
	 * @param uid
	 * @return
	 */
	public List<ChannelTree> generateUserChannelTree(int uid);

}
