package cn.zgc.cms.dao;

import java.util.List;

import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.GroupChannel;
import cn.zgc.cms.model.Pager;

public interface IGroupDao extends IBasicHibernateDao<Group>{
	/**
	 * 列出所有的用户组，前端用于增加和修改的准备数据
	 * @return
	 */
	List<Group> listGroup();
	/**
	 * 用户组信息（带分页）
	 * @return
	 */
	Pager<Group> findGroup();
	/**
	 * 通过用户组id删除改组下的所有用户
	 * @param gid
	 */
	void deleteGroupUsers(int gid);
	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	void addGroupChannel(Group group,Channel channel);
	/**
	 * 加载GroupChannel对象
	 */
	GroupChannel loadGroupChannel(int gid,int cid);
	/**
	 * 清空组所管理的栏目
	 * @param gid
	 */
	void clearGroupChannel(int gid);
	/**
	 * 删除用户组下某个栏目
	 * @param gid 将删除的用户组id
	 * @param cid 将删除的栏目id
	 */
	void deleteGroupChannel(int gid,int cid);
	/**
	 * 获取某个用户组下所有栏目的id
	 * @param gid
	 * @return
	 */
	List<Integer> listGroupChannelIds(int gid);
	/**
	 * 生成用户组的栏目树
	 * @param gid
	 * @return
	 */
	List<ChannelTree> generateGroupChannelTree(int gid);
	/**
	 * 生成用户的栏目树
	 * @param uid
	 * @return
	 */
	List<ChannelTree> generateUserChannelTree(int uid);

}
