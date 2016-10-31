package cn.zgc.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.zgc.cms.dao.IGroupDao;
import cn.zgc.cms.model.Group;
@Repository
public class GroupDao extends BasicHibernateDaoImpl<Group> implements IGroupDao {

	public List<Group> listGroup() {
		return this.list("from Group");
	}
	
}