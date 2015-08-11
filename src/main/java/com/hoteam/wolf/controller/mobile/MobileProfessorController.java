package com.hoteam.wolf.controller.mobile;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.service.ProfessorService;
import com.hoteam.wolf.service.UserService;

@Controller
@RequestMapping("/mobile/prof")
public class MobileProfessorController {

	private static Logger logger = Logger.getLogger(MobileProfessorController.class);
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private UserService userService;

	@RequestMapping("/list/{category}")
	@ResponseBody
	public EntityResult list(@PathVariable String category,Long user) {
		try {
			List<ProfessorBean> professors = new ArrayList<ProfessorBean>();
			if(null == category||category.isEmpty()||"all".endsWith(category)){
				category= "all";
				professors = this.professorService.loadAll();
			}else if("rss".equals(category)){
				professors = this.userService.getRssProfessors(user);
			}else if("subs".equals(category)){
				professors = this.userService.getSubProfessors(user);
			}else{
				professors = this.professorService.loadAll();
			}
			return new EntityResult(true, "success", professors);
		} catch (Exception e) {
			logger.error("mobile load professor exception:", e);
			return new EntityResult(false, "加载失败！", null);
		}
	}

	@RequestMapping("/detail")
	@ResponseBody
	public EntityResult detail(Long id) {
		try {
			ProfessorBean professor = this.professorService.loadDetail(id);
			if (null == professor) {
				return new EntityResult(false, "该讲师被删除");
			} else {
				return new EntityResult(true, "加载成功", professor);
			}
		} catch (Exception e) {
			logger.error("mobile load professor error:", e);
			return new EntityResult(false, "系统一场！", null);
		}
	}

	@RequestMapping("/search")
	@ResponseBody
	public EntityResult search(String name) {
		try {
			List<Professor> professors = this.professorService.loadList(name);
			return new EntityResult(true, "success", professors);
		} catch (Exception e) {
			logger.error("search professor exception:", e);
			return new EntityResult(false, "服务器异常", null);
		}
	}
}
