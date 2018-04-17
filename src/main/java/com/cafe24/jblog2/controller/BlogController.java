package com.cafe24.jblog2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.service.CategoryService;
import com.cafe24.jblog2.service.FileUploadService;
import com.cafe24.jblog2.service.PostService;
import com.cafe24.jblog2.service.UserService;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.PostVo;
import com.cafe24.jblog2.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

//@RequestMapping("/{id:.*}")
@Controller
@RequestMapping("/{id:(?!assets|uploads).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	
	@RequestMapping({"","/{path1}","/{path1}/{path2}"})
	public String myBlog(
			@PathVariable("id") String userId,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2,
			Model model
			) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		UserVo userVo = userService.get(userId);
		BlogVo blogVo = blogService.get(userVo.getNo());
		List<CategoryVo> cateList = categoryService.getList(userVo.getNo());
//		for(CategoryVo vo : cateList) {
//			System.out.println(vo);
//		}
		System.out.println("!!?");
		List<PostVo> postList = postService.getListByBlogNo(userVo.getNo());
//		
		model.addAttribute("postList",postList);
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("cateList",cateList);
		
		if(path2.isPresent()) {
			postNo=path2.get();
			categoryNo = path1.get();
		}else if(path1.isPresent()) {
			postNo = path1.get();
		}
//		if(postNo)

		return "blog/blog-main";
	}
	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping({"/admin/{path1}"})
	public String blogAdmin(
			Model model,
			@PathVariable("id") String userId,
			@PathVariable("path1") String path1,
			@AuthUser UserVo authUser
//			@RequestParam(value="logo-file",required=false, defaultValue="")MultipartFile multipartFile
			) {
		UserVo userVo = userService.get(userId);
		BlogVo blogVo = blogService.get(userVo.getNo());
		model.addAttribute("blogVo",blogVo);
		
		if("basic".equals(path1)) {
			return "blog/blog-admin-basic";
		}
		if("category".equals(path1)) {
			List<CategoryVo> list = categoryService.getList(userVo.getNo());
			model.addAttribute("list",list);
			return "blog/blog-admin-category";
		}
		if("write".equals(path1)) {
			List<CategoryVo> list = categoryService.getList(userVo.getNo());
			model.addAttribute("list",list);
			return "blog/blog-admin-write";
		}
//		if("upload".equals(path1)) {
//			String url = fileUploadService.restore(multipartFile);
//			model.addAttribute("url",url);
//			return "blog/userId";
//		}
		
		return "blog/blog-admin-basic";
	}
	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping(value="/admin/upload" , method=RequestMethod.POST)
	public String blogAdmin(
			Model model,
			@PathVariable("id") String userId,
			@AuthUser UserVo authUser,
			@RequestParam(value="title",required=false)String title,
			@RequestParam(value="logo-file",required=false)MultipartFile multipartFile
			) {
				
					
				UserVo userVo = userService.get(userId);
				BlogVo blogVo = blogService.get(userVo.getNo());
				String url =null;
				if(multipartFile.isEmpty()) {
					url= blogVo.getLogoPath();
				}else {
					url = fileUploadService.restore(multipartFile);
				}
	
				blogVo.setTitle(title);
				blogVo.setLogoPath(url);
				System.out.println(blogVo);
				blogService.update(blogVo);
				return "redirect:/"+userId;
	}
	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping(value="/admin/write" , method=RequestMethod.POST)
	public String blogAdmin(
			Model model,
			@AuthUser UserVo authUser,
			@RequestParam(value="category",required=false)Long categoryNo,
			@RequestParam(value="title",required=false)String title,
			@RequestParam(value="content",required=false)String content
			) {
		CategoryVo categoryVo = categoryService.getByNo(categoryNo);
				System.out.println("categoryNo : " + categoryNo);
				System.out.println("title : " +title);
				System.out.println("content: "+content);
				PostVo vo = new PostVo();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setCategoryNo(categoryNo);
				vo.setBlogNo(categoryVo.getBlogNo());
				if(postService.insert(vo)==1) {
					categoryVo.setPostCount(categoryVo.getPostCount()+1);
					categoryService.update(categoryVo);
				}
				return "redirect:/"+authUser.getId();
	}
	
	@Auth(role=Auth.Role.ADMIN)
	@ResponseBody()
	@RequestMapping(value="api/admin/category" , method=RequestMethod.POST)
	public CategoryVo categoryAdd(
			Model model,
			@PathVariable("id") String userId,
			@AuthUser UserVo authUser,
			@RequestParam(value="categoryName")String categoryName,
			@RequestParam(value="categoryDesc")String categoryDesc			
			) {
		CategoryVo vo = new CategoryVo();
		vo.setName(categoryName);
		vo.setDescription(categoryDesc);
		vo.setPostCount(0l);
		vo.setBlogNo(authUser.getNo());
		categoryService.insert(vo);
		
		
		return vo;
	}
	
	@Auth(role=Auth.Role.ADMIN)
	@ResponseBody()	
	@RequestMapping(value="api/admin/category-rmv" , method=RequestMethod.POST)
	public CategoryVo categoryRemove(
			Model model,
			@PathVariable("id") String userId,
			@AuthUser UserVo authUser,
			@RequestParam(value="no") Long no 
//			@RequestParam(value="categoryDesc")String categoryDesc			
			) {
		CategoryVo vo = new CategoryVo();
		vo.setNo(no);
		categoryService.remove(vo);
		
		return vo;
	}
	
	
}
