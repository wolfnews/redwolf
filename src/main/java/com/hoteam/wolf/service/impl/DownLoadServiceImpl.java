package com.hoteam.wolf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.dao.AppDownloadDao;
import com.hoteam.wolf.domain.AppDownload;
import com.hoteam.wolf.service.DownLoadService;

@Service("downLoadService")
public class DownLoadServiceImpl implements DownLoadService {

	private static Logger logger = Logger.getLogger(DownLoadServiceImpl.class);
	@Autowired
	private AppDownloadDao appDownloadDao;
	@Override
	public boolean saveRecord(AppDownload ad) {
		try {
			this.appDownloadDao.save(ad);
			return true;
		} catch (Exception e) {
			logger.error("save download record exception:",e);
			return false;
		}
	}

	@Override
	public GridBean list(AppDownload ad, int pageNum, int pageSize) {
		try {
			return this.appDownloadDao.pagination(ad, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("list download exception:",e);
			return new GridBean(0, 0, 0, null);
		}
	}

}
