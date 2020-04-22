package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.ReservationVO;
import com.webaid.domain.SearchCriteria;

@Repository
public class ReservationDaoImpl implements ReservationDao {

	private static final String namespace = "com.webaid.mappers.ReservationMapper";
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<ReservationVO> selectAll() {
		return session.selectList(namespace+".selectAll");
	}

	@Override
	public ReservationVO selectOne(int no) {
		return session.selectOne(namespace+".selectOne", no);
	}

	@Override
	public void insert(ReservationVO vo) {
		session.insert(namespace+".insert", vo);
	}

	@Override
	public void update(ReservationVO vo) {
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace+".delete", no);
	}

	@Override
	public List<ReservationVO> listSearch(SearchCriteria cri) {
		return session.selectList(namespace+".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return session.selectOne(namespace+".listSearchCount", cri);
	}

}
