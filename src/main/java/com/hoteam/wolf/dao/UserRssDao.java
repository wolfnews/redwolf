package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.domain.UserRss;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;

@Component("userRssDao")
public class UserRssDao extends BaseDao {
	public UserRss save(final UserRss userRss) throws Exception {
		userRss.prePersist();
		saveWithPk(userRss);
		return userRss;
	}

	public UserRss update(final UserRss userRss) {
		userRss.preUpdate();
		baseSaveUpdate(userRss);
		return userRss;
	}

	public void delete(UserRss userRss) {
		this.baseDelete(userRss);
	}

	public void delete(Long id) {
		UserRss userRss = new UserRss();
		userRss.setId(id);
		this.baseDelete(userRss);
	}

	public void deleteByProfessor(Long professor) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("professor", professor);
		this.namedParameterJdbcTemplate.update("delete from user_rss where professor_id =:professor", param);
	}

	public void deleteByUser(Long user) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user", user);
		this.namedParameterJdbcTemplate.update("delete from user_rss where user_id =:user", param);
	}

	public GridBean rssUser(Long professor, int pageNum, int pageSize) throws Exception {
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		paramMap.put("professor", professor);
		String sql = "select user.username as username,user.level as level,user_rss.gmt_create as gmt_create,user.id from user"
				+ " inner join user_rss on(user.id=user_rss.user_id and professor_id =:professor)";
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
		Long count = this.jdbcTemplate.queryForObject("select count(1) from user_rss where professor_id = ?", param,
				Long.class);
		int records = count.intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}

	public List<Professor> rssProfessorList(Long user, int pageNum, int pageSize) throws Exception {
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		paramMap.put("user", user);
		String sql = "select professor.username as username,professor.level as level,user_rss.gmt_create as gmt_create,professor.id,professor.occupation from professor"
				+ " inner join user_rss on(user_id =:user and professor.id=user_rss.professor_id)";
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

	public GridBean rssProfessor(Long user, int pageNum, int pageSize) throws Exception {
		List<Professor> list = this.rssProfessorList(user, pageNum, pageSize);
		Object[] param = { user };
		Long count = this.jdbcTemplate.queryForObject("select count(1) from user_rss where user_id = ?", param,
				Long.class);
		int records = count.intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}

}
