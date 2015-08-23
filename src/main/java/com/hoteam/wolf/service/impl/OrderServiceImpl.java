package com.hoteam.wolf.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.dao.OrderDao;
import com.hoteam.wolf.dao.OrderItemDao;
import com.hoteam.wolf.domain.Order;
import com.hoteam.wolf.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public GridBean list(Order order, int pageNum, int pageSize) {
		try {
			return this.orderDao.pagination(order, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("list Order exception:",e);
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
			logger.error("删除订单失败！：",e);
			return new Result(false, "删除订单失败！");
		}
	}

	@Override
	public Result process(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result remove(Long user, Long id) {
		Order order = this.orderDao.load(id);
		if(null == order){
			return new Result(false, "订单不存在!");
		}
		if(order.getUserId().equals(user)){
			return this.remove(id);
		}else{
			return new Result(false, "无效订单！");
		}
	}

}
