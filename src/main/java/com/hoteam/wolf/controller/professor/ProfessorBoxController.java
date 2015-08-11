package com.hoteam.wolf.controller.professor;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.enums.BoxStatus;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.service.BoxService;

@Controller
@RequestMapping("/professor/box")
public class ProfessorBoxController {

	private static Logger logger = Logger.getLogger(ProfessorBoxController.class);
	@Autowired
	private BoxService boxService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result addNotic(HttpSession session, String title, String keyword, String category, String freeContent,
			String chargeContent, Long group) {
		Long professor = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.name());
		String username = (String)session.getAttribute(Constants.PROFESSOR_NAME.name());
		Box box = new Box(professor, group, title, keyword, freeContent, chargeContent, category,
				BoxStatus.NEW_CREATED.toString(), 0, 0,username);
		try {
			this.boxService.addBox(box);
			return new Result(true, "add box success");
		} catch (Exception e) {
			logger.error("add box error:" + e.getMessage());
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, Box box, HttpSession session) {
		try {
			Long professorId = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.toString());
			box.setAuthor(professorId);
			return this.boxService.pagination(box, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<Box>());
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
