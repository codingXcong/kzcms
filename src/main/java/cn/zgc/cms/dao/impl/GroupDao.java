package cn.zgc.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.zgc.cms.dao.IGroupDao;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.Pager;
@Repository
public class GroupDao extends BasicHibernateDaoImpl<Group> implements IGroupDao {

	public List<Group> listGroup() {
		return this.list("from Group");
	}

	public Pager<Group> findGroup() {
		return this.find("from Group");
	}

	public void deleteGroupUsers(int gid) {
		this.updateObject("delete UserGroup ug where ug.group.id=?", gid);
	}
	
}
