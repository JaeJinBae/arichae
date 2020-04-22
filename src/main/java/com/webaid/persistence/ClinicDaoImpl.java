package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.ClinicVO;
import com.webaid.domain.SearchCriteria;

@Repository
public class ClinicDaoImpl implements ClinicDao {

	private static final String namespace = "com.webaid.mappers.ClinicMapper";

	@Autowired
	private SqlSession session;

	@Override
	public List<ClinicVO> selectAll() {
		return session.selectList(namespace + ".selectAll");
	}

	@Override
	public ClinicVO selectOne(int no) {
		return session.selectOne(namespace + ".selectOne", no);
	}

	@Override
	public List<ClinicVO> selectByKind1(int kind1) {
		return session.selectList(namespace + ".selectByKind1", kind1);
	}

	@Override
	public void insert(ClinicVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public void update(ClinicVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace + ".delete", no);
	}

	@Override
	public List<ClinicVO> listSearch(SearchCriteria cri) {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

}
