package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Order;

public interface OrderService {
 
	
	public GridBean list(Order order,int pageNum,int pageSize);
}
