package com.webaid.service;

import java.util.List;

import com.webaid.domain.EventVO;
import com.webaid.domain.SearchCriteria;

public interface EventService {
	public List<EventVO> selectAll();
	public EventVO selectOne(int no);
	public List<EventVO> selectByDate(String select_date);
	public void insert(EventVO vo);
	public void update(EventVO vo);
	public void delete(int no);
	public List<EventVO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
}
