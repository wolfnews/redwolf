package com.hoteam.wolf.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.utils.SendSMSRequestCache;

/**
 * IP访问清理任务
 * @author dmw
 *
 */
@Component
public class IPCleanTask {

	private static Logger logger = Logger.getLogger(IPCleanTask.class);
	@Autowired
	private SendSMSRequestCache sendSMSRequestCache;
	public IPCleanTask() {
		super();
	}

	@Scheduled(cron = "${ip.clean.cycle:0/30 * * * * ?}")
	protected void execute() {
		logger.info("start to clean send sms ip... ");
		sendSMSRequestCache.clean();
		logger.info("clean send sms over!");
	}
}
