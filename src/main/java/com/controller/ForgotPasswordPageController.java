package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.ChangePasswordService;


@Controller
public class ForgotPasswordPageController {

	
	@Autowired
	ChangePasswordService changeSer;
	
	@RequestMapping("/forgotpasswordpage")
	public String forgotPasswordPageController() {  // forgot password UI
		return "forgotpasswordpage";
	}
	
	
	// username confirm user
	@RequestMapping(value="/forgotcheckusername", method=RequestMethod.POST)
	@ResponseBody
	public String checkUsernameForgotPass(HttpServletRequest request) {
		String username=(String)request.getParameter("username").trim();
		//check username valid or not
		int result=changeSer.checkUsername(username);
		String str="";
		if(result!=0) {
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);
			
			str=str+"<div class='emaildiv' style='display:block;' id='emaildiv'>";
			str=str+"<h3>Confirm your email</h3>";
			str=str+"<input type='email' id='email' placeholder='Enter Your Email'><br>";
			str=str+"<div id='msgemail' class='msg'></div>";
			str=str+"<button type='submit' name='emailconfirmbtn' id='emailconfirmbtn' onclick='checkConfirmEmail()' >Confirm</button><br>";
			str=str+"<form action='loginpage' method='post'>";
			str=str+"<button type='submit'  id='cancelbtn'>Cancel</button><br>";
			str=str+"</form>";
			str=str+"</div>";
		}else {
			str=str+"<h3>Forgot Password</h3>";
			str=str+"<input type='text' name='username' id='username' placeholder='Enter Username' required><br>";
			str=str+"<div id='msgusername' class='msg'>Invalid username. Please try again</div>";
			str=str+"<button type='submit' name='usernamenextbtn' id='usernamenextbtn' onclick='checkUsername()' >Next</button><br>";
			str=str+"<form action='loginpage' method='post'>";
			str=str+"<button type='submit' id='cancelbtn' >Cancel</button><br>";
			str=str+"</form>";
		}
		return str;
	}
	
	
	
	// check email valid or not
	@RequestMapping(value="/forgotcheckemail", method=RequestMethod.POST)
	@ResponseBody
	public String checkEmailForgotPass(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("username");
		String email=(String)request.getParameter("email");
		// check user email valid or not
		int emailResult = changeSer.checkEmail(email, username);
		String str="";
		if(emailResult!=0) {
			session.setAttribute("forgotregisterid", emailResult);
			
			str=str+"<div class='sendotpdiv' style='display:block;' id='sendotpdiv'>";
			str=str+"<h3>Where should we send a confirmation code</h3>";
			str=str+"<input type='hidden' value='"+email+"' id='email'><br>";
			str=str+"<h5>Send an email to "+email+"</h5>";
			str=str+"<button type='submit' name='sendotpbtn' id='sendotpbtn' onclick='checkOTP()'  >Send OTP</button><br>";
			str=str+"</div>"; // sendotpdiv
			
		}else {
			str=str+"<h3>Confirm your email</h3>";
			str=str+"<input type='email' id='email' placeholder='Enter Your Email'><br>";
			str=str+"<div id='msgemail' class='msg'>Invalid email. Please try again</div>";
			str=str+"<button type='submit' name='emailconfirmbtn' id='emailconfirmbtn' onclick='checkConfirmEmail()' >Send OTP</button><br>";
			str=str+"<form action='loginpage' method='post'>";
			str=str+"<button type='submit'  id='cancelbtn'>Cancel</button><br>";
			str=str+"</form>";
		}
		return str;
	}
	
	
	
	// retype otp when wrong
	@RequestMapping(value="/forgotcheckotp", method=RequestMethod.POST)
	@ResponseBody
	public String reEnterOtpForgotPass() {
		String str="";
		str=str+"<h3>Check your email to get your confirmation code</h3>";
		str=str+"<input type='text' id='otpinput'  placeholder='Enter OTP code'><br>";
		str=str+"<div id='msgotp' class='msg'>Invalid OTP. Please enter correct OTP</div>";
		str=str+"<button type='submit' name='otpverifybtn' id='otpverifybtn'  >Confirm</button><br>";
		return str;
	}
	
	
	// forgot password
	@RequestMapping(value="/forgotpassword", method=RequestMethod.POST)
	@ResponseBody
	public String forgotPasswordUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int registerid = Integer.parseInt(session.getAttribute("forgotregisterid").toString());
		String pass=(String)request.getParameter("pass");
		int result = changeSer.changeUserPassword(pass, registerid);
		String str="";
		str=str+"<div class='closetab'>";
		str=str+"<a href='loginpage'><i class='fa-solid fa-xmark'></i></a>";
		str=str+"</div>"; //closetab
		str=str+"<div class='usernamediv' id='usernamediv'>";
		str=str+"<h3>Forgot Password</h3>";
		str=str+"<input type='text' name='newpass' id='newpass' placeholder='New Password' required><br>";
		str=str+"<input type='text' name='retypepass' id='retypepass' placeholder='Retype New Password' required><br>";
		str=str+"<div id='msg' class='msg'style='color:green; font-weight:20px;' >Password forgot successfully</div>";
		str=str+"<form action='loginpage' method='post'>";
		str=str+"<button type='submit' name='forgot' id='forgot' >Logout</button>";
		str=str+"</form>";
		return str;
	}
	
}

