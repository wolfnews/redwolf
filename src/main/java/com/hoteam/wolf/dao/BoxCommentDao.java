package com.hoteam.wolf.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.domain.BoxComment;
import com.hoteam.wolf.jdbc.Conditions;

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
			return (BoxComment) this.baseQueryForEntity(BoxComment.class, Conditions.loadCondition, paramMap);
		} catch (Exception e) {
			logger.error("Load BoxComment by id exception:", e);
			return null;
		}
	}

}
