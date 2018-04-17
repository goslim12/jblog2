package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.CategoryDao;
import com.cafe24.jblog2.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	public List<CategoryVo> getList(Long no) {
		return categoryDao.getList(no);
	}
	public CategoryVo getByNo(Long no) {
		return categoryDao.get(no);
	}
	public int update(CategoryVo vo) {
		return categoryDao.update(vo);
	}
	

	public boolean insert(CategoryVo vo) {
		return categoryDao.insert(vo);
	}
	public boolean remove(CategoryVo vo) {
		return categoryDao.remove(vo.getNo());
	}
//	public boolean update(BlogVo vo) {
//		return categoryDao.update(vo);
//	}
	
}
