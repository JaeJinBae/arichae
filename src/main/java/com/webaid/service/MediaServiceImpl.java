package com.webaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webaid.domain.MediaVO;
import com.webaid.domain.SearchCriteria;
import com.webaid.persistence.MediaDao;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaDao dao;

	@Override
	public List<MediaVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public MediaVO selectOne(int no) {
		return dao.selectOne(no);
	}

	@Override
	public MediaVO selectBefore(int no) {
		return dao.selectBefore(no);
	}

	@Override
	public MediaVO selectAfter(int no) {
		return dao.selectAfter(no);
	}

	@Override
	public void insert(MediaVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(MediaVO vo) {
		dao.update(vo);
	}

	@Override
	public void updateCnt(int no) {
		dao.updateCnt(no);
	}

	@Override
	public void updateUseState(MediaVO vo) {
		dao.updateUseState(vo);
	}

	@Override
	public void updateThumb(MediaVO vo) {
		dao.updateThumb(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

	@Override
	public List<MediaVO> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public List<MediaVO> listSearchAll(SearchCriteria cri) throws Exception {
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
