package cn.zgc.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zgc.cms.dao.ITopicDao;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Topic;

/** 
 * @author gczhang 
 * 文章Dao
 */
@Repository
public class TopicDao extends BasicHibernateDaoImpl<Topic> implements ITopicDao {

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		String hql = "";
		return null;
	}
	
	private String getTopicSelect() {
		return "select new Topic(t.id,t.title,t.keyword,t.status,t.recommend,t.publishDate,t.author,t.cname)";
	}

	@Override
	public Pager<Topic> find(Integer uid, Integer cid, String title, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<Topic> searchTopic(String con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<Topic> findRecommendTopic(Integer ci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> listTopicByChannelAndNumber(int cid, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> listTopicsByChannel(int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUpdateIndex(int cid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Topic loadLastedTopicByColumn(int cid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
