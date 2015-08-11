package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.AppDownload;

public interface DownLoadService {

	public boolean saveRecord(AppDownload ad);
	
	public GridBean list(AppDownload ad,int pageNum,int pageSize);
}
