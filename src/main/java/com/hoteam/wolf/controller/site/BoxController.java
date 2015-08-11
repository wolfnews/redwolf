package com.hoteam.wolf.controller.site;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.enums.BoxStatus;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.service.BoxService;

@Controller
@RequestMapping("/box")
public class BoxController {
	private static Logger logger = Logger.getLogger(BoxController.class);
	@Autowired
	private BoxService boxService;

	@RequestMapping("/list")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, Long author) {
		Box box = new Box();
		box.setStatus(BoxStatus.NORMAL.name());
		if(null ==author || 0l==author){
		}else{
			box.setAuthor(author);
		}
		try {
			return this.boxService.pagination(box, page, rows);
		} catch (Exception e) {
			return new GridBean(0, 0, 0, new ArrayList<Box>());
		}
	}

	@RequestMapping("/detail.html")
	@ResponseBody
	public ModelAndView detail(HttpSession session, Long id) {
		ModelAndView mav = new ModelAndView("site/box_detail");
		try {
			Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
			EntityResult result = this.boxService.detail(user, id);
			if (result.isSuccess()) {
				mav.addObject("exist", true);
				if("NSOSO".equals(result.getMessage())){
					mav.addObject("sub",false);
				}else{
					mav.addObject("sub", true);
				}
				mav.addObject("box",result.getData());
			} else {
				mav.addObject("exist", false);
			}
		} catch (Exception e) {
			logger.error("load box error:", e);
			mav.addObject("exist", false);
		}
		return mav;
	}
}
