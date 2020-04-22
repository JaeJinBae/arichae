package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.SearchCriteria;
import com.webaid.domain.StatisticSelectDateVO;
import com.webaid.domain.StatisticVO;

@Repository
public class StatisticDaoImpl implements StatisticDao {

	private static final String namespace = "com.webaid.mappers.StatisticMapper";

	@Autowired
	private SqlSession session;

	@Override
	public List<StatisticVO> selectByDate(StatisticSelectDateVO vo) {
		return session.selectList(namespace + ".selectByDate", vo);
	}

	@Override
	public List<StatisticVO> listSearch(SearchCriteria cri) {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public void insert(StatisticVO vo) {
		session.insert(namespace + ".insert", vo);
	}

}
