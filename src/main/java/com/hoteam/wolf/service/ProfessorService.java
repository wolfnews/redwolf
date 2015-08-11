package com.hoteam.wolf.service;

import java.util.List;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.common.vo.ProfessorStatBean;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.SubscribeGroup;

/**
 * 讲师服务接口
 * @author mingwei.dmw
 *
 */
public interface ProfessorService {

	/**添加讲师
	 * @param professor
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public Professor add(Professor professor,int price)throws Exception;
	
	/**获取讲师详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProfessorBean loadDetail(Long id)throws Exception;
	
	
	/**获取讲师详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Professor load(Long id)throws Exception;
	
	/**按照讲师姓名模糊查询讲师列表
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<Professor> loadList(String username)throws Exception;
	/**获取所有讲师列表
	 * @return
	 * @throws Exception
	 */
	public List<ProfessorBean> loadAll()throws Exception;
	
	
	/**讲师登录系统
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Professor login(String username,String password)throws Exception;
	
	/**修改讲师基本信息
	 * @param professor
	 * @return
	 * @throws Exception
	 */
	public Professor modify(Professor professor)throws Exception;
	
	/**获取讲师目前的订阅分组
	 * @param professorId 讲师ID
	 * @return
	 * @throws Exception
	 */
	public List<SubscribeGroup> listGroup(Long professorId) throws Exception;
	/**删除讲师，同时删除讲师下的订阅分组和锦囊信息
	 * @param id
	 * @throws Exception
	 */
	public void remove(Long id)throws Exception;
	
	/**统计讲师的一些信息，锦囊数，订阅和关注的数目等
	 * @param professorId
	 * @return
	 * @throws Exception
	 */
	public ProfessorStatBean statisProfessor(Long professorId)throws Exception;
	
	/**讲师分页信息
	 * @param professor
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public GridBean pagination(Professor professor, int pageNum, int pageSize)throws Exception;
	/**讲师分页信息
	 * @param professor
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public GridBean list(Professor professor, int pageNum, int pageSize)throws Exception;

	/**获取关注当前讲师的用户列表
	 * @param professorId 讲师ID
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public GridBean getRssUser(Long professorId,int pageNum,int pageSize)throws Exception;
	
	/**获取订阅当前讲师的用户信息
	 * @param professorId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public GridBean getSubsUser(Long professorId,int pageNum,int pageSize)throws Exception;
}
