package com.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.LoginModel;
import com.service.AdminService;
import com.service.CreatePostService;
import com.service.FollowingService;
import com.service.SearchService;
import com.service.UserLoginService;
import com.model.*;
import java.util.*;

@Controller
public class LoginPageController {

	@Autowired
	UserLoginService loginSer;
	
	@Autowired
	CreatePostService postSer;
	
	@Autowired
	FollowingService followSer;
	
	@Autowired
	SearchService searchSer;
	
	@Autowired
	AdminService adminSer;
	
	
	@RequestMapping("/")
	public String loginPageController() {          // index page (loading page UI)
		return "loginpage";
	}
	
	
	@RequestMapping("/validation")
	public String checkLogin(HttpServletRequest request, Model mod) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		LoginModel model = new LoginModel();
		model.setUsername(username);
		model.setPassword(password);
		
		int adminID = adminSer.checkAdminLogin(model);
		if(adminID!=-1) {
			// Admin logics
			return "adminpage"; // adminpage.jsp
			
		}else {
			// user logics        
			int userID = loginSer.checkUserLogin(model);
			if(userID!=-1) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userID", userID);
				model.setLoginid(userID);
				boolean res=loginSer.isAddUserLogin(model); // add user login details
				
        		return getHomePage(request, mod); // call getHomePage
			}else {
				return "loginpage";  // call loginpage.jsp
			}
		}
	}
	
	
	
	// home page logic loading
	@RequestMapping("/userhomepage")
	public String getHomePage(HttpServletRequest request, Model model) {
		HttpSession session  = request.getSession(false);
		int userID =Integer.parseInt(session.getAttribute("userID").toString());
	
   		List<PostLayoutModel> postlist= postSer.ViewAllApplicationPosts(userID); // for you home page
   		model.addAttribute("postlist", postlist);
		List<Integer> al=followSer.followingUserIDs(userID);// fetch all following users id home page
		List<PostLayoutModel> listPosts=postSer.fetchFollowingAllUserPost(al,userID);  // fetch following users details home page
		model.addAttribute("listPosts", listPosts);
		
		List <UserInfoModel> list = searchSer.fetchAllUserDetails(userID); // fetch search details search home page
		model.addAttribute("list", list);
		
		return "userhomepage"; // userhomepage.jsp
	}
	
	

	
	
	
}
