package com.hoteam.wolf.controller.professor;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Message;
import com.hoteam.wolf.service.MessageService;

@Controller
@RequestMapping("/professor/message")
public class ProfessorMessageController {

	@Autowired
	private MessageService messageService;
	@RequestMapping("/list/{category}")
	@ResponseBody
	public GridBean list(@PathVariable String category,@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows,HttpSession session){
		Message message = new Message();
		Long profId = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.name());
		if("send".equals(category)){
			message.setSenderId(profId);
		}else if("receive".equalsIgnoreCase(category)){
			message.setReceiverId(profId);
		}
		return this.messageService.list(message, page, rows);
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
	@RequestMapping("/add")
	@ResponseBody
	public Result add(HttpSession session,
			@RequestParam(value="receiver" ,required = true)Long receiver,
			@RequestParam(value="title",required = true)String title,
			@RequestParam(value="content",required = false)String content,
			@RequestParam(value="last",required=false)Long last){
		Long sender = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.name());
		Message message = new Message(sender, receiver, last, title, content);
		boolean success = this.messageService.saveMessage(message);
		if(success){
			return new Result(true, "success");
		}else{
			return new Result(false, "添加失败！");
		}
	}
}
