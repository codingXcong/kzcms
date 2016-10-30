package cn.zgc.cms.service;

import java.util.List;

import cn.zgc.cms.model.Group;

public interface IGroupService {
	
	/**
	 * 获取所有的组信息
	 * @return
	 */
	List<Group> listGroup();

}
