package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.Conditions;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;
import com.hoteam.wolf.jdbc.utils.Orders;

@Component("professorDao")
public class ProfessorDao extends BaseDao {
	private static Logger logger = Logger.getLogger(ProfessorDao.class);

	public Professor save(final Professor professor) throws Exception {
		professor.prePersist();
		saveWithPk(professor);
		return professor;
	}

	public Professor update(final Professor professor) {
		professor.preUpdate();
		baseSaveUpdate(professor);
		return professor;
	}

	public void delete(Professor professor) {
		this.baseDelete(professor);
	}

	public void delete(Long id) {
		Professor professor = new Professor();
		professor.setId(id);
		this.baseDelete(professor);
	}

	public Professor load(String username, String password) {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("username", username);
		paramMap.put("password", password);
		try {
			return (Professor) this.baseQueryForEntity(Professor.class, Conditions.loginCondition, paramMap);
		} catch (Exception e) {
			logger.error("load professor exception:", e);
			return null;
		}
	}

	public Professor load(Long id) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("id", id);
		try {
			return (Professor) this.baseQueryForEntity(Professor.class, Conditions.loadCondition, paramMap);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Professor> loadAll() throws Exception {
		List<Map<String, Object>> metaList = this.jdbcTemplate.queryForList("select * from professor");
		List<Professor> list = new ArrayList<Professor>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((Professor) SQLUtils.coverMapToBean(meta, Professor.class));
			}
		}
		return list;
	}

	public List<Professor> loadList(String username) throws Exception {
		Object[] param = { "%" + username + "%" };
		List<Map<String, Object>> metaList = this.jdbcTemplate.queryForList(
				"select * from professor where username like ?", param);
		List<Professor> list = new ArrayList<Professor>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((Professor) SQLUtils.coverMapToBean(meta, Professor.class));
			}
		}
		return list;
	}

	public GridBean pagination(Professor Professor, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaProfessorList = baseQueryForList(Professor.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<Professor> list = new ArrayList<Professor>();
		if (null != metaProfessorList && !metaProfessorList.isEmpty()) {
			for (Map<String, Object> meta : metaProfessorList) {
				list.add((Professor) SQLUtils.coverMapToBean(meta, Professor.class));
			}
		}
		paramMap.remove(PagingUtils.IS_PAGING);
		int records = listCount(Professor.class, pageConditiion, paramMap).intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}
