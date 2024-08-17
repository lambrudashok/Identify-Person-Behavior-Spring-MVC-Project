package com.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.LoginModel;
import com.service.AdminService;
import com.service.UserLoginService;


@Controller
public class LoginPageController {

	@Autowired
	UserLoginService loginSer;
	
	@Autowired
	AdminService adminSer;
	
	
	@RequestMapping("/")
	public String loginPageController() {          // index page (loading page UI)

		return "loginpage";
	}
	
	
	@RequestMapping("/validation")
	public String checkLogin(HttpServletRequest request) {
		
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
				return "userhomepage";  // call userloginpage.jsp
				
			
			}else {

				return "loginpage";  // call loginpage.jsp
			}
		}
	}
	

	
	
	
}
