package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.HospitalTimeVO;

@Repository
public class HospitalTimeDaoImpl implements HospitalTimeDao {

	private static final String namespace = "com.webaid.mappers.HospitalTimeMapper";

	@Autowired
	private SqlSession session;

	@Override
	public List<HospitalTimeVO> selectAll() {
		return session.selectList(namespace + ".selectAll");	
	}

	@Override
	public HospitalTimeVO selectOne(int no) {
		return session.selectOne(namespace + ".selectOne", no);
	}

	@Override
	public HospitalTimeVO selectByDow(String h_date) {
		return session.selectOne(namespace + ".selectByDow", h_date);
	}

	@Override
	public void insert(HospitalTimeVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public void update(HospitalTimeVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace + "delete", no);
	}

}
