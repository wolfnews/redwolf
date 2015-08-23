package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.service.ProfessorService;

@Controller
@RequestMapping("/manage/professor")
public class ManageProfessorController {

	private static Logger logger = Logger.getLogger(ManageProfessorController.class);
	@Autowired
	private ProfessorService professorService;

	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, Professor professor) {
		try {
			return this.professorService.pagination(professor, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<Professor>());
		}
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Result remove(Long professorId) {
		try {
			this.professorService.remove(professorId);
			return new Result(true, "remove professor success");
		} catch (Exception e) {
			logger.error("remove Professor exception:", e);
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result addProfessor(String truename, String username, String password, String mobile, String occupation,
			String summary, int level, int price) {
		Professor professor = new Professor();
		professor.setUsername(username);
		professor.setPassword(password);
		professor.setTruename(truename);
		professor.setMobile(mobile);
		professor.setOccupation(occupation);
		professor.setSummary(summary);
		professor.setLevel(level);
		professor.setIconPath("");
		try {
			this.professorService.add(professor, price);
			return new Result(true, "add Professor success");
		} catch (Exception e) {
			logger.error("add Professor error:" + e.getMessage());
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/detail")
	@ResponseBody
	public EntityResult detail(Long professorId) {
		try {
			Professor professor = this.professorService.load(professorId);
			if (null == professor) {
				return new EntityResult(false, "none professor", null);
			} else {
				return new EntityResult(true, "get professor", professor);
			}
		} catch (Exception e) {
			return new EntityResult(false, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/loadAll")
	@ResponseBody
	public EntityResult loadAll(){
		try {
			List<Professor> profs = new ArrayList<Professor>();
			GridBean grid = this.professorService.pagination(null, 1, Integer.MAX_VALUE);
			if(null ==grid.getRows()){
			}else{
				profs = (List<Professor>) grid.getRows();
			}
			return new EntityResult(true, "success", profs);
		} catch (Exception e) {
			logger.error("load all professor exception:",e);
			return new EntityResult(false, "系统异常", null);
		}
	}
}
