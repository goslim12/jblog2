package com.cafe24.jblog2.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.CategoryVo;
@Repository
public class CategoryDao extends DBConnection {
	@Autowired
	private SqlSession sqlSession;
	public boolean insert(CategoryVo vo) {
		int count = sqlSession.insert("category.insert",vo);
		return count ==1 ;
	}
	public List<CategoryVo> getList(Long no){
		return sqlSession.selectList("category.getList", no);
	}
	public CategoryVo get(Long no){
		return sqlSession.selectOne("category.get",no);
	}
	public int update(CategoryVo vo){
		return sqlSession.update("category.update",vo);
	}
	public boolean remove(Long no){
		return 1 == sqlSession.delete("category.remove",no);
	}
}
