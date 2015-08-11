package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Orders;
import com.hoteam.wolf.domain.RechargeRecord;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;

@Component("rechargeRecordDao")
public class RechargeRecordDao extends BaseDao {
	public RechargeRecord save(final RechargeRecord userPayment) throws Exception {
		userPayment.prePersist();
		saveWithPk(userPayment);
		return userPayment;
	}

	public RechargeRecord update(final RechargeRecord userPayment) {
		userPayment.preUpdate();
		baseSaveUpdate(userPayment);
		return userPayment;
	}

	public void delete(RechargeRecord userPayment) {
		this.baseDelete(userPayment);
	}

	public void delete(Long id) {
		RechargeRecord userPayment = new RechargeRecord();
		userPayment.setId(id);
		this.baseDelete(userPayment);
	}

	public void deleteByUser(Long userId) {
		this.jdbcTemplate.execute("delete from recharge_record where user_id=" + userId);
	}

	public RechargeRecord load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (RechargeRecord) this.baseQueryForEntity(RechargeRecord.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			return null;
		}
	}

	public RechargeRecord loadBySn(String sn) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("sn", sn);
		try {
			return (RechargeRecord) this.baseQueryForEntity(RechargeRecord.class,
					Conditions.simpleCondition("SN", "sn"), paramMap);
		} catch (Exception e) {
			return null;
		}
	}

	public GridBean pagination(RechargeRecord record, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != record) {
			if (null != record.getUserId()) {
				Object[] item = { "USER_ID = :USER" };
				conditionMetaList.add(item);
				paramMap.put("USER", record.getUserId());
			}
			if (null != record.getPaid()) {
				Object[] item = { "PAID = :PAID" };
				conditionMetaList.add(item);
				paramMap.put("PAID", record.getPaid());
			}
		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metalList = baseQueryForList(RechargeRecord.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				RechargeRecord entity = (RechargeRecord) SQLUtils.coverMapToBean(meta, RechargeRecord.class);
				list.add(entity);
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(RechargeRecord.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC)).size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
