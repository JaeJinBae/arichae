package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.Category1VO;
import com.webaid.domain.SearchCriteria;

@Repository
public class Category1DaoImpl implements Category1Dao {

	private static final String namespace = "com.webaid.mappers.Category1Mapper";
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Category1VO> selectAll() {
		return session.selectList(namespace+".selectAll");
	}

	@Override
	public Category1VO selectOne(int no) {
		return session.selectOne(namespace+".selectOne", no);
	}

	@Override
	public void insert(Category1VO vo) {
		session.insert(namespace+".insert", vo);
	}

	@Override
	public void update(Category1VO vo) {
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace+".delete", no);
	}

	@Override
	public List<Category1VO> listSearch(SearchCriteria cri) {
		return session.selectList(namespace+".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return session.selectOne(namespace+".listSearchCount", cri);
	}

}
