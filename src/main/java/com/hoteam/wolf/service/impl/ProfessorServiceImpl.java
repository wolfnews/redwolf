package com.hoteam.wolf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.enums.GroupLevel;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.common.vo.ProfessorStatBean;
import com.hoteam.wolf.dao.BoxDao;
import com.hoteam.wolf.dao.ProfessorDao;
import com.hoteam.wolf.dao.SubscribeGroupDao;
import com.hoteam.wolf.dao.UserRssDao;
import com.hoteam.wolf.dao.UserSubscribeDao;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.SubscribeGroup;
import com.hoteam.wolf.service.ProfessorService;

@Service("professorService")
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

	private static Logger logger = Logger.getLogger(ProfessorServiceImpl.class);
	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private BoxDao boxDao;
	@Autowired
	private SubscribeGroupDao subscribeGroupDao;
	@Autowired
	private UserRssDao userRssDao;
	@Autowired
	private UserSubscribeDao userSubscribeDao;
	@Override
	public Professor add(Professor professor, int price) throws Exception {
		try {
			professor = this.professorDao.save(professor);
			SubscribeGroup sgroup = new SubscribeGroup(professor.getId(), professor.getUsername()
					+ "-1", GroupLevel.DEFAULT.getIndex(), price, 500);
			subscribeGroupDao.save(sgroup);
			return professor;
		} catch (Exception e) {
			logger.error("add professor exception:", e);
			throw new Exception("add professor error!");
		}
	}

	@Override
	public Professor login(String username, String password) throws Exception {
		try {
			return this.professorDao.load(username, password);
		} catch (Exception e) {
			logger.error("professor login error:", e);
			throw new Exception("professor login error");
		}
	}

	@Override
	public Professor modify(Professor professor) throws Exception {
		try {
			return this.professorDao.update(professor);
		} catch (Exception e) {
			logger.error("modify professor exception:", e);
			throw new Exception("modify professor error!");
		}
	}

	@Override
	public void remove(Long id) throws Exception {
		try {
			this.subscribeGroupDao.deleteByProfessor(id);
			this.userRssDao.deleteByProfessor(id);
			this.userSubscribeDao.deleteByProfessor(id);
			this.boxDao.deleteByProfessor(id);
			this.professorDao.delete(id);
		} catch (Exception e) {
			logger.error("remove professor exception:", e);
			throw new Exception("remove professor error!");
		}
	}

	@Override
	public GridBean pagination(Professor professor, int pageNum, int pageSize) throws Exception {
		try {
			return this.professorDao.pagination(professor, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("professor pagination exception:", e);
			throw new Exception("professor pagination exception");
		}
	}
	@Override
	public Professor load(Long id) throws Exception {
		try {
			return this.professorDao.load(id);
		} catch (Exception e) {
			logger.error("professor load exception:", e);
			throw new Exception("professor load exception");
		}
	}
	@Override
	public ProfessorBean loadDetail(Long id) throws Exception {
		try {
			Professor professor = this.professorDao.load(id);
			ProfessorBean professorBean = new ProfessorBean();
			BeanUtils.copyProperties(professor, professorBean);
			ProfessorStatBean statis = this.statisProfessor(professor.getId());
			professorBean.setRssCount(statis.getUserRssAccount());
			professorBean.setSubCount(statis.getUserSubscribeAccount());
			professorBean.setBoxCount(statis.getNoticeAccount());
			SubscribeGroup group = new SubscribeGroup();
			group.setProfessorId(professor.getId());
			List<SubscribeGroup> groups = this.subscribeGroupDao.queryForList(group);
			List<Integer> prices =new ArrayList<Integer>();
			for(SubscribeGroup sg: groups){
				prices.add(sg.getPrice());
			}
			professorBean.setPrices(prices);
			return professorBean;
		} catch (Exception e) {
			logger.error("professor load exception:", e);
			throw new Exception("professor load exception");
		}
	}

	@Override
	public ProfessorStatBean statisProfessor(Long professorId) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("author", professorId);
		Long browseCount = this.professorDao.baseAccountQuery("select sum(browse_num) from box where author=:author",
				param);
		Long boxCount = this.professorDao.baseAccountQuery("select count(1) from box where author=:author", param);
		Long rssCount = this.professorDao.baseAccountQuery("select count(1) from user_rss where professor_id=:author",
				param);
		Long subAccount = this.professorDao.baseAccountQuery(
				"select count(1) from subscribe_record where professor_id=:author", param);
		if (null == browseCount) {
			browseCount = 0l;
		}
		if (null == boxCount) {
			boxCount = 0l;
		}
		if (null == rssCount) {
			rssCount = 0l;
		}
		if (null == subAccount) {
			subAccount = 0l;
		}
		ProfessorStatBean account = new ProfessorStatBean(boxCount.longValue(), browseCount.longValue(),
				rssCount, subAccount);
		return account;
	}

	@Override
	public List<ProfessorBean> loadAll() throws Exception {
		List<ProfessorBean> professorBeans = new ArrayList<ProfessorBean>();
		try {
			List<Professor> professors =  this.professorDao.loadAll();
			for(Professor professor:professors){
				ProfessorBean professorBean = this.assembleProfessor(professor);
				professorBeans.add(professorBean);
			}
		} catch (Exception e) {
			logger.error("load all professor exception:", e);
			throw new Exception("load all professors error!");
		}
		return professorBeans;
	}

	private ProfessorBean assembleProfessor(Professor professor) throws Exception{
		ProfessorBean professorBean = new ProfessorBean();
		BeanUtils.copyProperties(professor, professorBean);
		ProfessorStatBean statis = this.statisProfessor(professor.getId());
		professorBean.setRssCount(statis.getUserRssAccount());
		professorBean.setSubCount(statis.getUserSubscribeAccount());
		professorBean.setBoxCount(statis.getNoticeAccount());
		SubscribeGroup group = new SubscribeGroup();
		group.setProfessorId(professor.getId());
		List<SubscribeGroup> groups = this.subscribeGroupDao.queryForList(group);
		List<Integer> prices =new ArrayList<Integer>();
		for(SubscribeGroup sg: groups){
			prices.add(sg.getPrice());
		}
		professorBean.setPrices(prices);
		return professorBean;
	}
	@Override
	public List<Professor> loadList(String username) throws Exception {
		return this.professorDao.loadList(username);
	}

	@Override
	public List<SubscribeGroup> listGroup(Long professorId) throws Exception {
		SubscribeGroup group = new SubscribeGroup();
		group.setProfessorId(professorId);
		List<SubscribeGroup> groups = this.subscribeGroupDao.queryForList(group);
		for(int i=0;i<groups.size();i++){
			group = groups.get(i);
			group.setGmtCreate(null);
			group.setGmtModify(null);
			groups.set(i, group);
		}
		return groups;
	}

	@Override
	public GridBean getRssUser(Long professorId, int pageNum, int pageSize) throws Exception {
		try {
			return this.userRssDao.rssUser(professorId, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("get Rss user exception:",e);
			throw new Exception("get Rss user service error");
		}
	}

	@Override
	public GridBean getSubsUser(Long professorId, int pageNum, int pageSize) throws Exception {
		try {
			return this.userSubscribeDao.subsUser(professorId, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("get subscribe user exception:",e);
			throw new Exception("get subscribe user service error");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GridBean list(Professor querybean, int pageNum, int pageSize) throws Exception {
		try {
			GridBean gridBean = this.professorDao.pagination(querybean, pageNum, pageSize);
			List<Professor> professors = (List<Professor>) gridBean.getRows();
			List<ProfessorBean> professorBeans = new ArrayList<ProfessorBean>();
			for (Professor professor : professors) {
				ProfessorBean professorBean = this.assembleProfessor(professor);
				professorBeans.add(professorBean);
			}
			gridBean.setRows(professorBeans);
			return gridBean;
		} catch (Exception e) {
			logger.error("professor pagination exception:", e);
			return new GridBean(pageNum, 0, 0, new ArrayList<ProfessorBean>());
		}
	}
}
