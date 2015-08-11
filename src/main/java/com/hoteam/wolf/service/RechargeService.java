package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.RechargeRecord;

public interface RechargeService {

	/**保存充值订单
	 * @param record
	 * @return
	 */
	public RechargeRecord saveRecord(RechargeRecord record);
	
	/**修改重置订单
	 * @param record
	 * @return
	 */
	public RechargeRecord modifyRecord(RechargeRecord record);
	
	/**按照主键删除重置订单
	 * @param recordId
	 * @return
	 */
	public boolean removeRechargeRecord(Long recordId);
	
	/**按照订单编号获取唯一订单
	 * @param sn
	 * @return
	 */
	public RechargeRecord loadRecordBySn(String sn);
	
	/**按照主键获取唯一订单详细信息
	 * @param recordId
	 * @return
	 */
	public RechargeRecord logRecordById(Long recordId);
	
	public GridBean pagination(RechargeRecord querybean,int pageNum,int pageSize);
}
