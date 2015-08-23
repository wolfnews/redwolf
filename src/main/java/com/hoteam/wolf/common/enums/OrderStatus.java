package com.hoteam.wolf.common.enums;

/**
 * 订单状态生命周期，依次分为新创建，已确认，待支付，已支付，已处理和已删除状态。
 * @author dmw
 *
 */
public enum OrderStatus {

	CREATED,//新创建
	CONFIRMED,//已确认
	UNPAID,//待支付
	PAID,//已支付
	DONE,//已处理完账户和订阅信息
	REMOVED;//已删除状态，为后续置位删除留空
}
