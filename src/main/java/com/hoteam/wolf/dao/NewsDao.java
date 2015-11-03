package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.News;
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
@Component("newsDao")
public class NewsDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(NewsDao.class);

	public News save(final News news) throws Exception {
		news.prePersist();
		saveWithPk(news);
		return news;
	}

	public News update(final News news) {
		news.preUpdate();
		baseSaveUpdate(news);
		return news;
	}

	public News load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (News) this.baseQueryForEntity(News.class, Conditions.loadCondition, paramMap);
		} catch (Exception e) {
			logger.error("Load news by id exception:", e);
			return null;
		}
	}

	public void delete(News news) {
		this.baseDelete(news);
	}

	public void delete(Long id) {
		News news = new News();
		news.setId(id);
		this.baseDelete(news);
	}

	public GridBean pagination(News news, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != news) {
			if (null != news.getCategory() && !news.getCategory().isEmpty()) {
				Object[] item = { "CATEGORY = :category" };
				conditionMetaList.add(item);
				paramMap.put("category", news.getCategory());
			}

		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(News.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<News> list = new ArrayList<News>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				News newsBean = (News) SQLUtils.coverMapToBean(meta, News.class);
				newsBean.setContent(null);
				list.add(newsBean);
			}
		}
		paramMap.remove(PagingUtils.IS_PAGING);
		int records = listCount(News.class, pageConditiion, paramMap).intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
