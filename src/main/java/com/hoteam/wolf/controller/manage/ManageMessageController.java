package com.hoteam.wolf.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Comment;
import com.hoteam.wolf.service.MessageService;

@Controller
@RequestMapping("/manage/message")
public class ManageMessageController {

	@Autowired
	private MessageService messageService;
	@RequestMapping("/list")
	@ResponseBody
	public GridBean list(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows){
		return this.messageService.list(new Comment(), page, rows);
	}
	
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public Result remove(@PathVariable Long id){
		boolean success = this.messageService.remove(id);
		if(success){
			return new Result(true, "success");
		}else{
			return new Result(false, "删除失败！");
		}
	}
	
}
