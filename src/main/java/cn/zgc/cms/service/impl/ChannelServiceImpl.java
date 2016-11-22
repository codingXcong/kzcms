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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

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
		return channelDao.generateTree();
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}

	@Override
	public List<Channel> listPublishChannel() {
		// TODO Auto-generated method stub
		return null;
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
