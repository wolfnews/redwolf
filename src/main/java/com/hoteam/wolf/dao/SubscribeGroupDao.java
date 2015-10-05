package com.hoteam.wolf.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.domain.SubscribeGroup;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

@Component("subscribeGroupDao")
public class SubscribeGroupDao extends BaseDao {
	private static Logger logger = Logger.getLogger(SubscribeGroupDao.class);

	public SubscribeGroup save(final SubscribeGroup subscribeGroup) throws Exception {
		subscribeGroup.prePersist();
		saveWithPk(subscribeGroup);
		return subscribeGroup;
	}

	public SubscribeGroup update(final SubscribeGroup subscribeGroup) {
		subscribeGroup.preUpdate();
		baseSaveUpdate(subscribeGroup);
		return subscribeGroup;
	}

	public void delete(SubscribeGroup subscribeGroup) {
		this.baseDelete(subscribeGroup);
	}

	public void delete(Long id) {
		SubscribeGroup subscribeGroup = new SubscribeGroup();
		subscribeGroup.setId(id);
		this.baseDelete(subscribeGroup);
	}

	public void deleteByProfessor(Long professorId) {
		Object[] params = { professorId };
		this.jdbcTemplate
				.update("delete from subscribe_group where professor_id=?", params, new int[] { Types.BIGINT });
	}

	public SubscribeGroup load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (SubscribeGroup) this.baseQueryForEntity(SubscribeGroup.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load SubscribeGroup by id exception:", e);
			return null;
		}
	}

	public SubscribeGroup load(Long professorId, String groupName) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("professor", professorId);
		paramMap.put("group", groupName);
		try {
			Map<String, String> conditionMap = new HashMap<String, String>();
			conditionMap.put("PROFESSOR_ID", "professor");
			conditionMap.put("NAME", "group");
			return (SubscribeGroup) this.baseQueryForEntity(SubscribeGroup.class,
					Conditions.complexCondition(conditionMap), paramMap);
		} catch (Exception e) {
			logger.error("Load SubscribeGroup by professor and group exception:", e);
			return null;
		}
	}

	public List<SubscribeGroup> queryForList(SubscribeGroup group) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (null != group) {
			if (null != group.getProfessorId()) {
				Object[] item = { "professor_id =:professor" };
				conditionMetaList.add(item);
				paramMap.put("professor", group.getProfessorId());
			}
		}
		List<Map<String, Object>> metalList = baseQueryForList(SubscribeGroup.class,
				Conditions.simpleCondition("PROFESSOR_ID", "professor"), paramMap, Orders.simpleCreateOrder(ORDER.DESC));
		List<SubscribeGroup> list = new ArrayList<SubscribeGroup>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				SubscribeGroup entity = (SubscribeGroup) SQLUtils.coverMapToBean(meta, SubscribeGroup.class);
				list.add(entity);
			}
		}
		return list;
	}
}
