package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.PostDao;
import com.cafe24.jblog2.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;


	public PostVo get(Long no) {
		return postDao.get(no);
	}
	public List<PostVo> getListByBlogNo(Long no) {
		return postDao.getListByBlogNo(no);
	}
	public int insert(PostVo vo) {
		return postDao.insert(vo);
	}
//	public boolean update(BlogVo vo) {
//		return postDao.update(vo);
//	}
	
}
