package com.biz.bbs.dao;

import java.util.List;

import com.biz.bbs.vo.BBsVO;

public interface BBsMainDao {

	public void insert(BBsVO vo);
	public List<BBsVO> selectAll();
	public BBsVO fintByID(long id);
	public void update(BBsVO vo);
	public void delete(long id);
	
}
