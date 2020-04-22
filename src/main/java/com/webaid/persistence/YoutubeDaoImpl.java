package com.webaid.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webaid.domain.SearchCriteria;
import com.webaid.domain.YoutubeVO;

@Repository
public class YoutubeDaoImpl implements YoutubeDao {

	private static final String namespace = "com.webaid.mappers.YoutubeMapper";

	@Autowired
	private SqlSession session;
	
	
	@Override
	public List<YoutubeVO> selectAll() {
		return session.selectList(namespace+".selectAll");
	}

	@Override
	public YoutubeVO selectOne(int no) {
		return session.selectOne(namespace+".selectOne", no);
	}

	@Override
	public YoutubeVO selectBefore(int no) {
		return session.selectOne(namespace+".selectBefore", no);
	}

	@Override
	public YoutubeVO selectAfter(int no) {
		return session.selectOne(namespace+".selectAfter", no);
	}

	@Override
	public void insert(YoutubeVO vo) {
		session.insert(namespace+".insert", vo);
	}

	@Override
	public void update(YoutubeVO vo) {
		session.update(namespace+".update", vo);
	}

	@Override
	public void updateCnt(int no) {
		session.update(namespace+".updateCnt", no);
	}

	@Override
	public void updateUseState(YoutubeVO vo) {
		session.update(namespace+".updateUseState", vo);
	}

	@Override
	public void updateThumb(YoutubeVO vo) {
		session.update(namespace+".updateThumb", vo);
	}

	@Override
	public void delete(int no) {
		session.delete(namespace+".delete", no);
	}

	@Override
	public List<YoutubeVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace+".listSearch", cri);
	}

	@Override
	public List<YoutubeVO> listSearchAll(SearchCriteria cri) throws Exception {
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
