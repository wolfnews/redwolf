package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.SubscribeRecord;

public interface SubscribeService {

	/**用户订阅记录分页
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public GridBean pagination(SubscribeRecord record, int pageNum, int pageSize);
	
	
	public GridBean list(int pageNum, int pageSize) throws Exception;
}
