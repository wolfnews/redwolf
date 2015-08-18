package com.hoteam.wolf.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.dao.MessageDao;
import com.hoteam.wolf.domain.Message;
import com.hoteam.wolf.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	private static Logger logger = Logger.getLogger(MessageServiceImpl.class);
	@Autowired
	private MessageDao messageDao;
	@Override
	public boolean saveMessage(Message message) {
		try {
			this.messageDao.save(message);
			return true;
		} catch (Exception e) {
			logger.error("save messge exception:",e);
			return false;
		}
	}

	@Override
	public Message load(Long id) {
		return this.messageDao.load(id);
	}

	@Override
	public GridBean list(Message message, int pageNum, int pageSize) {
		try {
			return this.messageDao.pagination(message, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("list message exception:",e);
			return new GridBean(0, 0, 0, new ArrayList<Message>());
		}
	}

	@Override
	public boolean remove(Long id) {
		try {
			this.messageDao.delete(id);
			return true;
		} catch (Exception e) {
			logger.error("remove message exception:",e);
			return false;
		}
	}

}
