package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Order;
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
@Component("orderDao")
public class OrderDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(OrderDao.class);

	public Order save(final Order order) throws Exception {
		order.prePersist();
		saveWithPk(order);
		return order;
	}

	public Order update(final Order order) {
		order.preUpdate();
		baseSaveUpdate(order);
		return order;
	}

	public Order load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Order) this.baseQueryForEntity(Order.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load order by id exception:", e);
			return null;
		}
	}

	public void delete(Order order) {
		this.baseDelete(order);
	}

	public void delete(Long id) {
		Order order = new Order();
		order.setId(id);
		this.baseDelete(order);
	}

	public GridBean pagination(Order order, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != order) {
			if (null != order.getUserId()) {
				Object[] item = { "USER = :user" };
				conditionMetaList.add(item);
				paramMap.put("user", order.getUserId());
			}

		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(Order.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Order> list = new ArrayList<Order>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				Order orderBean = (Order) SQLUtils.coverMapToBean(meta, Order.class);
				list.add(orderBean);
			}
		}
		paramMap.remove(PagingUtils.IS_PAGING);
		int records = listCount(Order.class, pageConditiion, paramMap).intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
