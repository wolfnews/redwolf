package com.hoteam.wolf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.enums.ItemCategory;
import com.hoteam.wolf.common.vo.OrderBean;
import com.hoteam.wolf.dao.CartDao;
import com.hoteam.wolf.dao.ItemDao;
import com.hoteam.wolf.dao.OrderDao;
import com.hoteam.wolf.dao.OrderItemDao;
import com.hoteam.wolf.dao.SubscribeGroupDao;
import com.hoteam.wolf.dao.SubscribeRecordDao;
import com.hoteam.wolf.dao.UserAccountDao;
import com.hoteam.wolf.dao.UserSubscribeDao;
import com.hoteam.wolf.domain.Carts;
import com.hoteam.wolf.domain.Item;
import com.hoteam.wolf.domain.Order;
import com.hoteam.wolf.domain.OrderItem;
import com.hoteam.wolf.domain.SubscribeGroup;
import com.hoteam.wolf.domain.SubscribeRecord;
import com.hoteam.wolf.domain.UserAccount;
import com.hoteam.wolf.domain.UserSubscribe;
import com.hoteam.wolf.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserSubscribeDao userSubscribeDao;
	@Autowired
	private SubscribeGroupDao subscribeGroupDao;
	@Autowired
	private SubscribeRecordDao subscribeRecordDao;

	@Override
	public GridBean list(Order order, int pageNum, int pageSize) {
		try {
			return this.orderDao.pagination(order, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("list Order exception:", e);
			return new GridBean(0, 0, 0, new ArrayList<Order>());
		}
	}

	@Override
	public Result remove(Long id) {
		try {
			this.orderItemDao.deleteByOrder(id);
			this.orderDao.delete(id);
			return new Result(true, "删除订单成功！");
		} catch (Exception e) {
			logger.error("删除订单失败！：", e);
			return new Result(false, "删除订单失败！");
		}
	}

	@Override
	public Result process(Long id) {
		Order order = orderDao.load(id);
		if (null == order) {
			return new Result(false, "订单不存在");
		}
		try {
			Long user = order.getUserId();
			List<OrderItem> items = orderItemDao.loadByOrder(order.getId());
			if (null == items || items.isEmpty()) {
				logger.warn("订单下没有商品");
				return new Result(true, "订单下没有商品");
			} else {
				for (OrderItem orderItem : items) {// 目前的处理逻辑是按照当前的商品价值进行处理。
					// 存在的问题：如果用户在下单和支付完成期间，商品的实际价值发生变化，如充值卡优惠额度增大，
					// 服务卡实际时间变长，那么将会按照当前的实际商品价值进行处理，而不是下单时的商品价值。该
					// 处理逻辑后续再进行修改
					// TODO
					Item item = itemDao.load(orderItem.getItemId());
					if (null == item) {
						logger.warn("商品ID【" + orderItem.getItemId() + "】" + orderItem.getName() + "不存在");
						continue;
					}
					if (ItemCategory.CREDITCARD.name().equalsIgnoreCase(item.getCategory())) {
						UserAccount userAccount = this.userAccountDao.load(user);
						long finalCoins = userAccount.getCoin() + item.getValue();
						userAccount.setCoin(finalCoins);
						userAccountDao.update(userAccount);
						logger.info("用户充值完毕");
					} else if (ItemCategory.SERVICEBAG.name().equalsIgnoreCase(item.getCategory())) {
						SubscribeGroup group = new SubscribeGroup();
						group.setProfessorId(item.getExtend());
						List<SubscribeGroup> groups = subscribeGroupDao.queryForList(group);
						if (null == groups || groups.isEmpty()) {
							logger.warn("讲师没有订阅分组");
							continue;
						}
						Long groupId = groups.get(0).getId();
						boolean userSubsExist = this.userSubscribeDao.userSubsExist(groupId, item.getExtend(), user);
						if (userSubsExist) {// 当前用户曾经订阅过该讲师
							logger.info("当前用户曾订阅过该讲师");
							UserSubscribe userSubscribe = this.userSubscribeDao.load(item.getExtend(), user, groupId);
							Date expireTime = userSubscribe.getExpireTime();
							if (null == expireTime || userSubscribe.isExpired()) {// 没有超时时间或者已经超时的，起始时间从当前时间开始计算
								expireTime = new Date();
							}
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(expireTime);
							calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + item.getValue());
							userSubscribe.setExpireTime(calendar.getTime());
							if (userSubscribe.isExpired()) {
								userSubscribe.setExpired(false);
							}
							this.userSubscribeDao.update(userSubscribe);
						} else {
							logger.info("the user fisrt subs the professor");
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(new Date());
							calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + item.getValue());
							UserSubscribe userSubscribe = new UserSubscribe(false, calendar.getTime(), user,
									item.getExtend(), groupId);
							this.userSubscribeDao.save(userSubscribe);
						}
						SubscribeRecord subscribeRecord = new SubscribeRecord(user, item.getExtend(), item.getValue());
						this.subscribeRecordDao.save(subscribeRecord);

					} else {
						logger.warn("未知商品类型！暂时不处理！");
						continue;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result remove(Long user, Long id) {
		Order order = this.orderDao.load(id);
		if (null == order) {
			return new Result(false, "订单不存在!");
		}
		if (order.getUserId().equals(user)) {
			return this.remove(id);
		} else {
			return new Result(false, "无效订单！");
		}
	}

	@Override
	public EntityResult create(Order order, List<Carts> carts) {
		try {
			order = this.orderDao.save(order);
			Long orderId = order.getId();
			List<OrderItem> items = new ArrayList<OrderItem>();
			List<Long> cartIds = new ArrayList<Long>();
			BigDecimal total = new BigDecimal(0);
			for (Carts cart : carts) {
				cartIds.add(cart.getId());
				Item item = this.itemDao.load(cart.getItemId());
				boolean hasProf = item.getValue() > item.getPrice().intValue();
				Integer profVal = item.getValue() - item.getPrice().intValue();
				OrderItem orderItem = new OrderItem(orderId, item.getId(), cart.getNum(), item.getPrice(), hasProf,
						profVal);
				total = total.add(item.getPrice().multiply(new BigDecimal(cart.getNum())));
				orderItemDao.save(orderItem);
				items.add(orderItem);
			}
			order.setTotal(total);
			this.orderDao.update(order);
			this.cartDao.deleteAll(cartIds);
			return new EntityResult(true, "创建订单成功", new OrderBean(order, items));
		} catch (Exception e) {
			logger.error("create order exception:", e);
			return new EntityResult(false, "创建订单失败", null);
		}
	}

	@Override
	public EntityResult load(Long user, Long id) {
		Order order = this.orderDao.load(id);
		if (null == order) {
			return new EntityResult(false, "订单不存在", null);
		}
		if (order.getUserId().equals(user)) {
			List<OrderItem> items = new ArrayList<OrderItem>();
			try {
				items = this.orderItemDao.loadByOrder(id);
			} catch (Exception e) {
				logger.error("load item exception", e);
			}
			if (null == items || items.isEmpty()) {
				return new EntityResult(false, "无效订单", null);
			} else {
				return new EntityResult(true, "正常订单", new OrderBean(order, items));
			}
		} else {
			return new EntityResult(false, "无效订单！", null);
		}
	}

}
