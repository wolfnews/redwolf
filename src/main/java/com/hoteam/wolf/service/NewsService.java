package com.hoteam.wolf.service;

import java.util.List;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.News;

public interface NewsService {

	/**保存文章
	 * @param news
	 * @return
	 */
	public News saveNews(News news) throws Exception;
	
	/**修改文章
	 * @param news
	 * @return
	 */
	public News modifyNews(News news)throws Exception;
	
	
	/**获取文章详情
	 * @param id
	 * @return
	 */
	public News loadNews(Long id)throws Exception;
	
	/**删除文章
	 * @param id
	 * @return
	 */
	public Result removeNews(Long id)throws Exception;
	
	/**文章分页
	 * @param news
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public GridBean pagination(News news, int pageNum, int pageSize)throws Exception;
	
	/**按照分类获取文章列表
	 * @param category 文章分类
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return
	 */
	public GridBean listByCategory(String category, int pageNum, int pageSize)throws Exception;
	
	/**月度排行
	 * @return
	 * @throws Exception
	 */
	public List<News> monthTop()throws Exception;
	
	
}
