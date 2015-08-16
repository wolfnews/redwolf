package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

@Component("userDao")
public class UserDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public User save(final User User) throws Exception {
		User.prePersist();
		saveWithPk(User);
		return User;
	}

	public User update(final User User) {
		User.preUpdate();
		baseSaveUpdate(User);
		return User;
	}

	public void delete(User User) {
		this.baseDelete(User);
	}

	public void delete(Long id) {
		User User = new User();
		User.setId(id);
		this.baseDelete(User);
	}

	public User load(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (User) this.baseQueryForEntity(User.class, Conditions.loadConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load User by id exception:", e);
			return null;
		}
	}

	public boolean mobileExist(String mobile) {
		Object[] param = { mobile };
		Integer number = this.jdbcTemplate.queryForObject("select count(1) from user where mobile = ?", param,
				Integer.class);
		return !(null == number || 0 >= number);
	}
	public boolean emailExist(String email) {
		Object[] param = { email };
		Integer number = this.jdbcTemplate.queryForObject("select count(1) from user where email = ?", param,
				Integer.class);
		return !(null == number || 0 >= number);
	}
	public boolean nameExist(String username) {
		Object[] param = { username };
		Integer number = this.jdbcTemplate.queryForObject("select count(1) from user where username = ?", param,
				Integer.class);
		return !(null == number || 0 >= number);
	}

	public User load(String mobile) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("mobile", mobile);
		try {
			return (User) this.baseQueryForEntity(User.class, Conditions.loginMConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load user by mobile exception:", e);
			return null;
		}
	}

	public User loadByName(String username) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("username", username);
		try {
			return (User) this.baseQueryForEntity(User.class, Conditions.simpleCondition("USERNAME", "username"),
					paramMap);
		} catch (Exception e) {
			logger.error("Load user by name exception:", e);
			return null;
		}
	}

	public User loadByEmail(String email) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("email", email);
		try {
			return (User) this.baseQueryForEntity(User.class, Conditions.simpleCondition("EMAIL", "email"),
					paramMap);
		} catch (Exception e) {
			logger.error("Load user by email exception:", e);
			return null;
		}
	}
	public User load(String username, String password) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("username", username);
		paramMap.put("password", password);
		try {
			return (User) this.baseQueryForEntity(User.class, Conditions.loginConditiion, paramMap);
		} catch (Exception e) {
			logger.error("Load user by id exception:", e);
			return null;
		}
	}

	public GridBean pagination(User user, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		if (null != user) {
			if (null != user.getUsername() && !user.getUsername().isEmpty()) {
				Object[] item = { "USERNAME = :name" };
				conditionMetaList.add(item);
				paramMap.put("name", user.getUsername());
			}

		}
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(User.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<User> list = new ArrayList<User>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((User) SQLUtils.coverMapToBean(meta, User.class));
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(User.class, pageConditiion, paramMap, Orders.simpleCreateOrder(ORDER.DESC))
				.size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
