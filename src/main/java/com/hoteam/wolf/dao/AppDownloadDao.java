package com.hoteam.wolf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Orders;
import com.hoteam.wolf.domain.AppDownload;
import com.hoteam.wolf.jdbc.ConditionDef;
import com.hoteam.wolf.jdbc.PagingUtils;
import com.hoteam.wolf.jdbc.SQLUtils;
import com.hoteam.wolf.jdbc.utils.ORDER;

/**
 * 活动数据处理层
 * 
 * @author mingwei.dmw
 *
 */
@Component("appDownloadDao")
public class AppDownloadDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(AppDownloadDao.class);

	public AppDownload save(final AppDownload appDownload) throws Exception {
		appDownload.prePersist();
		saveWithPk(appDownload);
		return appDownload;
	}


	public void delete(AppDownload appDownload) {
		this.baseDelete(appDownload);
	}

	public void delete(Long id) {
		AppDownload appDownload = new AppDownload();
		appDownload.setId(id);
		this.baseDelete(appDownload);
	}

	public GridBean pagination(AppDownload appDownload, int pageNum, int pageSize) throws Exception {
		List<Object[]> conditionMetaList = new ArrayList<Object[]>();
		Map<String, Object> paramMap = PagingUtils.initPage(pageNum, pageSize);
		Object[][] conMetaArray = new Object[conditionMetaList.size()][];
		for (int i = 0; i < conditionMetaList.size(); i++) {
			conMetaArray[i] = conditionMetaList.get(i);
		}
		if (null != appDownload) {
			String category = appDownload.getAppCategory();
			String version = appDownload.getAppVersion();
			if (null != category && !category.isEmpty()) {
				Object[] item = { "APP_CATEGORY = :category" };
				conditionMetaList.add(item);
				paramMap.put("category", category);
			}
			if (null != version && !version.isEmpty()) {
				Object[] item = { "APP_VERSION = :version" };
				conditionMetaList.add(item);
				paramMap.put("version", version);
			}
		}
		ConditionDef pageConditiion = new ConditionDef(conMetaArray);
		List<Map<String, Object>> metaList = baseQueryForList(AppDownload.class, pageConditiion, paramMap,
				Orders.simpleCreateOrder(ORDER.DESC));
		List<AppDownload> list = new ArrayList<AppDownload>();
		if (null != metaList && !metaList.isEmpty()) {
			for (Map<String, Object> meta : metaList) {
				list.add((AppDownload) SQLUtils.coverMapToBean(meta, AppDownload.class));
			}
		}
		paramMap.put(PagingUtils.IS_PAGING, false);
		int records = baseQueryForList(AppDownload.class, pageConditiion, paramMap, Orders.simpleCreateOrder(ORDER.DESC))
				.size();
		int totalPages = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
		return new GridBean(pageNum, totalPages, records, list);
	}
}