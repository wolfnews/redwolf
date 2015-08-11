package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.domain.UserSubscribe;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;

@Component("userSubscribeDao")
public class UserSubscribeDao extends BaseDao {
	private static Logger logger = Logger.getLogger(UserSubscribeDao.class);

	public UserSubscribe save(final UserSubscribe userSubscribe) throws Exception {
		userSubscribe.prePersist();
		saveWithPk(userSubscribe);
		return userSubscribe;
	}

	public UserSubscribe update(final UserSubscribe UserSubscribe) {
		UserSubscribe.preUpdate();
		baseSaveUpdate(UserSubscribe);
		return UserSubscribe;
	}

	public void delete(UserSubscribe UserSubscribe) {
		this.baseDelete(UserSubscribe);
	}

	public void delete(Long id) {
		UserSubscribe UserSubscribe = new UserSubscribe();
		UserSubscribe.setId(id);
		this.baseDelete(UserSubscribe);
	}

	public void deleteByProfessor(Long professor) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("professor", professor);
		this.namedParameterJdbcTemplate.update("delete from user_subscribe where professor_id =:professor", param);
	}

	public void deleteByUser(Long user) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user", user);
		this.namedParameterJdbcTemplate.update("delete from user_subscribe where user_id =:user", param);
	}

	public UserSubscribe load(Long professorId, Long userId, Long groupId) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("professor", professorId);
		paramMap.put("user", userId);
		paramMap.put("group", groupId);
		try {
			Map<String, String> conditionMap = new HashMap<String, String>();
			conditionMap.put("PROFESSOR_ID", "professor");
			conditionMap.put("USER_ID", "user");
			conditionMap.put("GROUP_ID", "group");
			return (UserSubscribe) this.baseQueryForEntity(UserSubscribe.class,
					Conditions.complexCondition(conditionMap), paramMap);
		} catch (Exception e) {
			logger.error("Load UserSubscribe by user,group and professor exception:", e);
			return null;
		}
	}

	public boolean userSubsExist(Long groupId, Long professorId, Long userId) {
		Object[] params = { groupId, professorId, userId };
		Integer number = this.jdbcTemplate.queryForObject(
				"select count(1) from user_subscribe where group_id=? and professor_id=? and user_id = ?", params,
				Integer.class);
		return !(null == number || number <= 0);
	}

	public GridBean subsUser(Long professor, int pageNum, int pageSize) throws Exception {
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		paramMap.put("professor", professor);
		String sql = "select user.username as username,user_subscribe.gmt_create as gmt_create,user.id,"
				+ " user_subscribe.expire_time as gmt_modify,user.level as level from user"
				+ " inner join user_subscribe on(user.id=user_subscribe.user_id and professor_id =:professor)";
		List<Map<String, Object>> metalList = PagingUtils.pagingQuery(namedParameterJdbcTemplate, sql, User.class,
				paramMap);
		List<User> list = new ArrayList<User>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				User entity = (User) SQLUtils.coverMapToBean(meta, User.class);
				list.add(entity);
			}
		}
		Object[] param = { professor };
		Long count = this.jdbcTemplate.queryForObject("select count(1) from user_subscribe where professor_id = ?",
				param, Long.class);
		int records = count.intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}

	public List<Professor> subsProfessorList(Long user, int pageNum, int pageSize) throws Exception {
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		paramMap.put("user", user);
		String sql = "select professor.username as username,user_subscribe.gmt_create as gmt_create,professor.id,professor.occupation,"
				+ "user_subscribe.expire_time as gmt_modify,professor.level as level from professor inner join user_subscribe"
				+ " on(user_id =:user and professor.id=user_subscribe.professor_id and user_subscribe.expired = false)";
		List<Map<String, Object>> metalList = PagingUtils.pagingQuery(namedParameterJdbcTemplate, sql, Professor.class,
				paramMap);
		List<Professor> list = new ArrayList<Professor>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				Professor entity = (Professor) SQLUtils.coverMapToBean(meta, Professor.class);
				list.add(entity);
			}
		}
		return list;
	}

	public GridBean subsProfessor(Long user, int pageNum, int pageSize) throws Exception {
		List<Professor> list = this.subsProfessorList(user, pageNum, pageSize);
		Object[] param = { user };
		Long count = this.jdbcTemplate.queryForObject("select count(1) from user_subscribe where user_id = ?", param,
				Long.class);
		int records = count.intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}

	public List<UserSubscribe> loadAll() {
		List<UserSubscribe> list = new ArrayList<UserSubscribe>();
		try {
			List<Map<String, Object>> metaList = this.jdbcTemplate.queryForList("select * from user_subscribe");

			if (null != metaList && !metaList.isEmpty()) {
				for (Map<String, Object> meta : metaList) {
					list.add((UserSubscribe) SQLUtils.coverMapToBean(meta, UserSubscribe.class));
				}
			}
		} catch (DataAccessException e) {
			logger.error("DataAccessException:", e);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return list;
	}
}
