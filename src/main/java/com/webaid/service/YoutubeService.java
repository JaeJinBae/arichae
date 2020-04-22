package com.webaid.service;

import java.util.List;

import com.webaid.domain.SearchCriteria;
import com.webaid.domain.YoutubeVO;

public interface YoutubeService {
	public List<YoutubeVO> selectAll();
	public YoutubeVO selectOne(int no);
	public YoutubeVO selectBefore(int no);
	public YoutubeVO selectAfter(int no);
	public void insert(YoutubeVO vo);
	public void update(YoutubeVO vo);
	public void updateCnt(int no);
	public void updateUseState(YoutubeVO vo);
	public void updateThumb(YoutubeVO vo);
	public void delete(int no);
	public List<YoutubeVO> listSearch(SearchCriteria cri) throws Exception;
	public List<YoutubeVO> listSearchAll(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	public int listSearchCountAll(SearchCriteria cri) throws Exception;
}
