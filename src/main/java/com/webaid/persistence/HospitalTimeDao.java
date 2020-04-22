package com.webaid.persistence;

import java.util.List;

import com.webaid.domain.HospitalTimeVO;

public interface HospitalTimeDao {
	public List<HospitalTimeVO> selectAll();
	public HospitalTimeVO selectOne(int no);
	public HospitalTimeVO selectByDow(String h_date);
	public void insert(HospitalTimeVO vo);
	public void update(HospitalTimeVO vo);
	public void delete(int no);
}	
