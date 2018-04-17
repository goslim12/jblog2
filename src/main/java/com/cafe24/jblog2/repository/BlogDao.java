package com.cafe24.jblog2.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.UserVo;
@Repository
public class BlogDao extends DBConnection {
	@Autowired
	private SqlSession sqlSession;
	public BlogVo get(Long no){
		return sqlSession.selectOne("blog.getByNo",no);
	}
	public boolean insert(BlogVo vo) {
		int count = sqlSession.insert("blog.insert",vo);
		return count ==1;
	}
	public boolean update(BlogVo vo){
		return 1==sqlSession.update("blog.update",vo);
	
	}
	
}
