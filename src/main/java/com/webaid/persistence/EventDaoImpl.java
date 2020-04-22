package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.EventVO;
import com.webaid.domain.SearchCriteria;

@Repository
public class EventDaoImpl implements EventDao {

	private static final String namespace = "com.webaid.mappers.EventMapper";

	@Autowired
	private SqlSession session;

	@Override
	public List<EventVO> selectAll() {
		return session.selectList(namespace + ".selectAll");
	}

	@Override
	public EventVO selectOne(int no) {
		return session.selectOne(namespace + ".selectOne", no);
	}

	@Override
	public List<EventVO> selectByDate(String select_date) {
		return session.selectList(namespace + ".selectByDate", select_date);
	}

	@Override
	public void insert(EventVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public void update(EventVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace + ".delete", no);
	}

	@Override
	public List<EventVO> listSearch(SearchCriteria cri) {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

}
