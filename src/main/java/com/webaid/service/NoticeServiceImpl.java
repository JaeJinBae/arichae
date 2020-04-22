package com.webaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webaid.domain.NoticeVO;
import com.webaid.domain.SearchCriteria;
import com.webaid.persistence.NoticeDao;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao dao;

	@Override
	public List<NoticeVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public NoticeVO selectOne(int no) {
		return dao.selectOne(no);
	}

	@Override
	public NoticeVO selectBefore(int no) {
		return dao.selectBefore(no);
	}

	@Override
	public NoticeVO selectAfter(int no) {
		return dao.selectAfter(no);
	}

	@Override
	public List<NoticeVO> selectTopNotice(String use_state) {
		return dao.selectTopNotice(use_state);
	}

	@Override
	public void insert(NoticeVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(NoticeVO vo) {
		dao.update(vo);
	}

	@Override
	public void updateCnt(int no) {
		dao.updateCnt(no);
	}

	@Override
	public void updateUseState(NoticeVO vo) {
		dao.updateUseState(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

	@Override
	public List<NoticeVO> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}
	
	@Override
	public List<NoticeVO> listSearchAll(SearchCriteria cri) throws Exception {
		return dao.listSearchAll(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

	@Override
	public int listSearchCountAll(SearchCriteria cri) throws Exception {
		return dao.listSearchCountAll(cri);
	}

}
