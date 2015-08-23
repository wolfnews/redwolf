package com.hoteam.wolf.controller.profile;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.enums.OrderStatus;
import com.hoteam.wolf.domain.Order;
import com.hoteam.wolf.service.OrderService;

@Controller
@RequestMapping("/profile/order")
public class ProfileOrderController {

	private static Logger logger = Logger.getLogger(ProfileOrderController.class);
	@Autowired
	private OrderService orderService;

	@RequestMapping("/list")
	@ResponseBody
	public GridBean list(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, 
			@RequestParam(value = "state",required = false) String state,
			HttpSession session) {
		try {
			Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
			Order order = new Order();
			order.setUserId(userId);
			order.setState(state);
			return orderService.list(order, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<Order>());
		}
	}
	/**删除订单，用户只能删除自己的订单
	 * @param id 订单ID
	 * @param session 用户会话信息
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public Result remove(@PathVariable Long id,HttpSession session) {
		Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		return this.orderService.remove(user, id);
	}
	
	@RequestMapping("/submit")
	@ResponseBody
	public Result submit(HttpSession session,String sn,String name,Long money){
		Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		Order order = new Order(sn, name, "用户直充订单", OrderStatus.CONFIRMED.name(), new BigDecimal(money), user, null, null); 
		return null;
	}
}
