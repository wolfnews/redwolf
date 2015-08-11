package com.hoteam.wolf.controller.site;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.service.ProfessorService;

@Controller
@RequestMapping("/prof")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	@RequestMapping("/list")
	@ResponseBody
	public GridBean list(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		try {
			return this.professorService.list(null, page, rows);
		} catch (Exception e) {
			return new GridBean(0, 0, 0, new ArrayList<ProfessorBean>());
		}
	}
}
