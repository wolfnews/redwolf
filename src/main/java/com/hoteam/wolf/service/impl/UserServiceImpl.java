package com.hoteam.wolf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.config.SystemConfig;
import com.hoteam.wolf.common.enums.ActivityCategory;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.common.vo.ProfessorStatBean;
import com.hoteam.wolf.common.vo.UserGroupBean;
import com.hoteam.wolf.common.vo.UserProfile;
import com.hoteam.wolf.dao.ActivityDao;
import com.hoteam.wolf.dao.ProfessorDao;
import com.hoteam.wolf.dao.RechargeRecordDao;
import com.hoteam.wolf.dao.SubscribeGroupDao;
import com.hoteam.wolf.dao.SubscribeRecordDao;
import com.hoteam.wolf.dao.UserAccountDao;
import com.hoteam.wolf.dao.UserDao;
import com.hoteam.wolf.dao.UserRssDao;
import com.hoteam.wolf.dao.UserSubscribeDao;
import com.hoteam.wolf.domain.Activity;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.RechargeRecord;
import com.hoteam.wolf.domain.SubscribeGroup;
import com.hoteam.wolf.domain.SubscribeRecord;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.domain.UserAccount;
import com.hoteam.wolf.domain.UserRss;
import com.hoteam.wolf.domain.UserSubscribe;
import com.hoteam.wolf.service.ProfessorService;
import com.hoteam.wolf.service.UserService;
import com.hoteam.wolf.utils.DESUtil;
import com.hoteam.wolf.utils.MailClient;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private MailClient mailClient;
	@Autowired
	private SystemConfig systemConfig;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRssDao userRssDao;
	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserSubscribeDao userSubscribeDao;
	@Autowired
	private RechargeRecordDao rechargeRecordDao;
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private SubscribeGroupDao subscribeGroupDao;
	@Autowired
	private SubscribeRecordDao subscribeRecordDao;
	@Autowired
	private ProfessorService professorService;

	@Override
	public EntityResult addUser(User user) throws Exception {
		logger.info("start to add user:" + user.toString());
		try {
			if (!user.getUsername().isEmpty()) {
				if (this.userDao.nameExist(user.getUsername())) {
					logger.warn("username:" + user.getUsername() + "exist!");
					throw new Exception("用户名已存在!");
				}
			}
			if (!user.getMobile().isEmpty()) {
				if (this.userDao.mobileExist(user.getMobile())) {
					logger.warn("mobile:" + user.getMobile() + "exist!");
					throw new Exception("手机号码已存在!");
				}
			}
			if (!user.getEmail().isEmpty()) {
				if (this.userDao.emailExist(user.getEmail())) {
					logger.warn("email:" + user.getEmail() + "exist!");
					throw new Exception("邮箱地址已存在!");
				}
			}
			if (!user.isIdentified()) {
				sendEmail(user.getEmail(), user.getUsername());
			}
			user = this.userDao.save(user);
			logger.info("save User Success!");
			Activity activity = this.activityDao.loadByCateogry(ActivityCategory.REGISTER);
			long grade = 0l;
			if (null != activity) {
				logger.info("find register acvtivity");
				grade = activity.getRate().longValue();
			}
			UserAccount userAccount = new UserAccount(user.getId(), grade);
			this.userAccountDao.save(userAccount);
			logger.info("save user account success!");
			return new EntityResult(true, "register success", user);
		} catch (Exception e) {
			logger.error("Create User Exception:", e);
			throw e;
		}
	}

	@Override
	public EntityResult login(String mobile, String username, String password) throws Exception {
		User user = this.userDao.load(username, password);
		if (null == user) {
			return new EntityResult(false, "用户名或密码错误！", null);
		} else if (user.isIdentified()) {
			return new EntityResult(true, "SUCCESS", user);
		} else {
			return new EntityResult(false, "用户未注册确认，请查看注册邮箱中的确认邮件并确认！", null);
		}
	}

	@Override
	public User modifyUser(User user) throws Exception {
		try {
			user = this.userDao.update(user);
			logger.info("Modify User Success!");
			return user;
		} catch (Exception e) {
			logger.error("Modify User Exception:", e);
			throw e;
		}
	}

	@Override
	public void removeUser(Long id) throws Exception {
		try {
			this.userDao.delete(id);
			this.userAccountDao.deleteByUser(id);
			this.rechargeRecordDao.deleteByUser(id);
			this.userRssDao.deleteByUser(id);
			this.userSubscribeDao.deleteByUser(id);
		} catch (Exception e) {
			logger.error("remove user exception:", e);
			throw e;
		}
	}

	@Override
	public GridBean pagination(User user, int pageNum, int pageSize) throws Exception {
		try {
			return this.userDao.pagination(user, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("Get User List Excception:", e);
			throw e;
		}
	}

	@Override
	public User load(Long userId) throws Exception {
		try {
			return this.userDao.load(userId);
		} catch (Exception e) {
			logger.error("load user info exception:", e);
			throw e;
		}
	}

	@Override
	public Result rssProfessor(Long userId, Long professorId) throws Exception {
		try {
			UserRss userRss = new UserRss(userId, professorId);
			this.userRssDao.save(userRss);
			logger.info("user[" + userId + "] rss professor[" + professorId + "]success!");
			return new Result(true, "rss professor:" + professorId + "success");
		} catch (Exception e) {
			logger.error("rss professor exception:", e);
			throw e;
		}
	}

	@Override
	public Result subscribeProfessor(Long userId, Long professorId, Long groupId, int time) throws Exception {
		UserAccount userAccount = this.userAccountDao.load(userId);
		if (null == userAccount) {
			logger.warn("user has no account");
			return new Result(false, "NO_ACCOUNT");
		}
		if (time <= 0) {
			logger.warn("subscribe time less than 1 day");
			return new Result(false, "LESS_ONE_DAY");
		}
		SubscribeGroup group = this.subscribeGroupDao.load(groupId);
		int grade = time * group.getPrice();
		// 更新用户积分
		Long coin = userAccount.getCoin();
		if (coin.intValue() < grade) {
			return new Result(false, "NO_MORE_GRADE");// 用户积分余额不足
		} else {
			userAccount.setCoin(coin - grade);
			this.userAccountDao.update(userAccount);
		}
		UserSubscribe userSubscribe = null;
		boolean userSubsExist = this.userSubscribeDao.userSubsExist(groupId, professorId, userId);
		if (userSubsExist) {
			logger.debug("the user already subs the professor");
			userSubscribe = this.userSubscribeDao.load(professorId, userId, groupId);
			Date expireTime = userSubscribe.getExpireTime();
			if (null == expireTime || userSubscribe.isExpired()) {// 没有超时时间或者已经超时的，起始时间从当前时间开始计算
				expireTime = new Date();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(expireTime);
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + time);
			userSubscribe.setExpireTime(calendar.getTime());
			if (userSubscribe.isExpired()) {
				userSubscribe.setExpired(false);
			}
			this.userSubscribeDao.update(userSubscribe);
		} else {
			logger.info("the user fisrt subs the professor");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + time);
			userSubscribe = new UserSubscribe(false, calendar.getTime(), userId, professorId, groupId);
			this.userSubscribeDao.save(userSubscribe);
		}
		SubscribeRecord subscribeRecord = new SubscribeRecord(userId, professorId, time);
		this.subscribeRecordDao.save(subscribeRecord);
		logger.info("user[" + userId + "] subscribe professor[" + professorId + "] for [" + time + "] days success!");
		return new Result(true, "SUCCESS");
	}

	@Override
	public User load(String username,String password) throws Exception {
		return this.userDao.load(username, password);
	}

	@Override
	public Result recharge(Long userId, int money, String category) throws Exception {
		if (0 >= money) {
			throw new Exception("NO_MONEY");
		}
		try {
			UserAccount account = userAccountDao.load(userId);
			Activity activity = this.activityDao.loadByCateogry(ActivityCategory.RECHARGE);
			long grade = 1l;
			Long activityId = null;
			if (null != activity) {
				logger.info("find register acvtivity");
				grade = activity.getRate().multiply(new BigDecimal(money)).longValue();
				activityId = activity.getId();
			} else {
				grade = money;
			}
			// TODO 连接支付宝支付
			account.setCoin(account.getCoin() + grade);
			this.userAccountDao.update(account);
			RechargeRecord rechargeRecord = new RechargeRecord();
			rechargeRecordDao.save(rechargeRecord);
			return new Result(true, "充值成功！");
		} catch (Exception e) {
			logger.error("User recharge exception:", e);
			throw new Exception("User Recharge Exception");
		}
	}

	@Override
	public UserProfile profile(Long userId) throws Exception {
		UserProfile profile = null;
		User user = this.load(userId);
		if (null != user) {
			UserAccount account = this.userAccountDao.load(user.getId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("user", userId);
			Long rssAccount = this.userDao.baseCountQuery("select count(1) from user_rss where user_id = :user",
					param);
			Long subAccount = this.userDao.baseCountQuery(
					"select count(1) from user_subscribe where  user_id = :user", param);
			if (null == rssAccount) {
				rssAccount = 0l;
			}
			if (null == subAccount) {
				subAccount = 0l;
			}
			profile = new UserProfile("" + user.getLevel(), user.getUsername(), account.getCoin(), rssAccount,
					subAccount);
		} else {
			profile = new UserProfile("", "", 0l, 0l, 0l);
		}
		return profile;
	}

	@Override
	public List<Long> getRssProfessorIds(Long userId) throws Exception {// 这里需要优化，减少访问db的次数
		List<Professor> professors = this.userRssDao.rssProfessorList(userId, 1, Integer.MAX_VALUE);
		List<Long> professorIds = new ArrayList<Long>();
		for (Professor professor : professors) {
			professorIds.add(professor.getId());
		}
		return professorIds;
	}

	@Override
	public List<Long> getSubProfessorIds(Long userId) throws Exception {// 这里需要优化，减少访问db的次数
		List<Professor> professors = this.userSubscribeDao.subsProfessorList(userId, 1, Integer.MAX_VALUE);
		List<Long> professorIds = new ArrayList<Long>();

		for (Professor professor : professors) {
			professorIds.add(professor.getId());
		}
		return professorIds;
	}

	private boolean sendEmail(String target, String username) {
		try {
			String cipher = DESUtil.encrypt(username, systemConfig.getAuthKey());
			String address = systemConfig.getHost() + "/profile/user/identify.html?identify_key=" + cipher;
			StringBuffer stringBuffer = new StringBuffer("<html>");
			stringBuffer.append("<body>")
					.append("<h1> 欢迎注册牛股汇账号，请点击下面链接进行注册确认，如浏览器不支持自动跳转，请把下面链接复制粘贴到浏览器的地址栏直接访问即可</h1>")
					.append("<a href=\"").append(address).append("\">").append(address).append("</a>")
					.append("</body>").append("</html>");
			return mailClient.sendEmail(target, "牛股汇注册确认邮件", stringBuffer.toString());
		} catch (Exception e) {
			logger.error("Send Email Exception:", e);
			return false;
		}
	}

	@Override
	public Result identify(String usercliper) throws Exception {
		String username = DESUtil.decrypt(usercliper, systemConfig.getAuthKey());
		User user = this.userDao.loadByName(username);
		if (null == user) {
			logger.warn("No User:" + username);
			return new Result(false, "NO_USER");
		}
		user.setIdentified(true);
		this.userDao.update(user);
		return new Result(true, "SUCCESS");
	}

	@Override
	public List<ProfessorBean> getRssProfessors(Long userId) {
		List<ProfessorBean> professorBeans = new ArrayList<ProfessorBean>();
		List<Long> professorIds = new ArrayList<Long>();
		try {
			professorIds = this.getRssProfessorIds(userId);
			if (professorIds.isEmpty()) {
				return professorBeans;
			}
		} catch (Exception e1) {
			return professorBeans;
		}
		try {
			List<Professor> professors = this.professorDao.loadAll();
			professorBeans = this.assembleProfessor(professorIds, professors);
		} catch (Exception e) {
			logger.error("get User rss professor exception:", e);
		}
		return professorBeans;

	}

	@Override
	public List<ProfessorBean> getSubProfessors(Long userId) {
		List<ProfessorBean> professorBeans = new ArrayList<ProfessorBean>();
		List<Long> professorIds = new ArrayList<Long>();
		try {
			professorIds = this.getSubProfessorIds(userId);
			if (professorIds.isEmpty()) {
				return professorBeans;
			}
		} catch (Exception e1) {
			return professorBeans;
		}
		try {
			List<Professor> professors = this.professorDao.loadAll();
			professorBeans = this.assembleProfessor(professorIds, professors);
			for(ProfessorBean professorBean:professorBeans){
				SubscribeGroup group = new SubscribeGroup();
				group.setProfessorId(professorBean.getId());
				List<SubscribeGroup> groups = this.subscribeGroupDao.queryForList(group);
				List<UserGroupBean> userGroupBeans = new ArrayList<UserGroupBean>();
				for(SubscribeGroup subscribeGroup:groups){
					UserSubscribe userSubscribe = userSubscribeDao.load(professorBean.getId(), userId, subscribeGroup.getId());
					UserGroupBean userGroupBean = new UserGroupBean(userId, subscribeGroup.getId(), subscribeGroup.getName(), userSubscribe.getExpireTime());
					userGroupBeans.add(userGroupBean);
				}
				professorBean.setGroups(userGroupBeans);
			}
		} catch (Exception e) {
			logger.error("get User rss professor exception:", e);
		}
		return professorBeans;
	}

	private List<ProfessorBean> assembleProfessor(List<Long> professorIds, List<Professor> professors) throws Exception {
		List<ProfessorBean> professorBeans = new ArrayList<ProfessorBean>();
		for (Professor professor : professors) {
			if (professorIds.contains(professor.getId())) {
				ProfessorBean professorBean = new ProfessorBean();
				BeanUtils.copyProperties(professor, professorBean);
				ProfessorStatBean statis = this.professorService.statisProfessor(professor.getId());
				professorBean.setRssCount(statis.getUserRssAccount());
				professorBean.setSubCount(statis.getUserSubscribeAccount());
				professorBean.setBoxCount(statis.getNoticeAccount());
				SubscribeGroup group = new SubscribeGroup();
				group.setProfessorId(professor.getId());
				List<SubscribeGroup> groups = this.subscribeGroupDao.queryForList(group);
				List<Integer> prices = new ArrayList<Integer>();
				for (SubscribeGroup sg : groups) {
					prices.add(sg.getPrice());
				}
				professorBean.setPrices(prices);
				professorBeans.add(professorBean);
			}
		}
		return professorBeans;
	}

	@Override
	public Result findUserPassword(String email) {
		User user = this.userDao.loadByEmail(email);
		if (null == user) {
			return new Result(false, "该邮箱尚未被注册");
		} else {
			boolean sendEmail = sendPasswordEmail(user.getEmail(), user.getUsername(), user.getPassword());
			if (sendEmail) {
				return new Result(true, "系统给您发送了一封邮件，请登录邮件查看");
			} else {
				return new Result(false, "系统繁忙，请稍后再试");
			}
		}
	}
	private boolean sendPasswordEmail(String target, String username, String password) {
		try {
			StringBuffer stringBuffer = new StringBuffer("<html>");
			stringBuffer.append("<body>")
					.append("<h4> 您正在牛股会中注册的用户名【" + username + "】的登录密码为：【" + password + "】；为了您的账号安全，请在登录后修改登录密码！</h4>")
					.append("<b>如您未在牛股会注册任何账户，请忽略该邮件！对您的打扰表示抱歉！如果您对牛股会感兴趣，可以访问如下链接：</b>")
					.append("<a href=\"http://www.niuguhui.org\" >牛股会</a>").append("</body>").append("</html>");
			return mailClient.sendEmail(target, "牛股会账号密码找回", stringBuffer.toString());
		} catch (Exception e) {
			logger.error("Send Email Exception:", e);
			return false;
		}
	}

	@Override
	public Result exist(String category, String content) {
		Map<String,Object> param = new HashMap<String,Object>();
		String sql = "select count(1) from user where ";
		Long count =null;
		try {
			if("mobile".endsWith(category)){
				param.put("mobile", content);
				sql += "mobile=:mobile";
				count = this.userDao.baseCountQuery(sql, param);
			}else if("username".equals(category)){
				param.clear();
				param.put("username", content);
				sql += "username=:username";
				count = this.userDao.baseCountQuery(sql, param);
			}else if("email".equals(category)){
				param.clear();
				param.put("email", content);
				sql += "email=:email";
				count = this.userDao.baseCountQuery(sql, param);
			}
		} catch (Exception e) {
			logger.error("user info exist exception:",e);
			count =  null;
		}
		if(null != count && count >0){
			return new Result(true, "exist");
		}else{
			return new Result(false, "not exist");
		}
	}
}
