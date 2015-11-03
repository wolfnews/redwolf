package com.hoteam.wolf.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.domain.Recommend;
import com.hoteam.wolf.jdbc.Conditions;

/**
 * 推荐码数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("recommendDao")
public class RecommendDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(RecommendDao.class);

	public Recommend save(final Recommend recommend) throws Exception {
		recommend.prePersist();
		saveWithPk(recommend);
		return recommend;
	}

	public Recommend update(final Recommend recommend) {
		recommend.preUpdate();
		baseSaveUpdate(recommend);
		return recommend;
	}

	public Recommend load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Recommend) this.baseQueryForEntity(Recommend.class, Conditions.loadCondition, paramMap);
		} catch (Exception e) {
			logger.error("Load UserRecommend by id exception:", e);
			return null;
		}
	}

	public void delete(Long id) {
		Recommend recommend = new Recommend();
		recommend.setId(id);
		this.baseDelete(recommend);
	}
}
