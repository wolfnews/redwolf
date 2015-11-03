package com.hoteam.wolf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.enums.DeviceType;
import com.hoteam.wolf.domain.PushUser;
import com.hoteam.wolf.jdbc.Conditions;

@Component("pushUserDao")
public class PushUserDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(PushUserDao.class);

	public PushUser save(final PushUser pushUser) throws Exception {
		pushUser.prePersist();
		saveWithPk(pushUser);
		return pushUser;
	}

	public PushUser update(final PushUser pushUser) {
		pushUser.preUpdate();
		baseSaveUpdate(pushUser);
		return pushUser;
	}

	public void delete(PushUser pushUser) {
		this.baseDelete(pushUser);
	}

	public void delete(Long id) {
		PushUser pushUser = new PushUser();
		pushUser.setId(id);
		this.baseDelete(pushUser);
	}

	public void deleteByUser(Long userId) {

	}

	public PushUser load(Long user, DeviceType type) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("user", user);
		paramMap.put("type", type.name());
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("USER_ID", "user");
		conditionMap.put("TYPE", "type");
		try {
			return (PushUser) this.baseQueryForEntity(PushUser.class, Conditions.complexCondition(conditionMap),
					paramMap);
		} catch (Exception e) {
			logger.error("Load PushUser by user exception:", e);
			return null;
		}
	}

	public List<String> loadByProfessor(Long professor,DeviceType type){
		String sql = "select channel_id from push_user left join (select user_rss.user_id from user_rss where professor_id=:professor union select user_id from user_subscribe where user_subscribe.professor_id=:professor) as temp on push_user.type=:type and push_user.user_id = temp.user_id";
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("professor", professor);
		paramMap.put("type", type.name());
		return this.namedParameterJdbcTemplate.queryForList(sql, paramMap, String.class);
	}
}
