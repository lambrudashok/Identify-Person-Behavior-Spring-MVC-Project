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
import com.model.ProfileInformationModel;
import com.service.CreatePostService;
import com.service.LikeCommentService;
import com.service.UserRegistrationService;


@Controller
public class ProfilePageController {
	
	@Autowired
	UserRegistrationService registerSer;
	
	@Autowired
	CreatePostService postSer;
	
	@Autowired
	LikeCommentService lkSer;

	@RequestMapping("/profilepage")
	public String getProfilePage(HttpServletRequest request, Model model) {
		
		HttpSession	session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		List<ProfileInformationModel> profileList=registerSer.profileInformation(userID);	// fetch user profile information
		model.addAttribute("prof", profileList);  // set profile information in model
		List<PostLayoutModel> listPosts = postSer.ViewAllPosts(userID);  // fetch user all posts
		model.addAttribute("posts", listPosts); // set all posts in model
			
		return "profilepage";
	}

	//  delete posts profile
	@RequestMapping("/deletepost")
	public String deletePostProfile(HttpServletRequest request, Model model) {
		
		int postid=Integer.parseInt(request.getParameter("postid"));
		postSer.deletePost(postid); // delete post
		
		return getProfilePage(request, model);
	}
	
	
	// profile posts comment logic
	@RequestMapping(value="/commentsubmit", method=RequestMethod.POST)
	@ResponseBody
	public String storeComment(HttpServletRequest request) {
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
		
		boolean commentResult=lkSer.isAddComment(pmodel);
		int commentCount = lkSer.getCommentCount(postid);
		String str="";
		str=str+"<a id='commentshow' href=''> <i class='fa-solid fa-comment'></i> "+commentCount+"</a>";
        str=str+"<form name='frm' method='POST' onsubmit='return commentfun("+postid+",comment.value)'>"; 
        str=str+"<input type='text' name='comment' id='comment' placeholder='comment here...' required>"; 
        str=str+"<button type='submit' id='commentbtn'  name='commentbtn' >post</button>";
        str=str+"</form>";
		return str;
	}

	
	//profile posts like logic
	@RequestMapping("/likecontroller")
	@ResponseBody
	public String likeUserProfilePosts(HttpServletRequest request) {
		
		int postid=Integer.parseInt(request.getParameter("postid"));		
		// access user id from session
		HttpSession session=request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		
		boolean result=lkSer.isAddLike(postid, userID);
		int likeCount=lkSer.fetchLikeCount(postid); // get count of like
        int v=lkSer.checkLike(postid,userID);  // check like 
        String str="";
		if(v>0){
			str=str+"<a id='liked' onclick='unlikefun("+postid+")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>";  
         
        }else{
        	str=str+"<a id='like'  onclick='likefun("+postid+")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>";
         }
     
		return str;
	}
	
	
	
	//unlike logic posts profile
	@RequestMapping("/unlikecontroller")
	@ResponseBody
	public String unLikeUserPostsProfile(HttpServletRequest request) {
		
		int postid=Integer.parseInt(request.getParameter("postid"));		
		// access user id from session
		HttpSession session=request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		
		int result=lkSer.unLikePost(postid, userID); // unlike post
			
		// get count of like
		int likeCount=lkSer.fetchLikeCount(postid);
		
		// check like or not
        int v=lkSer.checkLike(postid,userID);
        
        String str=""; 
		if(v>0){
			str=str+"<a id='liked' onclick='unlikefun("+postid+")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>";  
	    }else{
	    	str=str+"<a id='like'  onclick='likefun("+postid+")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>";
		}
     
		return str;
	}
	
	
}