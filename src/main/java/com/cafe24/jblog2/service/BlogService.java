package com.cafe24.jblog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.BlogDao;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	public void create(UserVo vo) {

		BlogVo blogVo = new BlogVo();
		blogVo.setNo(vo.getNo());
		blogVo.setTitle(vo.getName() + "의 블로그");
		blogVo.setLogoPath("아직미정");
		blogVo.setIntroduction("소개글을 작성하세요.");
		blogDao.insert(blogVo);
	}

	public BlogVo get(Long no) {
		return blogDao.get(no);
	}
	public BlogVo get(UserVo vo) {
		return blogDao.get(vo.getNo());
	}
	public boolean update(BlogVo vo) {
		return blogDao.update(vo);
	}
	
}
