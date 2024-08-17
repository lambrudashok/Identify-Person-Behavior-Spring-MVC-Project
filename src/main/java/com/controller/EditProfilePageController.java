package com.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.RegistrationModel;
import com.model.UserInfoModel;
import com.service.UserRegistrationService;

@Controller
public class EditProfilePageController {

	@Autowired
	UserRegistrationService userSer;
	
	
	@RequestMapping("/editprofilepage")
	public String getEditProfile(HttpServletRequest request, Model model) {
		
		HttpSession session  = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		UserInfoModel info =userSer.getUserInfo(userID);
		model.addAttribute("updatedata", info);
		
		return "editprofilepage";
	}
	
	
	// update profile image
	@RequestMapping(path="/updateprofilephoto", method=RequestMethod.POST)
	public String submitPost(HttpServletRequest request, @RequestParam("chooseprofile") CommonsMultipartFile file, Model model) {
		
		try {
			
		  String fileName= file.getOriginalFilename(); // fetch image file name
		 
		  // access user id from session
		  HttpSession session = request.getSession(false);
		  int userID = Integer.parseInt(session.getAttribute("userID").toString());
		  
		  RegistrationModel photo = new RegistrationModel();
		  photo.setProfileimgname(fileName); //set image name
		  photo.setRegisterid(userID);
		
		  boolean b= userSer.isAddProfilePhoto(photo);  // add profile image name in database
		  
			 // upload photo in server (server as a folder)
			 String path = request.getServletContext().getRealPath("") +"resources\\Profile_Images";
			 
			 byte[] bytes =file.getBytes();  // convert image to bytes
			 FileOutputStream f = new FileOutputStream(path + File.separator +fileName);
			 f.write(bytes);
			 f.close();
			
			 return getEditProfile(request,model); 
		 
		}catch(Exception e) {
			return "editprofilepage";
		}
	}
	
	
	
	// edit or update another information name , username , email , bio
	@RequestMapping("/updateprofile")
	@ResponseBody
	public String setUpdateProfile(HttpServletRequest request , Model model) {
		
		String name=(String)request.getParameter("name");
		String username=(String)request.getParameter("username");
		String email=(String)request.getParameter("email");
		String bio=(String)request.getParameter("bio");
		
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		
		// declaration
		boolean usernameResult=false,emailResult=false,bioResult=false; 
		int done=0;
		String usernameError="",emailError="",allMsg="";
		
		if(!username.isEmpty()) {
			usernameResult=userSer.searchUsername(username);   // check username duplicate
			if(usernameResult) {
				// duplicate
				usernameError="username alredy taken by another user. ";
			}else {
				//update username
				done=userSer.isUpdateUsername(username, userID);
			}
		}
		
		if(!email.isEmpty()) {
			emailResult=userSer.searchEmail(email);  // check email duplicate
			if(!emailResult) {
				//update email
				done=userSer.isUpdateEmail(email, userID);
			}else {
				emailError="email already taken by another user. ";
			}
		}
		
		if(!name.isEmpty()) {
			done=userSer.isUpdateCustomerName(name, userID); // update customer name
		}
		
		if(!bio.isEmpty()) {
			int searchbio = userSer.searchBio(userID); // search bio
			if(searchbio!=0) {
				done=userSer.isUpdateBio(bio, searchbio);   // update bio
			}else {
				bioResult= userSer.isaddBio(bio, userID); // add bio
				allMsg="bio added successfully. ";
			}
		}
		
		if(done>0) {
			allMsg="updated successfully";
		}
		
		UserInfoModel user = userSer.getUserInfo(userID); // fetch user information
		
		String str="";
		
		str=str+"<h3>Edit Profile</h3>";
		str=str+"<form name='frm' action='updateprofilephoto' method='POST' enctype='multipart/form-data' onsubmit='return profilefun()'>";	
		str=str+"<div class='photo'>";
		str=str+"<div class='image' id='imageGrid'>";
		str=str+"<img alt='' id='profilepic' src='resources/Profile_Images/"+user.getProfileimage()+"'>";
		str=str+"<a onclick='a()'><input type='file' class='chooseprofile' name='chooseprofile'  id='chooseprofile' style='display:none;' onchange='profileImgChange(this)' >+</a>";  
		str=str+"</div>";
		str=str+"<div class='userdetail'>";
		str=str+"<h4>"+user.getUsername()+"</h4>";
		str=str+"<h4 id='name'>"+user.getName()+"</h4>";  
		str=str+"</div>";
		str=str+"<button type='submit' name='changebtn' id='changebtn' >Change Profile</button>";
		str=str+"</div>";
		str=str+"</form>";
		str=str+"<input type='text' name='cname' id='cname' placeholder='name'><br>";
		str=str+"<div id='msgname' class='msg'></div>";
		str=str+"<input type='text' name='username' id='username' placeholder='Username'><br>";
		str=str+"<div id='msgusername' class='msg'>"+usernameError+"</div>";
		str=str+"<input type='text' name='email' id='email' placeholder='Email'><br>";
		str=str+"<div id='msgemail' class='msg'>"+emailError+"</div>";
		str=str+"<input type='text' name='bio' id='bio' placeholder='Bio'><br>";
		str=str+"<div id='msg'>"+allMsg+"</div>";
		str=str+"<button type='submit' name='editbtn' id='editbtn' onclick='return checkField()' >Save</button>";
		
		return str;
	}
}
