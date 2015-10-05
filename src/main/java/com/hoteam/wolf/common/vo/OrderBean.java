package com.hoteam.wolf.common.vo;

import java.util.List;

import com.hoteam.wolf.domain.Order;
import com.hoteam.wolf.domain.OrderItem;

public class OrderBean {

	private Order order;
	private List<OrderItem> items;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public OrderBean(Order order, List<OrderItem> items) {
		super();
		this.order = order;
		this.items = items;
	}

	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderBean [order=" + order + ", items=" + items + "]";
	}

}
