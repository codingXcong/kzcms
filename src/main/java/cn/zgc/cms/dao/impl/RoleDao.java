package cn.zgc.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zgc.cms.dao.IRoleDao;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Role;
@Repository("roleDao")
public class RoleDao extends BasicHibernateDaoImpl<Role> implements IRoleDao {

	public List<Role> listRole() {
		return this.list("from Role");
	}

	public Pager<Role> findRole() {
		return this.find("from Role");
	}

	

	
}
