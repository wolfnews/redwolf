package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;

/**
 * 用户购物车服务
 * @author dmw
 *
 */
public interface CartService {

	/**添加商品到购物车
	 * @param user 用户Id
	 * @param item 产品Id
	 * @param name 商品名称
	 * @param num  商品数量
	 * @return
	 */
	public Result addItem(Long user,Long item,String name,int num);
	
	/**从购物车删除商品
	 * @param id 购物车Id
	 * @param user 用户ID
	 * @return
	 */
	public Result delItem(Long user,Long id);
	
	/**更新商品数量
	 * @param id
	 * @param num
	 * @return
	 */
	public Result updItem(Long user,Long id,int num);
	
	/**用户购物车商品列表
	 * @param user
	 * @return
	 */
	public GridBean userCarts(Long user);
}
