package com.hoteam.wolf.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Comment;
import com.hoteam.wolf.service.MessageService;

@Controller
@RequestMapping("/mobile/message")
public class MobileMessageController {
	private static final int PAGE_ROWS = 20;
	@Autowired
	private MessageService messageService;

	@RequestMapping("/list/{user}")
	@ResponseBody
	public EntityResult list(@PathVariable Long user, int page) {
		if (page <= 0) {
			page = 1;
		}
		Comment comment = new Comment();
		comment.setReceiverId(user);
		GridBean gridbean = this.messageService.list(comment, page, PAGE_ROWS);
		List<?> data = gridbean.getRows();
		if (null == data) {
			return new EntityResult(false, "exception", null);
		} else {
			return new EntityResult(true, "success", data);
		}
	}

	@RequestMapping("/detail/{id}")
	@ResponseBody
	public EntityResult detail(@PathVariable Long id) {
		Comment message = this.messageService.load(id);
		if (null == message) {
			return new EntityResult(false, "消息不存在！");
		} else {
			return new EntityResult(true, "success", message);
		}
	}

	@RequestMapping("/create")
	@ResponseBody
	public Result create(Long senderId, Long receiverId, Long lastMessage, String content, String sender,
			String receiver) {
		Comment message = new Comment(senderId, receiverId, lastMessage, content, sender, receiver, false);
		boolean success = this.messageService.saveMessage(message);
		return new Result(success, "" + success);
	}
}
