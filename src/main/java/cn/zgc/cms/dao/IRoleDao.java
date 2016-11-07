package cn.zgc.cms.dao;

import java.util.List;

import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Role;

public interface IRoleDao extends IBasicHibernateDao<Role>{

	List<Role> listRole();

	Pager<Role> findRole();

}
