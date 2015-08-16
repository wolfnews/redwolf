package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.enums.ActivityCategory;
import com.hoteam.wolf.domain.Activity;
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
@Component("activityDao")
public class ActivityDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(ActivityDao.class);

	public Activity save(final Activity activity) throws Exception {
		activity.prePersist();
		saveWithPk(activity);
		return activity;
	}

	public Activity update(final Activity activity) {
		activity.preUpdate();
		baseSaveUpdate(activity);
		return activity;
	}

	public Activity load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Activity) this.baseQueryForEntity(Activity.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load Activity by id exception:", e);
			return null;
		}
	}

	// 会有定时任务对活动数据进行清理
	public Activity loadByCateogry(ActivityCategory category) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("category", category.toString());
		paramMap.put("valid", true);
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("CATEGORY", "category");
		conditionMap.put("VALID", "valid");
		try {
			return (Activity) this.baseQueryForEntity(Activity.class, Conditions.complexCondition(conditionMap),
					paramMap);
		} catch (Exception e) {
			logger.error("Load Activity by id exception:", e);
			return null;
		}
	}

	public List<Activity> loadAll() {
		List<Activity> list = new ArrayList<Activity>();

		try {
			List<Map<String, Object>> metaList = this.namedParameterJdbcTemplate.queryForList("select * from activity",
					new HashMap<String, Object>());
			if (null != metaList && !metaList.isEmpty()) {
				for (Map<String, Object> meta : metaList) {
					list.add((Activity) SQLUtils.coverMapToBean(meta, Activity.class));
				}
			}
		} catch (Exception e) {
			logger.error("load all activity exception:", e);
		}
		return list;
	}

	public void delete(Activity activity) {
		this.baseDelete(activity);
	}

	public void delete(Long id) {
		Activity activity = new Activity();
		activity.setId(id);
		this.baseDelete(activity);
	}

	public GridBean pagination(Activity activity, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(Activity.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Activity> list = new ArrayList<Activity>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((Activity) SQLUtils.coverMapToBean(meta, Activity.class));
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(Activity.class, pageConditiion, paramMap, Orders.simpleCreateOrder(ORDER.DESC))
				.size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
