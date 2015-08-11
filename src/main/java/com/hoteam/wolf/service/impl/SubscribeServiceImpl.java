package com.hoteam.wolf.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.SubRecordBean;
import com.hoteam.wolf.dao.ProfessorDao;
import com.hoteam.wolf.dao.SubscribeRecordDao;
import com.hoteam.wolf.dao.UserDao;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.SubscribeRecord;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.service.SubscribeService;

@Service("subscribeService")
public class SubscribeServiceImpl implements SubscribeService {

	private static Logger logger = Logger.getLogger(SubscribeServiceImpl.class);
	@Autowired
	private SubscribeRecordDao subscribeRecordDao;
	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private UserDao userDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public GridBean pagination(SubscribeRecord record, int pageNum, int pageSize) {
		try {
			GridBean gridBean = subscribeRecordDao.pagination(record, pageNum, pageSize);
			List<SubscribeRecord> records = (List<SubscribeRecord>) gridBean.getRows();
			List<SubRecordBean> recordBeans = new ArrayList<SubRecordBean>();
			for(SubscribeRecord subscribeRecord:records){
				SubRecordBean subRecordBean = new SubRecordBean();
				BeanUtils.copyProperties(subscribeRecord, subRecordBean);
				Professor professor = this.professorDao.load(subscribeRecord.getProfessorId());
				subRecordBean.setProfessor(professor.getUsername());
				recordBeans.add(subRecordBean);
			}
			gridBean.setRows(recordBeans);
			return gridBean;
		} catch (Exception e) {
			logger.error("subscribe record pagination exception",e);
			return new GridBean(0, 0, 0, new ArrayList<SubRecordBean>());
		}
	}

	@Override
	public GridBean list(int pageNum, int pageSize) throws Exception {
		String sql = "select subscribe_record.id as id,subscribe_record.gmt_create as gmt_create,"+
					"subscribe_record.gmt_modify as gmt_modify,user_id,professor_id,subscribe_time,"+
					"user.username as user,professor.username as professor from subscribe_record left join user"+
					" on subscribe_record.user_id = user.id left join professor on professor_id=professor.id "+
					"order by subscribe_record.gmt_create desc";
		sql = sql+PagingUtils.buildPageSql(pageSize, pageNum);
		List<Map<String, Object>> metalList = subscribeRecordDao.getNamedParameterJdbcTemplate().queryForList(sql, new HashMap<String,Object>());
		List<SubRecordBean> list = new ArrayList<SubRecordBean>();
		if (null != metalList && !metalList.isEmpty()) {
			for (Map<String, Object> meta : metalList) {
				SubRecordBean entity = (SubRecordBean) SQLUtils.coverMapToBean(meta, SubRecordBean.class);
				list.add(entity);
			}
		}
		Long total = subscribeRecordDao.baseAccountQuery("select count(1) from subscribe_record", new HashMap<String, Object>()); 
		int records = total.intValue();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}

}
