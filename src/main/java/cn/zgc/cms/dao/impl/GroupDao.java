package cn.zgc.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.zgc.cms.dao.IGroupDao;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.GroupChannel;
import cn.zgc.cms.model.Pager;
@Repository
public class GroupDao extends BasicHibernateDaoImpl<Group> implements IGroupDao {

	public List<Group> listGroup() {
		return this.list("from Group");
	}

	public Pager<Group> findGroup() {
		return this.find("from Group");
	}

	public void deleteGroupUsers(int gid) {
		this.updateObject("delete UserGroup ug where ug.group.id=?", gid);
	}

	@Override
	public void addGroupChannel(Group group, Channel channel) {
		//添加之前，需要前判断是否已经存在
		GroupChannel gc = this.loadGroupChannel(group.getId(), channel.getId());
		if(gc!=null) return;
		gc = new GroupChannel();
		gc.setGroup(group);
		gc.setChannel(channel);
		this.getSession().save(gc);
	}

	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		String hql = "from GroupChannel where group.id=? and channel.id=?";
		return (GroupChannel) this.queryObject(hql, new Object[]{gid,cid});
	}

	@Override
	public void clearGroupChannel(int gid) {
		this.updateObject("delete GroupChannel gc where gc.group.id=?",gid);
	}

	@Override
	public void deleteGroupChannel(int gid, int cid) {
		this.updateObject("delete GroupChannel gc where gc.group.id=? and gc.channel.id=?", new Object[]{gid,cid});
	}

	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		String hql = "select gc.channel.id from GroupChannel gc where gc.group.id=?";
		List<Integer> idList = this.getSession().createQuery(hql).setInteger(0, gid).list();
		return idList;
	}

	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		String sql = "select c.id as id,c.name as name,c.pid as pid from " +
				"t_group_channel gc left join t_channel c on(gc.c_id=c.id) " +
				"where gc.g_id=?";
		List<ChannelTree> cts = this.listBySQL(sql, gid, ChannelTree.class, false);
		ChannelDao.initTreeNode(cts);
		return cts;
	}

	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		String sql = "select distinct c.id as id,c.name as name,c.pid as pid from " +
				"t_group_channel gc left join t_channel c on(gc.c_id=c.id) left join t_user_group ug on(ug.g_id=gc.g_id)" +
				"where ug.u_id=?";
		List<ChannelTree> cts = this.listBySQL(sql,uid,ChannelTree.class, false);
		return cts;
	}
	
}
