package cn.zgc.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zgc.cms.dao.IChannelDao;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;
import cn.zgc.cms.model.ChannelType;
/**
 * 渠道dao
 * @author gc
 */
@Repository
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
	
	public static void initTreeNode(List<ChannelTree> cts) {
		cts.add(0, new ChannelTree(Channel.ROOT_ID, Channel.ROOT_NAME, -1));
		for(ChannelTree ct : cts){
			if(ct.getPid()==null) ct.setPid(0); 
		}
	}

	@Override
	public List<ChannelTree> generateTree() {
		String sql = "select id,name,pid from t_channel order by orders";
		List<ChannelTree> cTree = this.listBySQL(sql,ChannelTree.class,false);
		initTreeNode(cTree);
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

	@Override
	public List<Channel> listPublishChannel() {
		String hql = "select new Channel(c.id,c.name) from Channel c where c.status=0 and c.type!="+ChannelType.NAV.ordinal();
		return this.list(hql);
	}

}
