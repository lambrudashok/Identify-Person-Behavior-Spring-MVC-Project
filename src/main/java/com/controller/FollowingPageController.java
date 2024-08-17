package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.UserInfoModel;
import com.service.FollowingService;


@Controller
public class FollowingPageController {

	@Autowired
	FollowingService followingSer;
	
	
	
	@RequestMapping("/followingpage")
	public String getFollowingPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		List <UserInfoModel> list =followingSer.fetchAllFollowingUser(userID);
		model.addAttribute("list", list);
		return "followingpage";
	}
	
	
	@RequestMapping(value="/userfollowing",method=RequestMethod.POST)
	public String userUnfollow(HttpServletRequest request,Model model) {
		
		HttpSession session = request.getSession(false);
		int registerId = Integer.parseInt(session.getAttribute("userID").toString());
		int followingUserID = Integer.parseInt(request.getParameter("following"));
		
		followingSer.removeFollowingUser(followingUserID, registerId); // unFollow particular user 
		return getFollowingPage(request, model);  // recalling following page
		
	}
	
	
	
	
	
	
}
