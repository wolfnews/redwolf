package com.hoteam.wolf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.dao.ManagerDao;
import com.hoteam.wolf.domain.Manager;
import com.hoteam.wolf.service.ManagerService;

@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {

	private static Logger logger = Logger.getLogger(ManagerServiceImpl.class);
	@Autowired
	private ManagerDao manangerDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Manager addNew(Manager manager) throws Exception {
		try {
			return this.manangerDao.save(manager);
		} catch (Exception e) {
			logger.error("add new manager db excetpion:", e);
			throw new Exception("add new manager db exception");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Manager modify(Manager manager) throws Exception {
		try {
			return this.manangerDao.update(manager);
		} catch (Exception e) {
			logger.error("modify manager db excetpion:", e);
			throw new Exception("modify manager db exception");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean remove(Long managerId) throws Exception {
		try {
			this.manangerDao.delete(managerId);
			return true;
		} catch (Exception e) {
			logger.error("remove manager exception:", e);
			throw e;
		}
	}

	@Override
	public Manager load(Long managerId) throws Exception {
		return this.manangerDao.load(managerId);
	}

	@Override
	public Manager login(String username, String password) throws Exception {
		return this.manangerDao.load(username, password);
	}

	@Override
	public GridBean pagination(Manager manager, int pageNum, int pageSize) throws Exception {
		return this.manangerDao.pagination(manager, pageNum, pageSize);
	}

}
