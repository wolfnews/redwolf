package com.hoteam.wolf.jdbc.utils;

import java.util.HashMap;
import java.util.Map;

public class Orders {

	public static Map<String,ORDER> simpleOrder(String column,ORDER order) {
		Map<String,ORDER> orders = new HashMap<String,ORDER>();
		orders.put(column, order);
		return orders;
	}
}
