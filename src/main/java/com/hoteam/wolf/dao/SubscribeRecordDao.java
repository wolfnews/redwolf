package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.SubscribeRecord;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

@Component("subscribeRecordDao")
public class SubscribeRecordDao extends BaseDao {
	public SubscribeRecord save(final SubscribeRecord subscribeRecord) throws Exception {
		subscribeRecord.prePersist();
		saveWithPk(subscribeRecord);
		return subscribeRecord;
	}

	public SubscribeRecord update(final SubscribeRecord subscribeRecord) {
		subscribeRecord.preUpdate();
		baseSaveUpdate(subscribeRecord);
		return subscribeRecord;
	}

	public void delete(SubscribeRecord subscribeRecord) {
		this.baseDelete(subscribeRecord);
	}

	public void delete(Long id) {
		SubscribeRecord subscribeRecord = new SubscribeRecord();
		subscribeRecord.setId(id);
		this.baseDelete(subscribeRecord);
	}

	public void deleteByUser(Long userId) {
		this.jdbcTemplate.execute("delete from subscribe_record where user_id=" + userId);
	}

	public GridBean pagination(SubscribeRecord record, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != record) {
			if (null != record.getUserId()) {
				Object[] item = { "USER_ID = :USER" };
				conditionMetaList.add(item);
				paramMap.put("USER", record.getUserId());
			}
		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metalList = baseQueryForList(SubscribeRecord.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<SubscribeRecord> list = new ArrayList<SubscribeRecord>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				SubscribeRecord entity = (SubscribeRecord) SQLUtils.coverMapToBean(meta, SubscribeRecord.class);
				list.add(entity);
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(SubscribeRecord.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC)).size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
