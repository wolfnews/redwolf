package com.hoteam.wolf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.dao.BoxDao;
import com.hoteam.wolf.dao.ProfessorDao;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.service.UserBoxService;
import com.hoteam.wolf.service.UserService;

@Service("userBoxService")
public class UserBoxServieImpl implements UserBoxService {

	private static Logger logger = Logger.getLogger(UserBoxServieImpl.class);
	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private UserService userService;
	@Autowired
	private BoxDao boxDao;

	@Override
	public Long getBoxCount(Long userId, String type) {
		String sql = "select count(1) from box where status='NORMAL' ";
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (null == userId || null == type) {
				return this.boxDao.baseCountQuery(sql, null);
			} else if ("rss".equals(type) && null != userId) {
				List<Long> professorIds = this.userService.getRssProfessorIds(userId);
				param.put("ids", professorIds);
				return this.boxDao.baseCountQuery(sql + "AND author in (:ids)", param);
			} else if ("subs".equals(type) && null != userId) {
				List<Long> professorIds = this.userService.getSubProfessorIds(userId);
				param.put("ids", professorIds);
				return this.boxDao.baseCountQuery(sql + "AND author in (:ids)", param);
			} else {
				return this.boxDao.baseCountQuery(sql, null);
			}
		} catch (Exception e) {
			logger.error("get box count exception:", e);
			return 0l;
		}
	}

	@Override
	public GridBean boxPagination(String type, Long userId, int pageNum, int pageSize) {
		String sql = "select * from box where status='NORMAL' AND author in (:ids) order by gmt_create desc "
				+ PagingUtils.buildPageSql(pageSize, pageNum);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			List<Long> professors = null;
			if ("rss".equals(type)) {
				professors = this.userService.getRssProfessorIds(userId);
			} else if ("subs".equals(type)) {
				professors = this.userService.getSubProfessorIds(userId);
			}
			if (null == professors || professors.isEmpty()) {
				return new GridBean(0, 0, 0, null);
			} else {
				paramMap.put("ids", professors);
				List<Map<String, Object>> metaList = this.boxDao.getNamedParameterJdbcTemplate().queryForList(sql,
						paramMap);
				List<Box> boxBeans = this.assembleBoxBean(metaList);
				int records = this.getBoxCount(userId, "rss").intValue();
				int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
				return new GridBean(pageNum, totalPages, records, boxBeans);
			}
		} catch (Exception e) {
			logger.error("get user rss box exception:", e);
			return new GridBean(0, 0, 0, null);
		}
	}

	private List<Box> assembleBoxBean(List<Map<String, Object>> metaList) throws Exception {
		List<Box> boxes = new ArrayList<Box>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				Box entity = (Box) SQLUtils.coverMapToBean(meta, Box.class);
				entity.setPrivateContent(null);
				String content = entity.getPublicContent();
				if (content.length() > 200) {
					entity.setPublicContent(content.substring(0, 200));
				}
				boxes.add(entity);
			}
		}
		return boxes;
	}
}
