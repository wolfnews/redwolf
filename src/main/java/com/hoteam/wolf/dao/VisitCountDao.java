package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.VisitLineBean;
import com.hoteam.wolf.domain.VisitBean;
import com.hoteam.wolf.domain.VisitCount;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;
import com.hoteam.wolf.utils.DateUtil;

/**
 * 文章数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("visitCountDao")
public class VisitCountDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(VisitCountDao.class);

	public VisitCount save(final VisitCount visitCount) throws Exception {
		visitCount.prePersist();
		saveWithPk(visitCount);
		return visitCount;
	}

	public VisitCount update(final VisitCount visitCount) {
		visitCount.preUpdate();
		baseSaveUpdate(visitCount);
		return visitCount;
	}

	public VisitCount load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (VisitCount) this.baseQueryForEntity(VisitCount.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load VisitCount by id exception:", e);
			return null;
		}
	}

	public VisitCount load(String source, String date, String target) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("source", source);
		paramMap.put("date", date);
		paramMap.put("target", target);
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("VISIT_DATE", "date");
		conditionMap.put("SOURCE", "source");
		conditionMap.put("TARGET", "target");
		try {
			return (VisitCount) this.baseQueryForEntity(VisitCount.class, Conditions.complexCondition(conditionMap),
					paramMap);
		} catch (Exception e) {
			logger.error("Load VisitCount by category exception:", e);
			return null;
		}
	}

	public void delete(VisitCount visitCount) {
		this.baseDelete(visitCount);
	}

	public void delete(Long id) {
		VisitCount visitCount = new VisitCount();
		visitCount.setId(id);
		this.baseDelete(visitCount);
	}

	public VisitLineBean visitCount() throws Exception {
		String sql = "select visit_date as `date`, count(source)as uv,sum(amount)as pv  from visit_count group by `date` order by `date` asc";
		List<Map<String, Object>> metaList = this.namedParameterJdbcTemplate.queryForList(sql,
				new HashMap<String, Object>());
		List<VisitBean> list = new ArrayList<VisitBean>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((VisitBean) SQLUtils.coverMapToBean(meta, VisitBean.class));
			}
		}
		List<String> dates = new ArrayList<String>();
		List<Long> uvs = new ArrayList<Long>();
		List<Long> pvs = new ArrayList<Long>();

		for (VisitBean bean : list) {
			dates.add(DateUtil.getDate(bean.getDate()));
			uvs.add(bean.getUv());
			pvs.add(bean.getPv().longValue());
		}
		return new VisitLineBean(dates, pvs, uvs);
	}

	public GridBean pagination(VisitCount VisitCount, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != VisitCount) {
			if (null != VisitCount.getTarget() && !VisitCount.getTarget().isEmpty()) {
				Object[] item = { "TARGET = :target" };
				conditionMetaList.add(item);
				paramMap.put("target", VisitCount.getTarget());
			}

		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(VisitCount.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<VisitCount> list = new ArrayList<VisitCount>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((VisitCount) SQLUtils.coverMapToBean(meta, VisitCount.class));
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(VisitCount.class, pageConditiion, paramMap, Orders.simpleCreateOrder(ORDER.DESC))
				.size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
