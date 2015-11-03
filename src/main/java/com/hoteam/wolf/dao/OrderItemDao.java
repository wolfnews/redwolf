package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.domain.OrderItem;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.SQLUtils;

/**
 * 订单商品关系数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("orderItemDao")
public class OrderItemDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(OrderItemDao.class);

	public OrderItem save(final OrderItem orderItem) throws Exception {
		orderItem.prePersist();
		saveWithPk(orderItem);
		return orderItem;
	}

	public OrderItem update(final OrderItem orderItem) {
		orderItem.preUpdate();
		baseSaveUpdate(orderItem);
		return orderItem;
	}

	public OrderItem load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (OrderItem) this.baseQueryForEntity(OrderItem.class, Conditions.loadCondition, paramMap);
		} catch (Exception e) {
			logger.error("Load orderItem by id exception:", e);
			return null;
		}
	}

	public List<OrderItem> loadByOrder(Long order) throws Exception{
		String sql = "select order_item.id,order_item.item_id as item_id,order_item.order_id as order_id,item.name,order_item.price,order_item.amount from order_item inner join item on order_item.order_id=:order and order_item.item_id = item.id";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("order", order);
		List<Map<String,Object>> metaList = this.namedParameterJdbcTemplate.queryForList(sql, param);
		List<OrderItem> items = new ArrayList<OrderItem>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				OrderItem entity = (OrderItem) SQLUtils.coverMapToBean(meta, OrderItem.class);
				items.add(entity);
			}
		}
		return items;
	}
	public void delete(OrderItem orderItem) {
		this.baseDelete(orderItem);
	}

	public void delete(Long id) {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(id);
		this.baseDelete(orderItem);
	}
	
	public void deleteByOrder(Long order){
		String sql="delete from order_item where order_id =:order";
		Map<String,Object> param =  new HashMap<String,Object>();
		param.put("order", order);
		this.namedParameterJdbcTemplate.update(sql, param);
	}
}
