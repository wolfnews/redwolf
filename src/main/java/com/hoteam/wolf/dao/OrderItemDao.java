package com.hoteam.wolf.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.domain.News;
import com.hoteam.wolf.jdbc.Conditions;

/**
 * 文章数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("orderItemDao")
public class OrderItemDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(OrderItemDao.class);

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
			return (News) this.baseQueryForEntity(News.class, Conditions.loadConditiion, paramMap);
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
}
