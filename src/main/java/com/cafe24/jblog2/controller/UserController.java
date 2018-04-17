package com.cafe24.jblog2.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog2.repository.UserDao;
import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.service.UserService;
import com.cafe24.jblog2.vo.UserVo;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		blogService.create(vo);
		return "redirect:/user/joinsuccess";
	}
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

//	@RequestMapping(value = "/auth", method = RequestMethod.POST)
//	public String login(@AuthUser UserVo vo,Model model) {
//
//		UserVo authUser = userService.login(vo);
//		//		UserVo authUser = userDao.get(vo.getId(),vo.getPassword());
////		model.addAttribute("authUser",authUser);
//		return "main/index";
//	}
	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "main/index";
//	}
}
