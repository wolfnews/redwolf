package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Carts;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

/**
 * 商品数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("cartDao")
public class CartDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(CartDao.class);

	public Carts save(final Carts cart) throws Exception {
		cart.prePersist();
		saveWithPk(cart);
		return cart;
	}

	public Carts update(final Carts cart) {
		cart.preUpdate();
		baseSaveUpdate(cart);
		return cart;
	}

	public Carts load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Carts) this.baseQueryForEntity(Carts.class, Conditions.loadCondition, paramMap);
		} catch (Exception e) {
			logger.error("Load cart by id exception:", e);
			return null;
		}
	}

	public void delete(Carts cart) {
		this.baseDelete(cart);
	}

	public void delete(Long id) {
		Carts cart = new Carts();
		cart.setId(id);
		this.baseDelete(cart);
	}

	public void deleteAll(List<Long> ids){
		String sql = "delete from carts where id in (:ids)";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ids", ids);
		this.namedParameterJdbcTemplate.update(sql, param);
	}
	public void deleteByUser(Long user){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("user", user);
		String sql = "delete from carts where user_id =:user";
		this.namedParameterJdbcTemplate.update(sql, param);
	}
	
	public List<Carts> loadByUser(Long user) throws Exception {
		String sql = "select * from carts where user_id =:user";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user", user);
		List<Map<String, Object>> metaList = this.namedParameterJdbcTemplate.queryForList(sql, param);
		List<Carts> list = new ArrayList<Carts>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				Carts itemBean = (Carts) SQLUtils.coverMapToBean(meta, Carts.class);
				list.add(itemBean);
			}
		}
		return list;
	}
	public GridBean pagination(Carts cart, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != cart) {
			if (null != cart.getUserId()) {
				Object[] itemObj = { "USER_ID = :user" };
				conditionMetaList.add(itemObj);
				paramMap.put("user", cart.getUserId());
			}

		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(Carts.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Carts> list = new ArrayList<Carts>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				Carts itemBean = (Carts) SQLUtils.coverMapToBean(meta, Carts.class);
				list.add(itemBean);
			}
		}
		paramMap.remove(PagingUtils.IS_PAGING);
		int records = listCount(Carts.class, pageConditiion, paramMap).intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
