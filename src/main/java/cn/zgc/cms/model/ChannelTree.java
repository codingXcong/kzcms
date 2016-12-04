package cn.zgc.cms.model;
/**
 * 栏目树对象,用于ztree的封装
 * @author gczhang
 */
public class ChannelTree{
	private Integer id;
	private String name;
	private Integer pid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public ChannelTree(Integer id, String name, Integer pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
	public ChannelTree() {
		super();
	}
	
	
}
