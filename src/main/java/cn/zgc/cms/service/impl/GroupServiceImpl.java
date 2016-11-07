package cn.zgc.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IGroupDao;
import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.Group;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.service.IGroupService;
@Service("groupService")
public class GroupServiceImpl implements IGroupService{
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IUserDao userDao;
		
	public List<Group> listGroup() {
		return groupDao.listGroup();
	}

	public Pager<Group> findGroup() {
		return groupDao.findGroup();
	}

	public void add(Group group) {
		groupDao.add(group);
	}

	public Group load(int id) {
		return groupDao.getByPk(id);
	}

	public void update(Group gp) {
		groupDao.update(gp);
	}

	public void delete(int id) {
		groupDao.delete(id);
	}

	public void deleteGroupUsers(int gid) {
		groupDao.deleteGroupUsers(gid);
	}
	
	
}
