package com.hoteam.wolf.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.dao.ProfessorDao;
import com.hoteam.wolf.dao.UserDao;
import com.hoteam.wolf.dao.UserSubscribeDao;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.domain.UserSubscribe;
import com.hoteam.wolf.utils.DateUtil;
import com.hoteam.wolf.utils.MailClient;

/**
 * 活动监控任务，用来对任务数据进行有效期的监控。默认每小时执行一次，可以具体配置
 * 
 * @author mingwei.dmw
 *
 */
@Component
public class UserSubscribeMonitorTask {

	private static Logger logger = Logger.getLogger(UserSubscribeMonitorTask.class);
	@Autowired
	private UserSubscribeDao userSubscribeDao;
	@Autowired
	private MailClient mailClient;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfessorDao professorDao;

	public UserSubscribeMonitorTask() {
		super();
	}

	@Scheduled(cron = "${subscribe.monitor.cycle:0 0 0/1 * * ?}")
	protected void execute() {
		logger.info("start to monitor user subscribe record");
		List<UserSubscribe> userSubscribes = userSubscribeDao.loadAll();
		for (UserSubscribe us : userSubscribes) {
			Date nowDate = new Date();
			if (nowDate.after(us.getExpireTime())) {
				us.setExpired(true);
				this.userSubscribeDao.update(us);
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_YEAR, 3);
				if (calendar.getTime().after(us.getExpireTime())) {// 该用户的订阅还差三天内到期，给出邮件提醒以及手机短信提醒
					User user = userDao.load(us.getUserId());
					Professor professor;
					try {
						professor = professorDao.load(us.getProfessorId());
					} catch (Exception e) {
						professor = new Professor();
						professor.setUsername("未知讲师");
						logger.error("load professor by id exception:", e);
					}
					notice(user.getEmail(), user.getUsername(), professor.getUsername(),
							DateUtil.getDate(us.getExpireTime()));
				}
			}
		}
		logger.info("monitor user subscribe over!");
	}

	private boolean notice(String target, String username, String professor, String expired) {
		try {
			StringBuffer stringBuffer = new StringBuffer("<html>");
			stringBuffer.append("<body>").append("<h3>尊敬的牛股会会员【" + username + "】您好！</h3>")
					.append("<h4> 您正在牛股会中订阅的讲师【" + professor + "】的宝盒信息将会在【" + expired + "】到期；为了您能够继续查看该讲师制作的其他宝盒")
					.append("，请在登录牛股会个人中心后对该讲师进行续订操作。如果您不慎忘记对该讲师进行续订，在订阅日期之后您将无法查看该讲师的最新宝盒信息。</h4>")
					.append("<b>如您未在牛股会注册任何账户，请忽略该邮件！对您的打扰表示抱歉！如果您对牛股会感兴趣，可以访问如下链接：</b>")
					.append("<a href=\"http://www.niuguhui.org\" >牛股会</a>").append("</body>").append("</html>");
			return mailClient.sendEmail(target, "牛股会账号密码找回", stringBuffer.toString());
		} catch (Exception e) {
			logger.error("Send Email Exception:", e);
			return false;
		}
	}

}
