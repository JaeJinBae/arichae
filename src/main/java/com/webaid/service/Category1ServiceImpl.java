package com.webaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webaid.domain.Category1VO;
import com.webaid.domain.SearchCriteria;
import com.webaid.persistence.Category1Dao;

@Service
public class Category1ServiceImpl implements Category1Service {

	@Autowired
	private Category1Dao dao;
	
	@Override
	public List<Category1VO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Category1VO selectOne(int no) {
		return dao.selectOne(no);
	}

	@Override
	public void insert(Category1VO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(Category1VO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

	@Override
	public List<Category1VO> listSearch(SearchCriteria cri) {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return dao.listSearchCount(cri);
	}

}
