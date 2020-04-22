package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.MediaVO;
import com.webaid.domain.SearchCriteria;

@Repository
public class MediaDaoImpl implements MediaDao {

	private static final String namespace = "com.webaid.mappers.MediaMapper";

	@Autowired
	private SqlSession session;

	@Override
	public List<MediaVO> selectAll() {
		return session.selectList(namespace + ".selectAll");
	}

	@Override
	public MediaVO selectOne(int no) {
		return session.selectOne(namespace + ".selectOne", no);
	}

	@Override
	public MediaVO selectBefore(int no) {
		return session.selectOne(namespace + ".selectBefore", no);
	}

	@Override
	public MediaVO selectAfter(int no) {
		return session.selectOne(namespace + ".selectAfter", no);
	}

	@Override
	public void insert(MediaVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public void update(MediaVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void updateCnt(int no) {
		session.update(namespace + ".updateCnt", no);
	}

	@Override
	public void updateUseState(MediaVO vo) {
		session.update(namespace + "updateUseState", vo);
	}

	@Override
	public void updateThumb(MediaVO vo) {
		session.update(namespace + ".updateThumb", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace + ".delete", no);
	}

	@Override
	public List<MediaVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public List<MediaVO> listSearchAll(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearchAll", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public int listSearchCountAll(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCountAll", cri);
	}

}
