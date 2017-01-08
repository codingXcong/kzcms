package cn.zgc.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IAttachmentDao;
import cn.zgc.cms.dao.IChannelDao;
import cn.zgc.cms.dao.ITopicDao;
import cn.zgc.cms.dao.IUserDao;
import cn.zgc.cms.model.Attachment;
import cn.zgc.cms.model.Channel;
import cn.zgc.cms.model.CmsException;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.model.Topic;
import cn.zgc.cms.model.User;
import cn.zgc.cms.service.ITopicService;

/** 
 * @author gczhang 
 * 
 */
@Service
public class TopicService implements ITopicService {
	@Resource
	private ITopicDao topicDao;
	@Resource
	private IAttachmentDao attachmentDao;
	@Resource
	private IChannelDao channelDao;
	@Resource
	private IUserDao userDao;
	
	private void addTopicAtts(Topic topic,Integer[] aids) {
		if(aids!=null) {
			for(Integer aid:aids) {
				Attachment a = attachmentDao.getByPk(aid);
				if(a==null) continue;
				a.setTopic(topic);
			}
		}
	}
	
	@Override
	public void add(Topic topic, int cid, int uid, Integer[] aids) {
		Channel c = channelDao.getByPk(cid);
		User u = userDao.getByPk(uid);
		if(c==null||u==null)throw new CmsException("要添加的文章必须有用户和栏目");
		topic.setAuthor(u.getNickname());
		topic.setCname(c.getName());
		topic.setCreateDate(new Date());
		topic.setChannel(c);
		topic.setUser(u);
		topicDao.add(topic);
		addTopicAtts(topic, aids);
	}

	@Override
	public void add(Topic topic, int cid, int uid) {
		add(topic,cid,uid,null);
	}

	@Override
	public void delete(int id) {
		List<Attachment> atts = attachmentDao.listByTopic(id);
		attachmentDao.deleteByTopic(id);
		topicDao.delete(id);
		//删除硬盘上面的文件
		for(Attachment a:atts) {
			AttachmentService.deleteAttachFiles(a);
		}
	}

	@Override
	public void update(Topic topic, int cid, Integer[] aids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Topic topic, int cid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStatus(int tid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Topic load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return topicDao.find(cid, title, status);
	}

	@Override
	public Pager<Topic> find(Integer uid, Integer cid, String title, Integer status) {
		return topicDao.find(uid, cid, title, status);
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
	public List<Topic> listTopicByChannel(int cid) {
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
