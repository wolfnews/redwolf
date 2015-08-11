package com.hoteam.wolf.service;

import java.util.List;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.common.vo.UserProfile;
import com.hoteam.wolf.domain.User;

/**用户服务接口
 * @author mingwei.dmw
 *
 */
public interface UserService {

	/**添加新用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public EntityResult addUser(User user)throws Exception;
	
	/**获取用户详细信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User load(Long userId)throws Exception;
	
	
	/**
	 * @param username
	 * @param password
	 * @return 
	 * @throws Exception
	 */
	public User load(String username,String password)throws Exception;
	
	/**用户确认
	 * @param usercliper
	 * @return
	 * @throws Exception
	 */
	public Result identify(String usercliper)throws Exception;
	/**用户登录
	 * @param mobile
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public EntityResult login(String mobile,String username,String password)throws Exception;
	
	/**修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User modifyUser(User user)throws Exception;
	
	/**删除用户信息
	 * @param id
	 * @throws Exception
	 */
	public void removeUser(Long id)throws Exception;
	
	/**用户信息分页
	 * @param user
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public GridBean pagination(User user, int pageNum, int pageSize)throws Exception;
	
	/**用户关注讲师
	 * @param userId
	 * @param professorId
	 * @return
	 * @throws Exception
	 */
	public Result rssProfessor(Long userId,Long professorId) throws Exception;
	
	/**订阅讲师
	 * @param userId
	 * @param professorId
	 * @param time 订阅时长
	 * @return
	 * @throws Exception
	 */
	public Result subscribeProfessor(Long userId,Long professorId, Long groupId,int time)throws Exception;
	
	/**用户充值
	 * @param userId
	 * @param money
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public Result recharge(Long userId,int money,String category)throws Exception;
	
	/**获取用户的概要信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserProfile profile(Long userId)throws Exception;
	
	/**获取用户关注的讲师ID列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Long> getRssProfessorIds(Long userId) throws Exception;
	
	/**获取用户订阅的讲师ID列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Long> getSubProfessorIds(Long userId)throws Exception;
	
	/**获取用户关注的讲师
	 * @param userId
	 * @return
	 */
	public List<ProfessorBean> getRssProfessors(Long userId);
	
	/**获取用户订阅的讲师
	 * @param userId
	 * @return
	 */
	public List<ProfessorBean> getSubProfessors(Long userId);
	
	/**根据注册邮箱进行密码找回
	 * @param email
	 * @return
	 */
	public Result findUserPassword(String email);
	
}
