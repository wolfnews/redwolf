package com.hoteam.wolf.controller.profile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.enums.OrderStatus;
import com.hoteam.wolf.common.vo.OrderBean;
import com.hoteam.wolf.domain.Carts;
import com.hoteam.wolf.domain.Item;
import com.hoteam.wolf.domain.Order;
import com.hoteam.wolf.service.CartService;
import com.hoteam.wolf.service.OrderService;
import com.hoteam.wolf.service.PayService;
import com.hoteam.wolf.utils.DateUtil;

@Controller
@RequestMapping("/profile/order")
public class ProfileOrderController {

	private static Logger logger = Logger.getLogger(ProfileOrderController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private PayService payService;

	@RequestMapping("/list")
	@ResponseBody
	public GridBean list(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, 
			@RequestParam(value = "category",required = false) String category,
			HttpSession session) {
		try {
			Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
			Order order = new Order();
			order.setUserId(userId);
			if(null != category&& category.equals("done")){
				order.setState(OrderStatus.DONE.name());
			}
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
		// Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		// Order order = new Order(sn, name, "用户直充订单", OrderStatus.CONFIRMED.name(), new BigDecimal(money), user, null, null); 
		return null;
	}
	
	@RequestMapping("/pay.html")
	public ModelAndView pay(Long id,String payway,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("profile/order/pay");
		String url = payService.genPayUrl(payway, id, request, response);
		if(null == url || url.isEmpty()){
			mav.addObject("normal",false);
			url = "http://www.niuguhui.org";
		}else{
			mav.addObject("normal",true);
		}
		mav.addObject("target","0;"+url);
		return mav;
	}
	@RequestMapping("/detail/{id}.html")
	@ResponseBody
	public ModelAndView detail(@PathVariable Long id,HttpSession session){
		Long user = (Long) session.getAttribute(Constants.USER_TOKEN.name());
		EntityResult result = this.orderService.load(user, id);
		ModelAndView mav = new ModelAndView("profile/order/detail");
		OrderBean order = (OrderBean) result.getData();
		mav.addObject("order", order.getOrder());
		mav.addObject("items", JSONArray.toJSONString(order.getItems()));
		return mav;
	}
	@RequestMapping("/settle.html")
	public ModelAndView settle(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile/order/settle");
		Long user = (Long) session.getAttribute(Constants.USER_TOKEN.name());
		@SuppressWarnings("unchecked")
		List<Carts> carts = (List<Carts>) this.cartService.userCarts(user).getRows();
		if (null == carts || carts.isEmpty()) {
			mav.addObject("continues", false);
			mav.addObject("reason", "购物车中没有商品");
			mav.addObject("order",new Order());
			mav.addObject("items", new ArrayList<Item>());
		} else {
			String username = (String) session.getAttribute(Constants.USER_NAME.toString());
			String sn = DateUtil.getRecordSn();
			String content = "牛股会用户【" + username + "】购买充值卡或服务订单";
			Order order = new Order(sn, content, content, OrderStatus.CREATED.name(), null, user, null, null);
			EntityResult result = this.orderService.create(order, carts);
			if (result.isSuccess()) {
				mav.addObject("continues", true);
				OrderBean orderBean = (OrderBean)result.getData();
				mav.addObject("order",orderBean.getOrder());
				mav.addObject("items",JSONArray.toJSONString(orderBean.getItems()));
			} else {
				mav.addObject("continues", false);
				mav.addObject("reason", "订单结算异常");
				mav.addObject("order",new Order());
				mav.addObject("items", new ArrayList<Item>());
			}
		}
		return mav;
	}
}
