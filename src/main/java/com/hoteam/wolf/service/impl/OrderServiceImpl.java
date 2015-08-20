package com.hoteam.wolf.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.dao.OrderDao;
import com.hoteam.wolf.domain.Order;
import com.hoteam.wolf.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public GridBean list(Order order, int pageNum, int pageSize) {
		try {
			return this.orderDao.pagination(order, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("list Order exception:",e);
			return new GridBean(0, 0, 0, new ArrayList<Order>());
		}
	}

}
