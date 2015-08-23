package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Order;

public interface OrderService {
 
	
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
