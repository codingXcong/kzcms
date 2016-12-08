package cn.zgc.cms.dwr;

import org.springframework.beans.factory.annotation.Autowired;

import cn.zgc.cms.service.IGroupService;

public class DwrService implements IDwrService{
	@Autowired
	private IGroupService groupService;

	@Override
	public void addGroupChannel(int gid, int cid) {
		groupService.addGroupChannel(gid, cid);
	}

	@Override
	public void deleteGroupChannel(int gid, int cid) {
		groupService.deleteGroupChannel(gid, cid);
	}

}
