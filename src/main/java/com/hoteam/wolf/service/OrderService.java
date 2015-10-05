package com.hoteam.wolf.service;

import java.util.List;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Carts;
import com.hoteam.wolf.domain.Order;

public interface OrderService {
 
	/**创建订单
	 * @param order 订单基本信息
	 * @param carts 购物车商品列表
	 * @return 创建订单结果
	 */
	public EntityResult create(Order order,List<Carts> carts);
	
	/**订单分页接口
	 * @param order
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public GridBean list(Order order,int pageNum,int pageSize);
	
	/**删除订单
	 * @param id
	 * @return
	 */
	public Result remove(Long id);
	
	/**获取订单详情
	 * @param user 用户ID
	 * @param id 订单ID
	 * @return 
	 */
	public EntityResult load(Long user,Long id);
	
	/**删除订单
	 * @param user
	 * @param id
	 * @return
	 */
	public Result remove(Long user,Long id);
	/**处理订单：主要用于订单完成支付之后对用户的订阅或者账户金币进行处理
	 * @param id
	 * @return
	 */
	public Result process(Long id);
}
