package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.UserInfoModel;
import com.model.ProfileInformationModel;
import com.model.PostLayoutModel;
import com.service.CreatePostService;
import com.service.FollowingService;
import com.service.LikeCommentService;
import com.service.SearchService;
import com.service.UserRegistrationService;

@Controller
public class SearchAnotherUserProfileController {

	@Autowired
	SearchService searchSer;
	
	@Autowired
	UserRegistrationService userSer;
	
	@Autowired
	FollowingService follwSer;
	
	@Autowired
	CreatePostService postSer;
	
	@Autowired
	LikeCommentService lkSer;
	
	@RequestMapping("/searchprofilepage")
	public String searchAnotherProfile(HttpServletRequest request , Model model) {
		List <UserInfoModel> list = searchSer.fetchAllUserDetails();
		model.addAttribute("searchdata", list);
		return "searchprofilepage";
	}
	
	
	// response Json format
	@RequestMapping("/searchprofile")
	@ResponseBody
	public List<UserInfoModel> searchAnotherUserJson(@RequestParam("n") String name) {
		List <UserInfoModel> list = searchSer.fetchAllUserDetails(name);
		return list;
	}
	
	// loading another user profile 
	@RequestMapping("/anotheruserprofilepage")
	public String getAnotherUserProfilePage(HttpServletRequest request , Model model) {
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString()); // user id
		int registerid=Integer.parseInt(request.getParameter("id")); // search user id
		List<ProfileInformationModel> profileList=userSer.profileInformation(registerid);
		model.addAttribute("profileList", profileList);
		int status=follwSer.checkFollowingStatus(registerid,userID); // check following status
		model.addAttribute("status", status);
		model.addAttribute("registerid", registerid);
        List<PostLayoutModel> listPosts = postSer.ViewAllPosts(registerid,userID); // fetch all posts
        model.addAttribute("listPosts", listPosts);
		return "anotheruserprofilepage";
	}
	
	
	// another user profile follow
	@RequestMapping(value="/follow" , method=RequestMethod.POST)
	@ResponseBody
	public String getFollowUser(HttpServletRequest request) {
		int followid=Integer.parseInt(request.getParameter("followid"));
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		boolean result=follwSer.isAddFollowingUser(userID,followid); // add following user
		int status=follwSer.checkFollowingStatus(followid,userID); // check following or not
		String str = "";
		if(status==0){
			// follow btn
			str=str+"<button name='follow' id='follow' value='"+followid+"' onclick='followUser(this.value)' >Follow</button>";            
		}else{
			 // following btn 
			str=str+"<button name='following' id='following' value='"+followid+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>";            
		}
		return str;
	}
	
	// another user profile unfollow
	@RequestMapping(value="/unfollow", method=RequestMethod.POST)
	@ResponseBody
	public String getFollowingUser(HttpServletRequest request) {
		int unfollowid=Integer.parseInt(request.getParameter("unfollowid"));
		HttpSession session = request.getSession(false);
		int registerId = Integer.parseInt(session.getAttribute("userID").toString());
		int result=follwSer.removeFollowingUser(unfollowid, registerId); // unfollow user
		int status=follwSer.checkFollowingStatus(unfollowid,registerId); // check following or not
		String str="";
		
		if(status==0){
			// follow btn
			str=str+"<button name='follow' id='follow' value='"+unfollowid+"' onclick='followUser(this.value)' >Follow</button>";            
		}else{
			 // following btn 
			str=str+"<button name='following' id='following' value='"+unfollowid+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>";            
		}
		return str;
	}
	
	
	
	
}
