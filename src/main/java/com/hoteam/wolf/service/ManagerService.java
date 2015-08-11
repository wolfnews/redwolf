package com.hoteam.wolf.service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.Manager;

/**
 * 管理员服务接口
 * 
 * @author mingwei.dmw
 *
 */
public interface ManagerService {

	/**
	 * 添加新的管理员
	 * 
	 * @param manager
	 * @return
	 * @throws Exception
	 */
	public Manager addNew(Manager manager) throws Exception;

	/**
	 * 修改管理员信息
	 * 
	 * @param manager
	 * @return
	 * @throws Exception
	 */
	public Manager modify(Manager manager) throws Exception;

	/**
	 * 删除管理员信息
	 * 
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	public boolean remove(Long managerId) throws Exception;
	
	
	/**获取指定主键的管理员信息
	 * @param managerId 管理员主键
	 * @return
	 * @throws Exception
	 */
	public Manager load(Long managerId) throws Exception;
	
	/**管理员登录
	 * @param username 账号
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	public Manager login(String username,String password)throws Exception;
	
	/**管理员分页信息
	 * @param manager 查询条件封装类
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return
	 * @throws Exception
	 */
	public GridBean pagination(Manager manager, int pageNum, int pageSize)throws Exception;
}
