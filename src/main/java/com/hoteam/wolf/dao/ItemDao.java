package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Item;
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
@Component("itemDao")
public class ItemDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(ItemDao.class);

	public Item save(final Item item) throws Exception {
		item.prePersist();
		saveWithPk(item);
		return item;
	}

	public Item update(final Item item) {
		item.preUpdate();
		baseSaveUpdate(item);
		return item;
	}

	public Item load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Item) this.baseQueryForEntity(Item.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load item by id exception:", e);
			return null;
		}
	}

	public void delete(Item item) {
		this.baseDelete(item);
	}

	public void delete(Long id) {
		Item item = new Item();
		item.setId(id);
		this.baseDelete(item);
	}

	public GridBean pagination(Item item, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != item) {
			if (null != item.getCategory() && !item.getCategory().isEmpty()) {
				Object[] itemObj = { "CATEGORY = :category" };
				conditionMetaList.add(itemObj);
				paramMap.put("category", item.getCategory());
			}

		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(Item.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Item> list = new ArrayList<Item>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				Item itemBean = (Item) SQLUtils.coverMapToBean(meta, Item.class);
				list.add(itemBean);
			}
		}
		paramMap.remove(PagingUtils.IS_PAGING);
		int records = listCount(Item.class, pageConditiion, paramMap).intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
