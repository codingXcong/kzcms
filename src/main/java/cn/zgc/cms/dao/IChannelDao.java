package cn.zgc.cms.dao;

import java.util.List;

import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.ChannelTree;

public interface IChannelDao extends IBasicHibernateDao<Channel>{
	
	/**
	 * 根据父id获取所有的子栏目
	 * @param pid
	 * @return
	 */
	public List<Channel> listByParent(Integer pid);
	/**
	 * 获取子栏目的最大的排序号
	 * @param pid
	 * @return
	 */
	public int getMaxOrderByParent(Integer pid);
	/**
	 * 把所有的栏目获取并生成一颗完整的树
	 * @return
	 */
	public List<ChannelTree> generateTree();
	/**
	 * 根据父类对象获取子类栏目，并且生成树列表
	 * @param pid
	 * @return
	 */
	public List<ChannelTree> generateTreeByParent(Integer pid);
	
}
