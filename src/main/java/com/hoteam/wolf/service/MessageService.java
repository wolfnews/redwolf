package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Message;

public interface MessageService {

	/**保存消息
	 * @param message
	 * @return
	 */
	public boolean saveMessage(Message message);
	
	/**获取消息详细情况
	 * @param id
	 * @return
	 */
	public Message load(Long id);
	
	/**删除消息
	 * @param id
	 * @return
	 */
	public boolean remove(Long id);
	/**消息分页
	 * @param message  查询条件封装类
	 * @param pageNum  页码
	 * @param pageSize 页面大小
	 * @return
	 */
	public GridBean list(Message message,int pageNum,int pageSize);
}
