package cn.zgc.cms.service;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.User;
public interface IUserService {
	/**
	 * 列表用户
	 */
	public Pager<User> findUser();
}
