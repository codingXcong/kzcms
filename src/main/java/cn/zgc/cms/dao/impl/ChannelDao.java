package cn.zgc.cms.dao.impl;

import java.util.List;

import cn.zgc.cms.dao.IChannelDao;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;

public class ChannelDao extends BasicHibernateDaoImpl<Channel> implements IChannelDao {
	@Override
	public List<Channel> listByParent(Integer pid) {
		String hql = "";
		if(pid==null||pid==0)
			hql = "select c from Channel c where c.parent is null order by c.orders";
		else
			hql = "select c from Channel c left join fetch c.parent cp where cp.id="+pid+" order by c.orders";
		return this.list(hql);
	}

	@Override
	public int getMaxOrderByParent(Integer pid) {
		String hql = "";
		if(pid==null||pid==0)
			hql = "select max(c.orders) from Channel c where c.parent is null";
		else
			hql = "select max(c.orders) from Channel c where c.parent.id="+pid;
		Object obj = this.queryObject(hql);
		return obj==null?0:(int)obj;
	}

	@Override
	public List<ChannelTree> generateTree() {
		String sql = "select id,name,pid from t_channel order by orders";
		List<ChannelTree> cTree = this.listBySQL(sql,ChannelTree.class,false);
		return cTree;
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		String sql = "";
		if(pid==null||pid==0){
			sql = "select id,name,pid from t_channel where pid is null order by orders";
		}else{
			sql = "select id,name,pid from t_channel where pid ="+pid+" order by orders";
		}
		return this.listBySQL(sql, ChannelTree.class, false);
	}

}
