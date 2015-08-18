package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.ConsumeRecord;
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
@Component("consumeRecordDao")
public class ConsumeRecordDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(ConsumeRecordDao.class);

	public ConsumeRecord save(final ConsumeRecord record) throws Exception {
		record.prePersist();
		saveWithPk(record);
		return record;
	}
	public ConsumeRecord load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (ConsumeRecord) this.baseQueryForEntity(ConsumeRecord.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load ConsumeRecord by id exception:", e);
			return null;
		}
	}

	public void delete(ConsumeRecord record) {
		this.baseDelete(record);
	}

	public void delete(Long id) {
		ConsumeRecord record = new ConsumeRecord();
		record.setId(id);
		this.baseDelete(record);
	}

	public void deleteByUser(Long user){
		
	}

	public GridBean pagination(ConsumeRecord record, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != record && null != record.getUserId()) {
			Object[] item = { "USER_ID = :user" };
			conditionMetaList.add(item);
			paramMap.put("user", record.getUserId());
		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(ConsumeRecord.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<ConsumeRecord> list = new ArrayList<ConsumeRecord>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((ConsumeRecord) SQLUtils.coverMapToBean(meta, ConsumeRecord.class));
			}
		}
		paramMap.remove(PagingUtils.IS_PAGING);
		int records = listCount(ConsumeRecord.class, pageConditiion, paramMap).intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
	
}
