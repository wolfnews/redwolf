package com.hoteam.wolf.controller.profile;

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
import com.hoteam.wolf.domain.Comment;
import com.hoteam.wolf.service.MessageService;

@Controller
@RequestMapping("/profile/message")
public class ProfileMessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("/list/{category}")
	@ResponseBody
	public GridBean list(@PathVariable String category, @RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, HttpSession session) {
		Comment message = new Comment();
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.name());
		if ("send".equals(category)) {
			message.setSenderId(userId);
		} else if ("receive".equalsIgnoreCase(category)) {
			message.setReceiverId(userId);
		}
		return this.messageService.list(message, page, rows);
	}
	
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public Result remove(@PathVariable Long id){
		boolean success = this.messageService.remove(id);
		if(success){
			return new Result(true, "删除成功");
		}else{
			return new Result(false, "删除失败！");
		}
	}
	@RequestMapping("/add")
	@ResponseBody
	public Result add(HttpSession session,
			@RequestParam(value="receiverId" ,required = true)Long receiverId,
			@RequestParam(value="content",required = false)String content,
			@RequestParam(value="receiver",required = false)String receiver,
			@RequestParam(value="last",required=false)Long last){
		Long senderId = (Long) session.getAttribute(Constants.USER_TOKEN.name());
		String sender = (String)session.getAttribute(Constants.USER_NAME.name());
		Comment message =  new Comment(senderId, receiverId, last, content, sender, receiver, true);
		boolean success = this.messageService.saveMessage(message);
		if(success){
			return new Result(true, "添加成功！");
		}else{
			return new Result(false, "添加失败！");
		}
	}
}
