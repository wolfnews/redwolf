package com.hoteam.wolf.controller.manage;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.enums.ActivityStatus;
import com.hoteam.wolf.domain.Activity;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.service.ActivityService;

@Controller
@RequestMapping("/manage/activity")
public class ManageActivityController {

	private static Logger logger = Logger.getLogger(ManageActivityController.class);
	@Autowired
	private ActivityService activityService;

	/**
	 * 分页信息
	 * 
	 * @param page
	 * @param rows
	 * @param activity
	 * @return
	 */
	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, Activity activity) {
		try {
			return this.activityService.pagination(activity, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<Box>());
		}
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result add(String name, String category, float rate, String start, String end, String desc) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// TODO 默认立即生效，后续可以在发布活动的时候指定是否立即生效，
			// 如果选择否，则可以在发布完活动后对该活动单独进行生效的操作，未生效的活动不会被系统执行
			BigDecimal bigDecimal = new BigDecimal(rate);
			Activity activity = new Activity(name, category, bigDecimal, sdf.parse(start), sdf.parse(end), true,
					ActivityStatus.NORMAL.toString(), desc);
			this.activityService.saveActivity(activity);
			return new Result(true, "SUCCESS");
		} catch (Exception e) {
			logger.error("Save Activity Exception:" + e.getMessage());
			return new Result(false, "活动发布失败！");
		}
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Result remove(Long id) {
		try {
			this.activityService.removeActivity(id);
			return new Result(true, "SUCCESS");
		} catch (Exception e) {
			logger.error("Remove Activity Exception:" + e.getMessage());
			return new Result(false, "删除发布失败！");
		}
	}
}
