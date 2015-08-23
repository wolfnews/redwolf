package com.hoteam.wolf.controller.manage;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Item;
import com.hoteam.wolf.service.ItemService;

@Controller
@RequestMapping("/manage/item")
public class ManageItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/list")
	@ResponseBody
	public GridBean list(int page,int rows){
		return itemService.list(null, page, rows);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(HttpSession session ,String name,String category,String desc,Long extend,int sku,int value,Long price){
		Item item = new Item(name, desc, category, sku, new BigDecimal(price), value,extend, null);
		return this.itemService.create(item);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(HttpSession session ,Long id,String name,String category,String desc,Long extend,int sku,int value,Long price){
		Item item = this.itemService.load(id);
		item.setCategory(category);
		item.setDesp(desc);
		item.setExtend(extend);
		item.setName(name);
		item.setPrice(new BigDecimal(price));
		item.setSku(sku);
		item.setValue(value);
		return this.itemService.update(item);
	}
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public Result remove(@PathVariable Long id){
		return this.itemService.remove(id);
	}
	
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public EntityResult detail(@PathVariable Long id){
		Item item = this.itemService.load(id);
		return new EntityResult(true, "success", item);
	}
	
}
