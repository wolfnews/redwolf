package com.hoteam.wolf.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.domain.UserAccount;
import com.hoteam.wolf.jdbc.Conditions;

@Component("userAccountDao")
public class UserAccountDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(UserAccountDao.class);

	public UserAccount save(final UserAccount userAccount) throws Exception {
		userAccount.prePersist();
		saveWithPk(userAccount);
		return userAccount;
	}

	public UserAccount update(final UserAccount userAccount) {
		userAccount.preUpdate();
		baseSaveUpdate(userAccount);
		return userAccount;
	}

	public void delete(UserAccount userAccount) {
		this.baseDelete(userAccount);
	}

	public void delete(Long id) {
		UserAccount userAccount = new UserAccount();
		userAccount.setId(id);
		this.baseDelete(userAccount);
	}

	public void deleteByUser(Long userId) {
		this.jdbcTemplate.execute("delete from user_account where user_id=" + userId);
	}

	public UserAccount load(Long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("userId", userId);
		try {
			return (UserAccount) this.baseQueryForEntity(UserAccount.class,
					Conditions.simpleCondition("USER_ID", "userId"), paramMap);
		} catch (Exception e) {
			logger.error("Load UserAccount by id exception:", e);
			return null;
		}
	}
}
