package com.hoteam.wolf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.dao.NewsDao;
import com.hoteam.wolf.domain.News;
import com.hoteam.wolf.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	@Autowired
	private NewsDao newsDao;

	@Override
	public News saveNews(News news) throws Exception {
		return this.newsDao.save(news);
	}

	@Override
	public News modifyNews(News news) throws Exception {
		return this.newsDao.update(news);
	}

	@Override
	public News loadNews(Long id) throws Exception {
		return this.newsDao.load(id);
	}

	@Override
	public Result removeNews(Long id) throws Exception {
		try {
			this.newsDao.delete(id);
			return new Result(true, "success");
		} catch (Exception e) {
			logger.error("delete news:" + id + "Exception:", e);
			throw e;
		}
	}

	@Override
	public GridBean pagination(News news, int pageNum, int pageSize) throws Exception {
		return this.newsDao.pagination(news, pageNum, pageSize);
	}

	@Override
	public GridBean listByCategory(String category, int pageNum, int pageSize) throws Exception {
		News news = new News();
		news.setCategory(category);
		return this.newsDao.pagination(news, pageNum, pageSize);
	}

	@Override
	public List<News> monthTop() throws Exception {
		return new ArrayList<News>();
	}

}
