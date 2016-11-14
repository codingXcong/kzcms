package cn.zgc.cms.dao.impl;

import java.util.List;
import java.util.Map;

import cn.zgc.cms.dao.IChannelDao;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.Pager;

public class ChannelDao extends BasicHibernateDaoImpl<Channel> implements IChannelDao {
	@Override
	public List<Channel> listByParent(Integer pid) {
		
		return null;
	}

	@Override
	public int getMaxOrderByParent(Integer pid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChannelTree> generateTree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
