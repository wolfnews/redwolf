package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Message;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

/**
 * 活动数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("messageDao")
public class MessageDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(MessageDao.class);

	public Message save(final Message message) throws Exception {
		message.prePersist();
		saveWithPk(message);
		return message;
	}

	public Message load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Message) this.baseQueryForEntity(Message.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load Message by id exception:", e);
			return null;
		}
	}


	public void delete(Message message) {
		this.baseDelete(message);
	}

	public void delete(Long id) {
		Message message = new Message();
		message.setId(id);
		this.baseDelete(message);
	}

	public GridBean pagination(Message message, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != message) {
			if (null != message.getSenderId()) {
				Object[] itemObj = { "SENDER_ID = :sender" };
				conditionMetaList.add(itemObj);
				paramMap.put("sender", message.getSenderId());
			}
			if (null != message.getReceiverId()) {
				Object[] itemObj = { "RECEIVER_ID =:receiver" };
				conditionMetaList.add(itemObj);
				paramMap.put("receiver", message.getReceiverId());
			}
		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(Message.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Message> list = new ArrayList<Message>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((Message) SQLUtils.coverMapToBean(meta, Message.class));
			}
		}
		paramMap.remove(PagingUtils.IS_PAGING);
		int records = listCount(Message.class, pageConditiion, paramMap).intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}