package cn.zgc.cms.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zgc.cms.dao.IAttachmentDao;
import cn.zgc.cms.model.Attachment;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.service.IAttachmentService;
import cn.zgc.cms.util.SystemContext;

/** 
 * @author gczhang
 * 
 */
@Service
public class AttachmentService implements IAttachmentService{
	@Resource
	private IAttachmentDao attachmentDao;
	public final static String UPLOAD_PATH="/upload/";
	
	
	@Override
	public void add(Attachment a, InputStream is) throws IOException {
		try {
			attachmentDao.add(a);
			//addFile(a,is);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void delete(int id) {
		Attachment a = attachmentDao.getByPk(id);
		attachmentDao.delete(id);
	}

	@Override
	public Attachment load(int id) {
		return attachmentDao.getByPk(id);
	}

	@Override
	public Pager<Attachment> findNoUseAttachment() {
		return attachmentDao.findNoUseAttachment();
	}

	@Override
	public void clearNoUseAttachment() {
		attachmentDao.clearNoUseAttachment();
	}

	@Override
	public List<Attachment> listByTopic(int tid) {
		return attachmentDao.listByTopic(tid);
	}

	@Override
	public List<Attachment> listIndexPic(int num) {
		return attachmentDao.listIndexPic(num);
	}

	@Override
	public Pager<Attachment> findChannelPic(int cid) {
		return attachmentDao.findChannelPic(cid);
	}

	@Override
	public List<Attachment> listAttachByTopic(int tid) {
		return attachmentDao.listAttachByTopic(tid);
	}

	@Override
	public void updateIndexPic(int aid) {
		Attachment att = attachmentDao.getByPk(aid);
		System.out.println(aid+"------------>");
		if(att.getIsIndexPic()==0) {
			att.setIsIndexPic(1);
		} else {
			att.setIsIndexPic(0);
		}
		attachmentDao.update(att);
	}

	@Override
	public void updateAttachInfo(int aid) {
		Attachment att = attachmentDao.getByPk(aid);
		if(att.getIsAttach()==0) {
			att.setIsAttach(1);
		} else {
			att.setIsAttach(0);
		}
		attachmentDao.update(att);
	}

	@Override
	public Pager<Attachment> listAllPic() {
		return attachmentDao.listAllIndexPic();
	}

	@Override
	public long findNoUseAttachmentNum() {
		return attachmentDao.findNoUseAttachmentNum();
	}

	public static void deleteAttachFiles(Attachment a) {
		String realPath = SystemContext.getRealPath();
		realPath +=UPLOAD_PATH;
		new File(realPath+a.getNewName()).delete();
	}

}
