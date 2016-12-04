package cn.zgc.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IChannelDao;
import cn.zgc.cms.dao.IGroupDao;
import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.CmsException;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.GroupChannel;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.service.IGroupService;
@Service("groupService")
public class GroupServiceImpl implements IGroupService{
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IChannelDao channelDao;
		
	public List<Group> listGroup() {
		return groupDao.listGroup();
	}

	public Pager<Group> findGroup() {
		return groupDao.findGroup();
	}

	public void add(Group group) {
		groupDao.add(group);
	}

	public Group load(int id) {
		return groupDao.getByPk(id);
	}

	public void update(Group gp) {
		groupDao.update(gp);
	}

	public void delete(int id) {
		groupDao.delete(id);
	}

	public void deleteGroupUsers(int gid) {
		groupDao.deleteGroupUsers(gid);
	}

	@Override
	public void addGroupChannel(int gid, int cid) {
		Group group = groupDao.getByPk(gid);
		Channel channel = channelDao.getByPk(cid);
		if(group==null||channel==null) 
			throw new CmsException("关联对象不存在，请确认存在对应的用户组或栏目");
		groupDao.addGroupChannel(group, channel);
	}

	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		return groupDao.loadGroupChannel(gid, cid);
	}

	@Override
	public void clearGroupChannel(int gid) {
		groupDao.clearGroupChannel(gid);
	}

	@Override
	public void deleteGroupChannel(int gid, int cid) {
		groupDao.deleteGroupChannel(gid, cid);
	}

	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		return groupDao.listGroupChannelIds(gid);
	}

	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		return groupDao.generateGroupChannelTree(gid);
	}

	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		return groupDao.generateUserChannelTree(uid);
	}
	
	
}
