package com.hoteam.wolf.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class PagingUtils {

	public static final String IS_PAGING = "IS_PAGING";
	public static final String PAGE_NUM = "PAGE_NUM";
	public static final String PAGE_SIZE = "PAGE_SIZE";

	public static Map<String, Object> initPage(int pageNum, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(IS_PAGING, true);
		map.put(PAGE_NUM, pageNum);
		map.put(PAGE_SIZE, pageSize);
		return map;
	}

	public static boolean isPagingSearchRequest(Map<String, Object> paramMap) {
		for (String key : paramMap.keySet()) {
			if (key.equals(IS_PAGING) && Boolean.valueOf(paramMap.get(key).toString())) {
				return true;
			}
		}
		return false;
	}

	public static List<Map<String, Object>> pagingQuery(NamedParameterJdbcTemplate jdbcTemplate, String sql,
			Class<?> cs, Map<String, Object> paramMap) throws Exception {
		int pageNum = 0;
		int pageSize = 0;
		String pageNumStr = paramMap.get(PAGE_NUM).toString();
		if (NumberUtils.isNumber(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		String pageSizeStr = paramMap.get(PAGE_SIZE).toString();
		if (NumberUtils.isNumber(pageNumStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		sql = sql + " " + buildPageSql(pageSize, pageNum);
		return jdbcTemplate.queryForList(sql, paramMap);
	}

	public static String buildPageSql(int pageSize, int pageNum) {
		pageSize = 0 >= pageSize ? 1 : pageSize;
		pageNum = 0 >= pageNum ? 10 : pageNum;
		return " LIMIT " + (pageNum - 1) * pageSize + "," + pageSize;
	}
}
