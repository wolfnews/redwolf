package com.hoteam.wolf.controller.profile;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/list")
	@ResponseBody
	public GridBean list(HttpSession session){
		Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		return cartService.userCarts(user);
	}
	
	@RequestMapping("/item/add/{id}")
	@ResponseBody
	public Result addItem(@PathVariable Long id,String name,Integer num,HttpSession session	){
		Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		if(0>=num){
			return new Result(false, "商品数量必须大于1！");
		}else{
			return cartService.addItem(user, id, name, num);
		}
	}
	
	@RequestMapping("/item/remove/{id}")
	@ResponseBody
	public Result removeItem(@PathVariable Long id,HttpSession session){
		Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		return cartService.delItem(user, id);
	}
	
	@RequestMapping("/item/update/{id}")
	@ResponseBody
	public Result modifyItem(@PathVariable Long id,int num,HttpSession session){
		Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		if(0>=num){
			return new Result(false, "商品数量必须大于1！");
		}else{
			return cartService.updItem(user, id, num);
		}
	}
}
