package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.LoginModel;
import com.model.PostLayoutModel;
import com.model.RegistrationModel;
import com.service.AdminService;

@Controller
public class AdminController {

	
	@Autowired
	AdminService adminSer;
	
	
	@RequestMapping("/adminpage")
	public String getAdminPage() {
		return "adminpage";
	}
	
	
	@RequestMapping("/userdetailsadminpage")
	public String getuserDetailsAdminPage(HttpServletRequest request, Model model) {
		List<RegistrationModel> list =adminSer.fetchAllUserDetails();
		model.addAttribute("list", list);
		return "userdetailsadminpage";
	}
	
	
	@RequestMapping(value="/userdeleteadmin", method=RequestMethod.POST)
	@ResponseBody
	public String userDeleteAdmin(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		int result=adminSer.deleteUser(userid);
		List<RegistrationModel> list =adminSer.fetchAllUserDetails();
		String str="";
		for(RegistrationModel info:list){
		str=str+"<div class='details'>";
		str=str+"<div id='userid'>"+info.getRegisterid()+"</div>";
		str=str+"<div id='photo'><img alt='' src='resources/Profile_Images/"+info.getProfileimgname()+"'></div>";
		str=str+"<div id='cname'>"+info.getCustomername()+"</div>";
		str=str+"<div id='email'>"+info.getEmail()+"</div>";
		str=str+"<div id='username'>"+info.getUsername()+"</div>";
		str=str+"<div id='password'>"+info.getPassword()+"</div>";
		str=str+"<div id='delete'><a onclick='userDelete("+info.getRegisterid()+")'>Delete</a></div>";
		str=str+"</div>"; //details
		}
		return str;
	}
	
	
	@RequestMapping("/userpostsadminpage")
	public String getPostsAdminPage(HttpServletRequest request,Model model) {
		List<PostLayoutModel> list =adminSer.ViewAllUserPosts();
		model.addAttribute("list", list);
		return "userpostsadminpage";
	}
	
	
	@RequestMapping("/deleterequestadminpage")
	public String getDeleteRequestAdminPage(Model model) {
		List<RegistrationModel> list =adminSer.fetchDeleteUserAccountReuests();
		model.addAttribute("list", list);
		return "deleterequestadminpage";
	}
	
	
	@RequestMapping(value="/deleteaccountrequest" ,method=RequestMethod.POST)
	@ResponseBody
	public String deleteUserAccountRequested(HttpServletRequest request) {
		int userid=Integer.parseInt(request.getParameter("userid"));
		int result=adminSer.deleteUserRequestAccount(userid); // delete user account requested
		List<RegistrationModel> list =adminSer.fetchDeleteUserAccountReuests();
		String str ="";
		if(list!=null){
			
			str=str+"<h2>User Requests Found</h2>";
			str=str+"<div class='columnname'>";
			str=str+"<div id='columnid'>RegisterId</div>";
			str=str+"<div id='columnphoto'>Profile Photo</div>";
			str=str+"<div id='columnnam'>Name</div>";
			str=str+"<div id='columnusername'>UserName</div>";
			str=str+"<div id='columnarrive'>Arrive Date</div>";
			str=str+"<div id='columnremaining'>Remaining Days</div>";
			str=str+"<div id='columnother'>Others</div>";
			str=str+"</div>";//columname
			
			for(RegistrationModel info:list){
			
				str=str+"<div class='details'>";
				str=str+"<div id='userid'>"+info.getRegisterid()+"</div>";
				str=str+"<div id='photo'><img alt='' src='resources/Profile_Images/"+info.getProfileimgname()+"'></div>";
				str=str+"<div id='cname'>"+info.getCustomername()+"</div>";
				str=str+"<div id='username'>"+info.getUsername()+"</div>";
				str=str+"<div id='arivedate'>"+info.getDate()+"</div>";
				str=str+"<div id='remainingdays'>days : "+info.getRemain()+"</div>";
				str=str+"<div id='delete'><a onclick='deleteAccountRequestUser("+info.getRegisterid()+")'>Confirm</a></div>";
				str=str+"</div>";// details
			}
		}else{
			str=str+"<h2>Account Delete Requests Not Found</h2>";
		}
		return str;
	}
	
	
	@RequestMapping("/logindetailsadminpage")
	public String getLoginInfoUsers(Model model) {
		List<LoginModel> list =adminSer.viewUserLoginDetails();
		model.addAttribute("list", list);
		return "logindetailsadminpage";
	}
	
}
