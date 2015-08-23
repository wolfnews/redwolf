package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Item;

public interface ItemService {

	/**产品分页接口
	 * @param item
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public GridBean list(Item item,int pageNum,int pageSize);
	/**添加商品
	 * @param item
	 * @return
	 */
	public Result create(Item item);
	/**更新商品
	 * @param item
	 * @return
	 */
	public Result update(Item item);
	/**删除商品
	 * @param id
	 * @return
	 */
	public Result remove(Long id);
	
	/**获取商品详细信息
	 * @param id
	 * @return
	 */
	public Item load(Long id);
	
}
