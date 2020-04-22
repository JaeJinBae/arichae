package com.webaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webaid.domain.SearchCriteria;
import com.webaid.domain.StatisticSelectDateVO;
import com.webaid.domain.StatisticVO;
import com.webaid.persistence.StatisticDao;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private StatisticDao dao;

	@Override
	public List<StatisticVO> selectByDate(StatisticSelectDateVO vo) {
		return dao.selectByDate(vo);
	}

	@Override
	public List<StatisticVO> listSearch(SearchCriteria cri) {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return dao.listSearchCount(cri);
	}

	@Override
	public void insert(StatisticVO vo) {
		dao.insert(vo);
	}

}
