package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.UserRss;

public interface RssService {

	/**用户关注记录分页
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public GridBean pagination(UserRss urss, int pageNum, int pageSize);
	
	
	public GridBean list(int pageNum, int pageSize) throws Exception;
}
