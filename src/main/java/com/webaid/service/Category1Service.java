package com.webaid.service;

import java.util.List;

import com.webaid.domain.Category1VO;
import com.webaid.domain.SearchCriteria;

public interface Category1Service {
	public List<Category1VO> selectAll();
	public Category1VO selectOne(int no);
	public void insert(Category1VO vo);
	public void update(Category1VO vo);
	public void delete(int no);
	public List<Category1VO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
}
