package cn.zgc.cms.dao.impl;

import org.springframework.stereotype.Repository;

import cn.zgc.cms.dao.IRoleDao;
import cn.zgc.cms.model.Role;
@Repository("roleDao")
public class RoleDao extends BasicHibernateDaoImpl<Role> implements IRoleDao {

	
}
