package com.hoteam.wolf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.enums.ActivityStatus;
import com.hoteam.wolf.dao.ActivityDao;
import com.hoteam.wolf.domain.Activity;
import com.hoteam.wolf.service.ActivityService;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	private static Logger logger = Logger.getLogger(ActivityServiceImpl.class);
	@Autowired
	private ActivityDao activityDao;
	@Override
	public Activity saveActivity(Activity activity) throws Exception {
		try {
			return this.activityDao.save(activity);
		} catch (Exception e) {
			logger.error("save activity exception:",e);
			throw new Exception("Save Activity DB Exception!");
		}
	}

	@Override
	public Activity modifyActivity(Activity activity) throws Exception {
		try {
			return this.activityDao.update(activity);
		} catch (Exception e) {
			logger.error("Modify activity exception:",e);
			throw new Exception("Modify Activity DB Exception!");
		}
	}

	@Override
	public Activity loadActivity(Long id) throws Exception {
		try {
			return this.activityDao.load(id);
		} catch (Exception e) {
			logger.error("Load activity exception:",e);
			throw new Exception("Load Activity DB Exception!");
		}
	}

	@Override
	public boolean removeActivity(Long id) throws Exception {
		try {
			this.activityDao.delete(id);
			return true;
		} catch (Exception e) {
			logger.error("Remove activity exception:",e);
			throw new Exception("Remove Activity DB Exception!");
		}
	}

	@Override
	public GridBean pagination(Activity activity, int pageNum, int pageSize) throws Exception {
		try {
			return this.activityDao.pagination(activity, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("list activity exception:",e);
			return new GridBean(0, 0, 0, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> hostActivity() throws Exception {
		Activity act = new Activity();
		act.setStatus(ActivityStatus.NORMAL.name());
		GridBean gridBean = this.pagination(act, 1, 100);
		if(null == gridBean.getRows() || gridBean.getRows().isEmpty()){
			return new ArrayList<Activity>();
		}else{
			return (List<Activity>) gridBean.getRows();
		}
	}

}
