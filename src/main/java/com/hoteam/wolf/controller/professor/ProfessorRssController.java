package com.hoteam.wolf.controller.professor;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.service.ProfessorService;

@Controller
@RequestMapping("/professor/rss")
public class ProfessorRssController {
	private static Logger logger = Logger.getLogger(ProfessorRssController.class);
	@Autowired
	private ProfessorService professorService;

	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, HttpSession session) {
		try {
			Long professor = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.toString());
			return this.professorService.getRssUser(professor, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<User>());
		}
	}
}
