package com.hoteam.wolf.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.RssRecordBean;
import com.hoteam.wolf.dao.UserRssDao;
import com.hoteam.wolf.domain.UserRss;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.service.RssService;

@Service("rssService")
public class RssServiceImpl implements RssService {

//	private static Logger logger = Logger.getLogger(RssServiceImpl.class);
	@Autowired
	private UserRssDao userRssDao;
	
	@Override
	public GridBean pagination(UserRss rss, int pageNum, int pageSize) {
		return null;
	}

	@Override
	public GridBean list(int pageNum, int pageSize) throws Exception {
		String sql = "select user_rss.id as id,user_rss.gmt_create as gmt_create,"
				+ "user_rss.gmt_modify as gmt_modify,user_id,professor_id,user.username as user"
				+ ",professor.username as professor from user_rss left join professor on "
				+ "professor_id = professor.id left join user on user_id = user.id order by gmt_create desc";
		sql = sql + PagingUtils.buildPageSql(pageSize, pageNum);
		List<Map<String, Object>> metalList = userRssDao.getNamedParameterJdbcTemplate().queryForList(sql,
				new HashMap<String, Object>());
		List<RssRecordBean> list = new ArrayList<RssRecordBean>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				RssRecordBean entity = (RssRecordBean) SQLUtils.coverMapToBean(meta, RssRecordBean.class);
				list.add(entity);
			}
		}
		Long total = userRssDao.baseAccountQuery("select count(1) from user_rss", new HashMap<String, Object>());
		int records = total.intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}

}
