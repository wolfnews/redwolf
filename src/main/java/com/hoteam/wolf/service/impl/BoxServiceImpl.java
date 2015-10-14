package com.hoteam.wolf.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.enums.BoxCategory;
import com.hoteam.wolf.common.enums.BoxStatus;
import com.hoteam.wolf.dao.BoxDao;
import com.hoteam.wolf.dao.BoxVerifyRecordDao;
import com.hoteam.wolf.dao.ProfessorDao;
import com.hoteam.wolf.dao.UserSubscribeDao;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.domain.BoxVerifyRecord;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.UserSubscribe;
import com.hoteam.wolf.message.MessageSender;
import com.hoteam.wolf.service.BoxService;

@Service("boxService")
public class BoxServiceImpl implements BoxService {

	private static Logger logger = Logger.getLogger(BoxServiceImpl.class);
	@Autowired
	private BoxDao boxDao;
	@Autowired
	private UserSubscribeDao userSubscribeDao;
	@Autowired
	private BoxVerifyRecordDao boxVerifyRecordDao;
	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private MessageSender messageSender;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result addBox(Box box, HttpServletRequest request) throws Exception {
		try {
			Professor professor = this.professorDao.load(box.getAuthor());
			if(professor.isNeedVerify()){
				box.setStatus(BoxStatus.NEW_CREATED.name());
			}else{
				box.setStatus(BoxStatus.NORMAL.name());
			}
			this.boxDao.save(box);
			if(!professor.isNeedVerify()){
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				StringBuffer messageBuffer = new StringBuffer();
				messageBuffer.append("【").append(professor.getUsername())
				.append("】刚刚发表了一个宝盒！点击<a target=\"parent\" href=\"")
				.append(basePath).append("box/detail.html?id=").append(box.getId())
				.append("\">【这里】</a>快速查看");
				messageSender.pushMessage(messageBuffer.toString());
				logger.info(messageBuffer.toString());
			}
			return new Result(true, "add box success");
		} catch (Exception e) {
			logger.error("add box exception:", e);
			throw new Exception("add box exception server");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result removeBox(Long boxId) throws Exception {
		try {
			this.boxDao.delete(boxId);
			this.boxVerifyRecordDao.deleteByBox(boxId);
			return new Result(true, "delete box success");
		} catch (Exception e) {
			logger.error("delete box exception:", e);
			throw new Exception("delete box exception server");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result verifyBox(Long boxId, String manager, boolean passed, String reason,HttpServletRequest request) throws Exception {
		try {
			Box box = this.boxDao.load(boxId);
			String result = "";
			if (passed) {
				result = "审核通过";
				box.setStatus(BoxStatus.NORMAL.name());
			} else {
				result = "审核拒绝";
				box.setStatus(BoxStatus.REFUSED.name());
			}
			this.boxDao.update(box);
			if(passed){
				Professor professor = this.professorDao.load(box.getAuthor());
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				StringBuffer messageBuffer = new StringBuffer();
				messageBuffer.append("【").append(professor.getUsername())
				.append("】刚刚发表了一个宝盒！点击<a target=\"parent\" href=\"")
				.append(basePath).append("box/detail.html?id=").append(box.getId())
				.append("\">【这里】</a>快速查看");
				messageSender.pushMessage(messageBuffer.toString());
				logger.info(messageBuffer.toString());
			}
			BoxVerifyRecord record = new BoxVerifyRecord(boxId, manager, result, reason);
			this.boxVerifyRecordDao.save(record);
			return new Result(true, "verify box success");
		} catch (Exception e) {
			logger.error("verify box exception:", e);
			throw new Exception("verify box exception!");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public GridBean pagination(Box box, int pageNum, int pageSize) throws Exception {
		try {
			return this.boxDao.pagination(box, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("box pagination exception:", e);
			throw new Exception("box pagination exception");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public EntityResult load(Long boxId) throws Exception {
		Box box = this.boxDao.load(boxId);
		return new EntityResult(true, null, box);
	}

	@Override
	public List<Box> queryForSimpleList(Box box, int pageNum, int pageSize) throws Exception {
		return this.boxDao.queryForSimpleList(box, pageNum, pageSize);
	}

	@Override
	public List<Box> queryForDetailList(Box box, int pageNum, int pageSize) throws Exception {
		List<Box> boxs = this.boxDao.queryForDetailList(box, pageNum, pageSize);
		boxs = this.assembleNoitceBeans(boxs);
		return boxs;
	}

	@Override
	public EntityResult detail(Long userId, Long boxId) throws Exception {
		Box box = this.boxDao.load(boxId);
		if(null == box){
			return new EntityResult(false, "盒子不存在");
		}
		box.setBrowseNum(box.getBrowseNum()+1);
		this.boxDao.update(box);
		boolean readable = false;
		switch (BoxCategory.valueOf(box.getCategory())) {
		case CHARGE:
			if(null == userId){
				break;
			}
			UserSubscribe userSubscribe = this.userSubscribeDao.load(box.getAuthor(), userId, box.getGroupId());
			if (null == userSubscribe || userSubscribe.isExpired()) {
				logger.warn("user do not subscribe this charge box or subscribe time is out!");
			} else {
				readable = true;
			}
			break;
		case FREE:
			readable = true;
			break;
		default:
			break;
		}
		if (readable) {
			return new EntityResult(true, "SUCCESS", box);
		} else {
			box.setPrivateContent("");
			return new EntityResult(true, "NSOSO", box);//NO_SUBS_OR_SUBS_OUT
		}
	}

	public List<Box> assembleNoitceBeans(List<Box> entitys) throws Exception{
		List<Box> boxBeans = new ArrayList<Box>();
		for (Box entity : entitys) {
			entity.setPrivateContent(null);
			String content = entity.getPublicContent();
			if(content.length()>1000){
				entity.setPublicContent(content.substring(0, 1000));
			}
			boxBeans.add(entity);
		}
		return boxBeans;
	}
}
