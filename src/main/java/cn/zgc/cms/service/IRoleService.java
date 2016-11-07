package cn.zgc.cms.service;

import java.util.List;

import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Role;

public interface IRoleService {
	
	//获取所有的角色信息
	List<Role> listRole();
	
	/**
	 * 分页展示角色信息
	 * @return
	 */
	Pager<Role> findRole();

}
