package cn.zgc.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IChannelDao;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.ChannelType;
import cn.zgc.cms.model.CmsException;
import cn.zgc.cms.service.IChannelService;
@Service
public class ChannelServiceImpl implements IChannelService {
	@Autowired
	private IChannelDao channelDao; 
	
	@Override
	public void add(Channel channel, Integer pid) {
		if(pid!=null&&pid>0){
			Channel parent = channelDao.getByPk(pid);
			if(parent==null) throw new CmsException("待添加栏目的父栏目为空");
			channel.setParent(parent);
		}
		int orders = channelDao.getMaxOrderByParent(pid);
		channel.setOrders(orders);
		channelDao.add(channel);
	}

	@Override
	public void update(Channel channel) {
		channelDao.update(channel);
	}

	@Override
	public void delete(int id) {
		//1、需要判断是否存在子栏目
		List<Channel> cs = channelDao.listByParent(id);
		if(cs!=null&&cs.size()>0) throw new CmsException("要删除的栏目还有子栏目，无法删除");
		//2、需要判断是否存在文章
		//TODO
		//3、需要删除和组的关联关系
		//channelDao.deleteChannelGroups(id);
		
		channelDao.delete(id);
	}

	@Override
	public void clearTopic(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Channel load(int id) {
		return channelDao.getByPk(id);
	}

	@Override
	public List<Channel> listByParent(Integer pid) {
		return channelDao.listByParent(pid);
	}

	@Override
	public List<ChannelTree> generateTree() {
		List<ChannelTree> channels = channelDao.generateTree();
		return channels;
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}

	@Override
	public List<Channel> listPublishChannel() {
		return channelDao.listPublishChannel();
	}

	@Override
	public List<Channel> listTopNavChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSort(Integer[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Channel> listAllIndexChannel(ChannelType ct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Channel loadFirstChannelByNav(int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Channel> listUseChannelByParent(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Channel> listChannelByType(ChannelType ct) {
		// TODO Auto-generated method stub
		return null;
	}

}
