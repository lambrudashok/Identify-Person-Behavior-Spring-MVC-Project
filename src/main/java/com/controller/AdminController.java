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
import com.model.NotificationModel;
import com.model.PostLayoutModel;
import com.model.RegistrationModel;
import com.model.ReportProblemModel;
import com.service.AdminService;
import com.service.UserRegistrationService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminSer;
	
	@Autowired
	UserRegistrationService userSer;
	
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
	
	
	//freeze user account
	@RequestMapping(value="/freezeaccountuser", method = RequestMethod.POST)
	@ResponseBody
	public String getfreezeUserAccount(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		int freeze = adminSer.freezeUserAccount(userid); // freeze user account	
		String status = adminSer.checkFreezeUser(userid); // check user account freeze or not
		String str="";
		if(status.compareTo("freeze")==0){
			str=str+"<div id='unfreezediv'>";
			str=str+"<button name='unfreeze' id='unfreeze' onclick='unFreezeUserFun("+userid+")' >UnFreeze</button>";            
			str=str+"</div>";
		}else{
			str=str+"<div id='freezediv'>";
			str=str+"<button name='freeze' id='freeze' onclick='freezeUserFun("+userid+")'  >Freeze</button>";            
			str=str+"</div>";
		}
		return str;
	}
	
	
	// unfreeze user account
	@RequestMapping(value="/unfreezeaccountuser", method = RequestMethod.POST)
	@ResponseBody
	public String getunfreezeUserAccount(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		int freeze = adminSer.unFreezeUserAccount(userid); // unfreeze user account	
		String status = adminSer.checkFreezeUser(userid); // check user account freeze or unfreeze
		String str="";
		if(status.compareTo("freeze")==0){
			str=str+"<div id='unfreezediv'>";
			str=str+"<button name='unfreeze' id='unfreeze' onclick='unFreezeUserFun("+userid+")' >UnFreeze</button>";            
			str=str+"</div>";
		}else{
			str=str+"<div id='freezediv'>";
			str=str+"<button name='freeze' id='freeze' onclick='freezeUserFun("+userid+")'  >Freeze</button>";            
			str=str+"</div>";
		}
		return str;
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
		str=str+"<div id='freezeGrid"+userid+"'>";
		String status=info.getStatus();
		if(status.compareTo("freeze")==0){
			str=str+"<div id='unfreezediv'>";
			str=str+"<button name='unfreeze' id='unfreeze' onclick='unFreezeUserFun("+userid+")' >UnFreeze</button>";            
			str=str+"</div>";
		}else{
			str=str+"<div id='freezediv'>";
			str=str+"<button name='freeze' id='freeze' onclick='freezeUserFun("+userid+")'  >Freeze</button>";           
			str=str+"</div>";	
		}
		str=str+"</div>"; // freezeGrid
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
	
	// delete account request user
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
	
	
	
	// reports admin section
	@RequestMapping("/reportproblemadminpage")
	public String getReportsAdminPage(Model model) {
		List<ReportProblemModel> list = adminSer.getReportsUser();
		model.addAttribute("list", list);
		return "reportproblemadminpage";
	}
	
	//pending reported problem solve then
	@RequestMapping(value="/solvependingreport",method=RequestMethod.POST)
	@ResponseBody
	public String solveReportProblemUser(HttpServletRequest request) {
		int reportid =Integer.parseInt(request.getParameter("reportid"));
		String title = request.getParameter("title");
		int registerid = Integer.parseInt(request.getParameter("registerid"));
		int result=adminSer.solveReportProblemStatus(reportid);  // solve problem
		
		//solve problem user notification send
		NotificationModel nmodel = new NotificationModel(); 
		nmodel.setNotification("'"+title+"'"+", Your reported issue has been resolved. Thank you for helping us improve!");
		nmodel.setRegisterid(registerid);
		boolean b=userSer.isAddNotification(1, nmodel); // send notification user
		
		String str="";
		String status=adminSer.checkReportProblem(reportid);
		if(status.compareTo("pending")==0){
			str=str+"<div id='pendingdiv'>";
			str=str+"<input type='submit' value='pending' name='pending' id='pending' onclick='pendingReportFun("+reportid+",'"+title+"','"+registerid+"')' >";            
			str=str+"</div>";
		}else{
			str=str+"<div id='solvediv'>";
			str=str+"<button name='solve' id='solve'  >Solve</button>";           
			str=str+"</div>";	
		}
		return str;
	}
	
}
