package com.hoteam.wolf.controller.profile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.enums.BoxStatus;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.service.BoxService;
import com.hoteam.wolf.service.UserBoxService;

@Controller
@RequestMapping("/profile/box")
public class ProfileBoxController {
	private static final int PAGE_ROWS = 20;
	private static Logger logger = Logger.getLogger(ProfileBoxController.class);
	@Autowired
	private BoxService boxService;
	@Autowired
	private UserBoxService userBoxService;

	@RequestMapping("/refresh")
	@ResponseBody
	public EntityResult refresh() {
		try {
			List<Box> boxs = this.boxService.queryForDetailList(null, 1, PAGE_ROWS);
			return new EntityResult(true, "success", boxs);
		} catch (Exception e) {
			logger.error("refresh latest box error:", e);
			return new EntityResult(false, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/pagination")
	@ResponseBody
	public EntityResult pagination(HttpSession session,String type,String author,int page,int rows){
		Long userId = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		GridBean gridBean =null; 
		if(null == type || type.isEmpty() || !("rss".equals(type)||"subs".equals(type))){
			try {
				Box box = new Box();
				box.setStatus(BoxStatus.NORMAL.name());
				if(null !=author && StringUtils.hasText(author)){
					Long professor = new Long(author);
					box.setAuthor(professor);
				}
				gridBean = this.boxService.pagination(box, page, rows);
			} catch (Exception e) {
				gridBean = new GridBean(0, 0, 0, new ArrayList<Box>());
			}
		}else{
			gridBean = this.userBoxService.boxPagination(type, userId, page, rows);
		}
		return new EntityResult(true, "success", gridBean);
	}
	@RequestMapping("/detail.html")
	@ResponseBody
	public ModelAndView detail(HttpSession session,Long id){
		ModelAndView mav = new ModelAndView("profile/ndetail");
		try {
			Long userId = (Long)session.getAttribute(Constants.USER_TOKEN.toString());
			EntityResult box = this.boxService.detail(userId, id);
			mav.addObject("result", JSONObject.toJSONString(box, SerializerFeature.WriteDateUseDateFormat));
//			mav.addObject("result", box);
		} catch (Exception e) {
			logger.error("mobile load box error:",e);
			mav.addObject("result", new EntityResult(false, "ERROR", null));
		}
		return mav;
	}
}
