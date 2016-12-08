package cn.zgc.cms.dwr;

/**
 * 发布dwr服务供前段页面使用
 * @author gczhang
 *
 */
public interface IDwrService {
	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	public void addGroupChannel(int gid,int cid);
	
	/**
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
}
