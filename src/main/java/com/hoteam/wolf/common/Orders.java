package com.hoteam.wolf.common;

import java.util.HashMap;
import java.util.Map;

import com.hoteam.wolf.jdbc.utils.ORDER;

public class Orders {

	public static Map<String,ORDER> simpleOrder(String column,ORDER order) {
		Map<String,ORDER> orders = new HashMap<String,ORDER>();
		orders.put(column, order);
		return orders;
	}
	
	public static Map<String,ORDER> simpleCreateOrder(ORDER order) {
		Map<String,ORDER> orders = new HashMap<String,ORDER>();
		orders.put("gmt_create", order);
		return orders;
	}
}
