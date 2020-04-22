package com.webaid.service;

import java.util.List;

import com.webaid.domain.MediaVO;
import com.webaid.domain.SearchCriteria;

public interface MediaService {
	public List<MediaVO> selectAll();
	public MediaVO selectOne(int no);
	public MediaVO selectBefore(int no);
	public MediaVO selectAfter(int no);
	public void insert(MediaVO vo);
	public void update(MediaVO vo);
	public void updateCnt(int no);
	public void updateUseState(MediaVO vo);
	public void updateThumb(MediaVO vo);
	public void delete(int no);
	public List<MediaVO> listSearch(SearchCriteria cri) throws Exception;
	public List<MediaVO> listSearchAll(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	public int listSearchCountAll(SearchCriteria cri) throws Exception;
}
