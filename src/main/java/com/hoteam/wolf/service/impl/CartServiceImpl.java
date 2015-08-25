package com.hoteam.wolf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.dao.CartDao;
import com.hoteam.wolf.domain.Carts;
import com.hoteam.wolf.service.CartService;

@Service("cartService")
public class CartServiceImpl implements CartService {

	private static Logger logger =Logger.getLogger(CartServiceImpl.class);
	@Autowired
	private CartDao cartDao;
	@Override
	public Result addItem(Long user, Long item, String name, int num) {
		Carts cart = new Carts(user, item, name, num); 
		try {
			cartDao.save(cart);
			return new Result(true, "加入购物车成功！");
		} catch (Exception e) {
			logger.error("商品加入购物车异常：",e);
			return new Result(false, "加入购物车异常！");
		}
	}

	@Override
	public Result delItem(Long user, Long id) {
		String sql = "delete from carts where id=:id and user_id=:user";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user", user);
		param.put("id", id);
		try {
			this.cartDao.getNamedParameterJdbcTemplate().update(sql, param);
			return new Result(true, "SUCCESS");
		} catch (DataAccessException e) {
			logger.error("商品移除购物车异常：", e);
			return new Result(false, "移除购物车异常！");
		}
	}

	@Override
	public Result updItem(Long user,Long id, int num) {
		String sql = "update carts set num =: num where id=:id and user_id=:user";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("num", num);
		param.put("id", id);
		param.put("user", user);
		try {
			this.cartDao.getNamedParameterJdbcTemplate().update(sql, param);
			return new Result(true, "SUCCESS");
		} catch (DataAccessException e) {
			logger.error("更新购物车失败：",e);
			return new Result(false, "更新购物车异常！");
		}
	}

	@Override
	public GridBean userCarts(Long user) {
		List<Carts> carts;
		try {
			carts = cartDao.loadByUser(user);
		} catch (Exception e) {
			carts = new ArrayList<Carts>();
			logger.error("获取用户购物车异常！", e);
		}
		if (null == carts) {
			carts = new ArrayList<Carts>();
		}
		return new GridBean(1, carts.size(), carts.size(), carts);
	}

}
