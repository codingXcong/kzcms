package cn.zgc.cms.model;

/**
 * 栏目类型
 * @author gczhang
 */
public enum ChannelType {
	NAV("导航栏目"),ARTICLES("文章列表栏目"),CONTENT("文章内容栏目"),IMAGES("图片列表栏目");

	private String name;
	
	private ChannelType(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
