package cn.zgc.cms.dao;

import java.util.List;

import cn.zgc.cms.model.Group;

public interface IGroupDao extends IBasicHibernateDao<Group>{

	List<Group> listGroup();

}
