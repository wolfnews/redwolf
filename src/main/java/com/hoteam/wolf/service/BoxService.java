package com.hoteam.wolf.service;

import java.util.List;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Box;

/**
 * 锦囊服务接口
 * @author mingwei.dmw
 *
 */
public interface BoxService {

	public EntityResult load(Long boxId)throws Exception;
	
	public EntityResult detail(Long userId,Long boxId)throws Exception; 
	
	public Result addBox(Box box)throws Exception;
	
	public Result removeBox(Long boxId)throws Exception;
	
	public Result verifyBox(Long boxId,String verifyPerson,boolean passed,String reason)throws Exception;
	
	public GridBean pagination(Box box, int pageNum, int pageSize) throws Exception;
	
	public List<Box> queryForSimpleList(Box box ,int pageNum,int pageSize)throws Exception;
	
	public List<Box> queryForDetailList(Box box ,int pageNum,int pageSize)throws Exception;

}
