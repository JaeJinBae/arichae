package com.webaid.persistence;

import java.util.List;

import com.webaid.domain.SearchCriteria;
import com.webaid.domain.StatisticSelectDateVO;
import com.webaid.domain.StatisticVO;

public interface StatisticDao {
	public List<StatisticVO> selectByDate(StatisticSelectDateVO vo);
	public List<StatisticVO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
	public void insert(StatisticVO vo);
}
