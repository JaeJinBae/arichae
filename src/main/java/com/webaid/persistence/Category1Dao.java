package com.webaid.persistence;

import java.util.List;

import com.webaid.domain.Category1VO;
import com.webaid.domain.SearchCriteria;

public interface Category1Dao {
	public List<Category1VO> selectAll();
	public Category1VO selectOne(int no);
	public void insert(Category1VO vo);
	public void update(Category1VO vo);
	public void delete(int no);
	public List<Category1VO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
}
