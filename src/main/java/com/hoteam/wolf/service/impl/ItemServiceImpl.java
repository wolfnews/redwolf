package com.hoteam.wolf.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.dao.ItemDao;
import com.hoteam.wolf.domain.Item;
import com.hoteam.wolf.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	private static Logger logger = Logger.getLogger(ItemServiceImpl.class);
	@Autowired
	private ItemDao itemDao;
	
	@Override
	public GridBean list(Item item, int pageNum, int pageSize) {
		try {
			return this.itemDao.pagination(item, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("list Item exception:",e);
			return new GridBean(pageNum, 0, 0, new ArrayList<Item>());
		}
	}

	@Override
	public Result create(Item item) {
		try {
			this.itemDao.save(item);
			return new Result(true, "添加商品成功！");
		} catch (Exception e) {
			logger.error("save item exception:",e);
			return new Result(false, "添加商品失败");
		}
	}

	@Override
	public Result update(Item item) {
		try {
			this.itemDao.update(item);
			return new Result(true, "更新商品成功!");
		} catch (Exception e) {
			logger.error("update item exception:",e);
			return new Result(false, "更新商品失败!");
		}
	}

	@Override
	public Result remove(Long id) {
		try {
			this.itemDao.delete(id);
			return new Result(true, "删除商品成功！");
		} catch (Exception e) {
			logger.error("remove item exception:", e);
			return new Result(false, "删除商品失败");
		}
	}

	@Override
	public Item load(Long id) {
		return itemDao.load(id);
	}

}
