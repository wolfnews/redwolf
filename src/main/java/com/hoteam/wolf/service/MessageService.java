package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Comment;

public interface MessageService {

	/**保存消息
	 * @param message
	 * @return
	 */
	public boolean saveMessage(Comment message);
	
	/**获取消息详细情况
	 * @param id
	 * @return
	 */
	public Comment load(Long id);
	
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
	public GridBean list(Comment message,int pageNum,int pageSize);
}
