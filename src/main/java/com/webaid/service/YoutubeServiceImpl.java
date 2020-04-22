package com.webaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webaid.domain.SearchCriteria;
import com.webaid.domain.YoutubeVO;
import com.webaid.persistence.YoutubeDao;

@Service
public class YoutubeServiceImpl implements YoutubeService {

	@Autowired
	private YoutubeDao dao;
	
	@Override
	public List<YoutubeVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public YoutubeVO selectOne(int no) {
		return dao.selectOne(no);
	}

	@Override
	public YoutubeVO selectBefore(int no) {
		return dao.selectBefore(no);
	}

	@Override
	public YoutubeVO selectAfter(int no) {
		return dao.selectAfter(no);
	}

	@Override
	public void insert(YoutubeVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(YoutubeVO vo) {
		dao.update(vo);
	}

	@Override
	public void updateCnt(int no) {
		dao.updateCnt(no);
	}

	@Override
	public void updateUseState(YoutubeVO vo) {
		dao.updateUseState(vo);
	}

	@Override
	public void updateThumb(YoutubeVO vo) {
		dao.updateThumb(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

	@Override
	public List<YoutubeVO> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public List<YoutubeVO> listSearchAll(SearchCriteria cri) throws Exception {
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
