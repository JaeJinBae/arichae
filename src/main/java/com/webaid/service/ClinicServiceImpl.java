package com.webaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webaid.domain.ClinicVO;
import com.webaid.domain.SearchCriteria;
import com.webaid.persistence.ClinicDao;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicDao dao;

	@Override
	public List<ClinicVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public ClinicVO selectOne(int no) {
		return dao.selectOne(no);
	}

	@Override
	public List<ClinicVO> selectByKind1(int kind1) {
		return dao.selectByKind1(kind1);
	}

	@Override
	public void insert(ClinicVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(ClinicVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

	@Override
	public List<ClinicVO> listSearch(SearchCriteria cri) {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return dao.listSearchCount(cri);
	}

}
