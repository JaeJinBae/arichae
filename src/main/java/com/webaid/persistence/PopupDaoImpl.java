package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.PopupVO;
import com.webaid.domain.SearchCriteria;

@Repository
public class PopupDaoImpl implements PopupDao {

	private static final String namespace = "com.webaid.mappers.PopupMapper";

	@Autowired
	private SqlSession session;

	@Override
	public List<PopupVO> selectAll() {
		return session.selectList(namespace + ".selectAll");
	}

	@Override
	public PopupVO selectOne(int no) {
		return session.selectOne(namespace + ".selectOne", no);
	}

	@Override
	public List<PopupVO> selectByDate(String select_date) {
		return session.selectList(namespace + ".selectByDate", select_date);
	}

	@Override
	public void insert(PopupVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public void update(PopupVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void updateUseState(PopupVO vo) {
		session.update(namespace + ".updateUseState", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace + ".delete", no);
	}

	@Override
	public List<PopupVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

}
