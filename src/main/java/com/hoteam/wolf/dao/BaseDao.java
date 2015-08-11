package com.hoteam.wolf.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hoteam.wolf.jdbc.Condition;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.FieldInfo;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;

@Repository
public class BaseDao {
	private static Logger logger = Logger.getLogger(BaseDao.class);
	@Resource
	JdbcTemplate jdbcTemplate;
	@Resource
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Object saveWithPk(Object bean) throws Exception {
		final String sql = SQLUtils.buildInsertSql(bean.getClass());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bean);
		namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
		setPk(bean, keyHolder.getKey().longValue());
		return bean;
	}

	private Object setPk(Object bean, Serializable pk) throws Exception {
		List<FieldInfo> fieldInfos = SQLUtils.loadPojoSqlInfo(bean.getClass());
		if (null == fieldInfos || fieldInfos.isEmpty()) {
			return null;
		}
		for (FieldInfo fieldInfo : fieldInfos) {
			if (fieldInfo.isPk()) {
				String fieldName = fieldInfo.getPojoFieldName();
				String setMethoName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method m = bean.getClass().getMethod(setMethoName, fieldInfo.getType());
				m.invoke(bean, pk);
				break;
			}
		}
		return bean;
	}

	/**
	 * 保存新增的实体对象
	 * 
	 * @param bean
	 * @return
	 */
	public boolean baseSave(Object bean) {
		String sql = SQLUtils.buildInsertSql(bean.getClass());
		SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);
		return this.namedParameterJdbcTemplate.update(sql, sps) > 0 ? true : false;
	}

	/**
	 * 根据主键保存修改的实体对象
	 * 
	 * @param bean
	 * @return
	 */
	public boolean baseSaveUpdate(Object bean) {
		String sql = SQLUtils.buildUpdateSql(bean.getClass());
		SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);
		return this.namedParameterJdbcTemplate.update(sql, sps) > 0 ? true : false;
	}

	/**
	 * 根据bean的部分字段的条件来更新bean的信息
	 * 
	 * @param bean
	 * @param fileds
	 * @return
	 * @throws Exception
	 */
	public boolean baseSaveUpdateWithColumn(Object bean, String[] fileds) throws Exception {
		String sql = SQLUtils.buildUpdateSqlByColumns(bean.getClass(), fileds);
		SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);
		return this.namedParameterJdbcTemplate.update(sql, sps) > 0 ? true : false;
	}

	/**
	 * 根据bean的pk来删除bean
	 * 
	 * @param bean
	 * @return
	 */
	public boolean baseDelete(Object bean) {
		String sql = SQLUtils.buildDeleteSql(bean.getClass());
		SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);
		return this.namedParameterJdbcTemplate.update(sql, sps) > 0 ? true : false;
	}

	/**
	 * 根据bean的部分字段的条件来删除bean
	 * 
	 * @param bean
	 * @param fileds
	 * @return
	 * @throws Exception
	 */
	public boolean baseDeleteWithColumn(Object bean, String[] fileds) throws Exception {
		String sql = SQLUtils.buildDeleteSqlByColumns(bean.getClass(), fileds);
		SqlParameterSource sps = new BeanPropertySqlParameterSource(bean);
		return this.namedParameterJdbcTemplate.update(sql, sps) > 0 ? true : false;
	}

	public Long baseAccountQuery(String sql, Map<String, Object> param) throws Exception {
		return this.namedParameterJdbcTemplate.queryForObject(sql, param, Long.class);
	}

	/**
	 * 自动分页/不分页查询返回list
	 * 
	 * @param cs
	 * @param conditionDef
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> baseQueryForList(Class<?> cs, ConditionDef conditionDef,
			Map<String, Object> paramMap, Map<String, ORDER> orderMap) throws Exception {
		Condition condition = new Condition(conditionDef, paramMap);
		String sql = SQLUtils.buildSelectSql(cs) + condition.getConditionClauseWithWhere();
		logger.debug("mateSQL=" + sql + "param =" + paramMap.toString());
		if (null != orderMap && !orderMap.isEmpty()) {
			StringBuffer sqlBuf = new StringBuffer(sql);
			for (String key : orderMap.keySet()) {
				sqlBuf.append(" ORDER BY ").append(key).append(" ").append(orderMap.get(key).toString());
			}
			sql = sqlBuf.toString();
		}
		if (PagingUtils.isPagingSearchRequest(paramMap)) {
			return PagingUtils.pagingQuery(namedParameterJdbcTemplate, sql, cs, paramMap);
		} else {
			return namedParameterJdbcTemplate.queryForList(sql, paramMap);
		}
	}

	/**
	 * 查询满足条件的单条记录的实体对象，如果超过1条则抛出异常，没查询到则返回null
	 * 
	 * @param cs
	 * @param conditionDef
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Object baseQueryForEntity(Class<?> cs, ConditionDef conditionDef, Map<String, Object> paramMap)
			throws Exception {
		Condition condition = new Condition(conditionDef, paramMap);
		String sql = SQLUtils.buildSelectSql(cs) + condition.getConditionClauseWithWhere();
		List<Map<String, Object>> list = this.namedParameterJdbcTemplate.queryForList(sql, paramMap);
		if (null == list || list.size() == 0 || list.size() > 1) {
			return null;
		} else if (list.size() > 1) {
			throw new Exception("query return record more then one!!");
		} else {
			Map<String, Object> map = (Map<String, Object>) list.get(0);
			return SQLUtils.coverMapToBean(map, cs);
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
}
