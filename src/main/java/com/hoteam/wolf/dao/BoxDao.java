package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

@Component("boxDao")
public class BoxDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(BoxDao.class);

	public Box save(final Box box) throws Exception {
		box.prePersist();
		saveWithPk(box);
		return box;
	}

	public Box update(final Box box) {
		box.preUpdate();
		baseSaveUpdate(box);
		return box;
	}

	public void delete(Box box) {
		this.baseDelete(box);
	}

	public void delete(Long id) {
		Box box = new Box();
		box.setId(id);
		this.baseDelete(box);
	}

	public void deleteByProfessor(Long professor) {
		this.jdbcTemplate.execute("delete from box where author = " + professor);
	}

	public Box load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Box) this.baseQueryForEntity(Box.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load Box by id exception:", e);
			return null;
		}
	}

	public List<Box> queryForSimpleList(Box box, int pageNum, int pageSize) throws Exception {
		List<Box> notices = this.queryForDetailList(box, pageNum, pageSize);
		for (int i = 0; i < notices.size(); i++) {
			Box entity = notices.get(i);
			entity.setPublicContent(null);
			notices.set(i, entity);
		}
		return notices;
	}

	public List<Box> queryForDetailList(Box box, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != box) {
			if (null != box.getAuthor()) {
				Object[] item = { "author = :author" };
				conditionMetaList.add(item);
				paramMap.put("author", box.getAuthor());
			} else if (null != box.getStatus() && !box.getStatus().isEmpty()) {
				Object[] item = { "STATUS = :STATUS" };
				conditionMetaList.add(item);
				paramMap.put("STATUS", box.getStatus());
			}
		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metalList = baseQueryForList(Box.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Box> list = new ArrayList<Box>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				Box entity = (Box) SQLUtils.coverMapToBean(meta, Box.class);
				entity.setPrivateContent(null);
				list.add(entity);
			}
		}
		return list;
	}

	public GridBean pagination(Box box, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != box) {
			if (null != box.getAuthor()) {
				Object[] item = { "author = :author" };
				conditionMetaList.add(item);
				paramMap.put("author", box.getAuthor());
			}
			if (null != box.getStatus() && !box.getStatus().isEmpty()) {
				Object[] item = { "status = :status" };
				conditionMetaList.add(item);
				paramMap.put("status", box.getStatus());
			}
		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(Box.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Box> list = new ArrayList<Box>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				Box entity = (Box) SQLUtils.coverMapToBean(meta, Box.class);
				entity.setPrivateContent(null);
				if (entity.getPublicContent().length() > 200) {
					entity.setPublicContent(entity.getPublicContent().substring(0, 200));
				}
				list.add(entity);
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(Box.class, pageConditiion, paramMap, Orders.simpleCreateOrder(ORDER.DESC))
				.size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
