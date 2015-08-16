package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Manager;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

/**
 * 文章数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("managerDao")
public class ManagerDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(ManagerDao.class);

	public Manager save(final Manager manager) throws Exception {
		manager.prePersist();
		saveWithPk(manager);
		return manager;
	}

	public Manager update(final Manager manager) {
		manager.preUpdate();
		baseSaveUpdate(manager);
		return manager;
	}

	public Manager load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Manager) this.baseQueryForEntity(Manager.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load Manager by id exception:", e);
			return null;
		}
	}

	public Manager load(String username, String password) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("username", username);
		paramMap.put("password", password);
		try {
			return (Manager) this.baseQueryForEntity(Manager.class, Conditions.loginConditiion, paramMap);
		} catch (Exception e) {
			throw e;
		}
	}

	public void delete(Manager manager) {
		this.baseDelete(manager);
	}

	public void delete(Long id) {
		Manager manager = new Manager();
		manager.setId(id);
		this.baseDelete(manager);
	}

	public GridBean pagination(Manager manager, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != manager) {

		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(Manager.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Manager> list = new ArrayList<Manager>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((Manager) SQLUtils.coverMapToBean(meta, Manager.class));
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(Manager.class, pageConditiion, paramMap, Orders.simpleCreateOrder(ORDER.DESC))
				.size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
