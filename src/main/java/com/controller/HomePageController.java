package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.PostLayoutModel;
import com.model.PostModel;
import com.model.UserInfoModel;
import com.service.FollowingService;
import com.service.LikeCommentService;
import com.service.SearchService;
import com.service.UserRegistrationService;


@Controller
public class HomePageController {

	@Autowired
	SearchService searchSer;
	
	@Autowired
	FollowingService followSer;
	
	@Autowired
	LikeCommentService lkSer;
	
	@Autowired
	UserRegistrationService userSer;
	
	// this function get the data in form of JSON format for home page search
	@RequestMapping(value="/searchuser" , method = RequestMethod.POST)
	@ResponseBody
	public List<UserInfoModel> getSearchUserHomePageJsonData(HttpServletRequest request) {
		String name=request.getParameter("n");
		HttpSession session = request.getSession(false); 
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		List<UserInfoModel> list= searchSer.fetchAllUserDetails(name,userID); // store jSON format user data
		return list; 
		
	}
	
	
	// JSON format for home page search follow click
	@RequestMapping(value="/searchfollowcontroller" ,method=RequestMethod.POST)
	@ResponseBody
	public List<UserInfoModel> getHomePageSearchFollow(HttpServletRequest request){
		int followid=Integer.parseInt(request.getParameter("followid"));
		HttpSession session = request.getSession(false);
		int registerId = Integer.parseInt(session.getAttribute("userID").toString());
		boolean result=followSer.isAddFollowingUser(registerId,followid);	// add user follow
		List<UserInfoModel> list = searchSer.fetchAllUserDetails(registerId); 
		return list;
	}
	
	//JSON format for home page search unfollow click
	@RequestMapping("/searchunfollowcontroller")
	@ResponseBody
	public List<UserInfoModel> gtHomePageSearchUnFollow(HttpServletRequest request){
		int unfollowid=Integer.parseInt(request.getParameter("unfollowid"));
		HttpSession session = request.getSession(false);
		int registerId = Integer.parseInt(session.getAttribute("userID").toString());
		int result=followSer.removeFollowingUser(unfollowid, registerId); // unfollow user
		List<UserInfoModel> list = searchSer.fetchAllUserDetails(registerId);
		return list;
	}
	
	
	//for you like home page response string format
	@RequestMapping(value="/likeforyoucontroller", method=RequestMethod.POST)
	@ResponseBody
	public String getForYouLikeHomePage(HttpServletRequest request) {
		int postid=Integer.parseInt(request.getParameter("postid"));		
		// access user id from session
		HttpSession session=request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		boolean result=lkSer.isAddLike(postid, userID);
		int likeCount=lkSer.fetchLikeCount(postid); // get count of like
        int v=lkSer.checkLike(postid,userID); // check like 
        String str="";
		 if(v>0){
			 str=str+"<a id='liked' onclick='unlikeForYou("+postid +")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>";
         }else{
			str=str+"<a id='like'  onclick='likeForYou("+postid +")'> <i class='fa-solid fa-heart'></i>&nbsp"+ likeCount+"</a>";
		 }
		return str;
	}
	
	
	
	//for you unlike home page response string format
	@RequestMapping(value="/unlikeforyoucontroller", method=RequestMethod.POST)
	@ResponseBody
	public String getForYouUnLikeHomePage(HttpServletRequest request) {
		int postid=Integer.parseInt(request.getParameter("postid"));		
		// access user id from session
		HttpSession session=request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		int result=lkSer.unLikePost(postid, userID); // unlike post
		int likeCount=lkSer.fetchLikeCount(postid); // get count of like
        int v=lkSer.checkLike(postid,userID); // check like 
		String str="";
        if(v>0){
			str=str+"<a id='liked' onclick='unlikeForYou("+postid+")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>";
         }else{
			str=str+"<a id='like'  onclick='likeForYou("+postid+")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>";
		 }
		return str;
	}
	
	
	
	// home page comment response string format logic
	@RequestMapping(value="/commentsubmithomepage", method=RequestMethod.POST)
	@ResponseBody
	public String getCommentHomePage(HttpServletRequest request) {
		// fetch postid and comment 
		int postid=Integer.parseInt(request.getParameter("postid"));
		String comment=(String)request.getParameter("comment");
		// access user id from session
		HttpSession session=request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		PostModel pmodel = new PostModel();
		pmodel.setComment(comment);
		pmodel.setPostid(postid);
		pmodel.setRegisterid(userID);
		int registerid = userSer.getPostRegisterid(postid); // fetch registerid post user
		boolean commentResult=lkSer.isAddComment(pmodel); // add comment record
		int commentCount = lkSer.getCommentCount(postid); // fetch comment count
		String str="";
		str=str+"<a id='commentshow' href='viewhomepagecomment?postid="+postid+"&userID="+registerid+"'> <i class='fa-solid fa-comment'></i> "+commentCount+"</a>";
        str=str+"<form name='frm' id='frm' method='POST' onsubmit='return commentfun("+postid+",comment.value)'>"; 
        str=str+"<input type='text' name='comment' id='comment' placeholder='comment here...' required>"; 
        str=str+"<button type='submit' id='commentbtn'  name='commentbtn' >post</button>";
        str=str+"</form>";
		return str;
	}
	
	
	
	// comment view controller logic
	@RequestMapping("/viewhomepagecomment")
	public String getCommentProfileUser(HttpServletRequest request, Model model) {
		int postid = Integer.parseInt(request.getParameter("postid"));
		int userID = Integer.parseInt(request.getParameter("userID"));
		UserInfoModel userInfo = userSer.getUserInfo(userID); // add user info
		model.addAttribute("userInfo", userInfo);
		List<PostLayoutModel> commentlist = lkSer.getCommentDetails(postid); // add comments user details 
		model.addAttribute("commentlist", commentlist);
		return "commentpage";
	}
	
	
}
