package com.hoteam.wolf.scheduler;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.enums.ActivityStatus;
import com.hoteam.wolf.dao.ActivityDao;
import com.hoteam.wolf.domain.Activity;

/**
 * 活动监控任务，用来对任务数据进行有效期的监控。默认每小时执行一次，可以具体配置
 * @author mingwei.dmw
 *
 */
@Component
public class ActivityMonitorTask {

	private static Logger logger = Logger.getLogger(ActivityMonitorTask.class);
	@Autowired
	private ActivityDao activityDao;
	public ActivityMonitorTask() {
		super();
	}

	@Scheduled(cron = "${activity.monitor.cycle:0 0/30 * * * ?}")
	protected void execute() {
		logger.info("start to monitor activity");
		List<Activity> activities = this.activityDao.loadAll();
		for(Activity activity : activities){
			Date nowDate = new Date();
			if(nowDate.after(activity.getEndTime())){
				activity.setValid(false);
				activity.setStatus(ActivityStatus.OVER.toString());
				this.activityDao.update(activity);
			}
		}
		logger.info("monitor activities over!");
	}
}
