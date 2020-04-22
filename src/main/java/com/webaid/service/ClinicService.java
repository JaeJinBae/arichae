package com.webaid.service;

import java.util.List;

import com.webaid.domain.ClinicVO;
import com.webaid.domain.SearchCriteria;

public interface ClinicService {
	public List<ClinicVO> selectAll();
	public ClinicVO selectOne(int no);
	public List<ClinicVO> selectByKind1(int kind1);
	public void insert(ClinicVO vo);
	public void update(ClinicVO vo);
	public void delete(int no);
	public List<ClinicVO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
}
