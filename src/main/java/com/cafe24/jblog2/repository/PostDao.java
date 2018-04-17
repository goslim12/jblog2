package com.cafe24.jblog2.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.PostVo;
@Repository
public class PostDao extends DBConnection {
	@Autowired
	private SqlSession sqlSession;
	public Long getCountByCategoryNo(Long no) {
		return sqlSession.selectOne("post.getCountByCategoryNo", no);
	}
	public int insert(PostVo vo) {
		return sqlSession.insert("post.insert", vo);
	}
	public PostVo get(Long no) {
		return sqlSession.selectOne("post.get", no);
	}
	public List<PostVo> getListByBlogNo(Long no) {
		return sqlSession.selectList("post.getListByBlogNo", no);
	}
}
