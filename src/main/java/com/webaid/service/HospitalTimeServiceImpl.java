package com.webaid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webaid.domain.HospitalTimeVO;
import com.webaid.persistence.HospitalTimeDao;

@Service
public class HospitalTimeServiceImpl implements HospitalTimeService {

	@Autowired
	private HospitalTimeDao dao;	

	@Override
	public List<HospitalTimeVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public HospitalTimeVO selectOne(int no) {
		return dao.selectOne(no);
	}

	@Override
	public HospitalTimeVO selectByDow(String h_date) {
		return dao.selectByDow(h_date);
	}

	@Override
	public void insert(HospitalTimeVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(HospitalTimeVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

}
