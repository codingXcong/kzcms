package cn.zgc.cms.dao;

import java.util.List;

import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.Pager;

public interface IGroupDao extends IBasicHibernateDao<Group>{

	List<Group> listGroup();

	Pager<Group> findGroup();
	
	void deleteGroupUsers(int gid);

}
