package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;

public interface UserBoxService {
	/**获取订阅或者关注的宝盒数量
	 * @param userId
	 * @param type
	 * @return
	 */
	public Long getBoxCount(Long userId,String type );
	
	/**关注宝盒分页信息
	 * @param type 宝盒类型
	 * @param userId 用户主键
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return
	 */
	public GridBean boxPagination(String type,Long userId,int pageNum,int pageSize);
}
