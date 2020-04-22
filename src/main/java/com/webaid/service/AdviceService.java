package com.webaid.service;

import java.util.List;

import com.webaid.domain.AdviceVO;
import com.webaid.domain.SearchCriteria;

public interface AdviceService {
	public AdviceVO selectOne(int no);
	public List<AdviceVO> selectNonComplete();
	public void insert(AdviceVO vo);
	public void update(AdviceVO vo);
	public void delete(int no);
	public List<AdviceVO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
}
