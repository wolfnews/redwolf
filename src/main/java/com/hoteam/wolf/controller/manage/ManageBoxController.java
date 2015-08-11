package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.service.BoxService;

@Controller
@RequestMapping("/manage/box")
public class ManageBoxController {

	private static Logger logger = Logger.getLogger(ManageBoxController.class);

	@Autowired
	private BoxService boxService;

	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, Box box) {
		try {
			return this.boxService.pagination(box, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<Box>());
		}
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Result remove(Long boxId) {
		try {
			return this.boxService.removeBox(boxId);
		} catch (Exception e) {
			logger.error("remove box exception:", e);
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/verify")
	@ResponseBody
	public Result verify(Long boxId, boolean passed, String reason, HttpSession session) {
		try {
			String manager = (String) session.getAttribute(Constants.MANAGER_NAME.toString());
			return this.boxService.verifyBox(boxId, manager, passed, reason);
		} catch (Exception e) {
			logger.error("verify box exception:", e);
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/detail")
	@ResponseBody
	public EntityResult detail(Long boxId) {
		try {
			return this.boxService.load(boxId);
		} catch (Exception e) {
			return new EntityResult(false, e.getMessage(), null);
		}
	}
}
