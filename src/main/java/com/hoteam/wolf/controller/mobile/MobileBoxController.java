package com.hoteam.wolf.controller.mobile;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.enums.BoxStatus;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.service.BoxService;
import com.hoteam.wolf.service.UserBoxService;

@Controller
@RequestMapping("/mobile/box")
public class MobileBoxController {

	private static final int PAGE_ROWS = 50;
	private static Logger logger = Logger.getLogger(MobileBoxController.class);

	@Autowired
	private BoxService boxService;
	@Autowired
	private UserBoxService userBoxService;

	@RequestMapping("/list")
	@ResponseBody
	public EntityResult list(Integer pages,String author,Long user,String category) {
		if (null == pages) {
			pages = 1;
		}
		if(null == category || category.isEmpty() || !("rss".equals(category)||"subs".equals(category))){
			Box box = new Box();
			box.setStatus(BoxStatus.NORMAL.name());
			if(null !=author && StringUtils.hasText(author)){
				Long professor = new Long(author);
				box.setAuthor(professor);
			}
			try {
				List<Box> boxs = this.boxService.queryForDetailList(box, pages, PAGE_ROWS);
				return new EntityResult(true, "success", boxs);
			} catch (Exception e) {
				logger.error("load mobile box exception:", e);
				return new EntityResult(false, "load box error!", null);
			}
		}else{
			GridBean gridbean = this.userBoxService.boxPagination(category, user, pages, PAGE_ROWS);
			List<?> data = gridbean.getRows();
			if(null == data){
				return new EntityResult(false, "exception", null);
			}else{
				return new EntityResult(true, "success", data);
			}
		}
	}

	@RequestMapping("/detail")
	@ResponseBody
	public EntityResult detail(Long id) {
		try {
			return this.boxService.detail(id, id);
		} catch (Exception e) {
			logger.error("mobile load box error:", e);
			return new EntityResult(false, "加载失败！", null);
		}
	}
}
