package com.hoteam.wolf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.dao.RechargeRecordDao;
import com.hoteam.wolf.domain.RechargeRecord;
import com.hoteam.wolf.service.RechargeService;

@Service("rechargeService")
public class RechargeServiceImpl implements RechargeService {

	private static Logger logger = Logger.getLogger(RechargeServiceImpl.class);
	@Autowired
	private RechargeRecordDao rechargeRecordDao;
	@Override
	public RechargeRecord saveRecord(RechargeRecord record) {
		try {
			RechargeRecord oldRecord = rechargeRecordDao.loadBySn(record.getSn());
			if(null==oldRecord){
				return this.rechargeRecordDao.save(record);
			}else{
				return oldRecord;
			}
		} catch (Exception e) {
			logger.error("Save RechargeRechrd Exception:",e);
			return null;
		}
	}

	@Override
	public RechargeRecord modifyRecord(RechargeRecord record) {
		try {
			return this.rechargeRecordDao.update(record);
		} catch (Exception e) {
			logger.error("Modify RechargeRechrd Exception:",e);
			return null;
		}
	}

	@Override
	public boolean removeRechargeRecord(Long recordId) {
		try {
			this.rechargeRecordDao.delete(recordId);
			return true;
		} catch (Exception e) {
			logger.error("Remove RechargeRechrd Exception:",e);
			return false;
		}
	}

	@Override
	public RechargeRecord loadRecordBySn(String sn) {
		return this.rechargeRecordDao.loadBySn(sn);
	}

	@Override
	public RechargeRecord logRecordById(Long recordId) {
		return this.rechargeRecordDao.load(recordId);
	}

	@Override
	public GridBean pagination(RechargeRecord querybean, int pageNum, int pageSize) {
		try {
			return this.rechargeRecordDao.pagination(querybean, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("recharge record pagination exception:",e);
			return new GridBean(0, 0, 0, null);
		}
	}

}
