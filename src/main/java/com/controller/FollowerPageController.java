package com.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.UserInfoModel;
import com.service.FollowingService;

@Controller
public class FollowerPageController {

	
	@Autowired
	FollowingService followingSer;
	
	@RequestMapping("/followerpage")
	public String getFollowerPage(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		List <UserInfoModel> list =followingSer.fetchAllFollowerUser(userID);
		model.addAttribute("followerlist", list);
		
		return "followerpage";
	}
}
