package com.hoteam.wolf.service;

import java.util.List;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Activity;

public interface ActivityService {

	/**
	 * 添加活动
	 * 
	 * @param activity
	 * @return
	 */
	public Activity saveActivity(Activity activity) throws Exception;

	/**
	 * 修改活动
	 * 
	 * @param activity
	 * @return
	 */
	public Activity modifyActivity(Activity activity) throws Exception;

	/**
	 * 获取活动详情
	 * 
	 * @param id
	 * @return
	 */
	public Activity loadActivity(Long id) throws Exception;

	/**
	 * 删除活动
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeActivity(Long id) throws Exception;

	/**
	 * 活动分页
	 * 
	 * @param activity
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public GridBean pagination(Activity activity, int pageNum, int pageSize) throws Exception;

	public List<Activity> hostActivity()throws Exception;
}
