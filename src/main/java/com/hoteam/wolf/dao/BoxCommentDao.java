package com.hoteam.wolf.dao;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Orders;
import com.hoteam.wolf.domain.BoxComment;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;

@Component("boxCommentDao")
public class BoxCommentDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(BoxCommentDao.class);

	public BoxComment save(final BoxComment boxComment) throws Exception {
		boxComment.prePersist();
		saveWithPk(boxComment);
		return boxComment;
	}

	public BoxComment update(final BoxComment boxComment) {
		boxComment.preUpdate();
		baseSaveUpdate(boxComment);
		return boxComment;
	}

	public void delete(BoxComment boxComment) {
		this.baseDelete(boxComment);
	}

	public void delete(Long id) {
		BoxComment boxComment = new BoxComment();
		boxComment.setId(id);
		this.baseDelete(boxComment);
	}

	public BoxComment load(String username, String password) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("username", username);
		paramMap.put("password", password);
		try {
			return (BoxComment) this.baseQueryForEntity(BoxComment.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load BoxComment by id exception:", e);
			return null;
		}
	}

	public GridBean pagination(BoxComment boxComment, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(BoxComment.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<BoxComment> list = new ArrayList<BoxComment>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((BoxComment) SQLUtils.coverMapToBean(meta, BoxComment.class));
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(Channel.class, pageConditiion, paramMap, Orders.simpleCreateOrder(ORDER.DESC))
				.size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
