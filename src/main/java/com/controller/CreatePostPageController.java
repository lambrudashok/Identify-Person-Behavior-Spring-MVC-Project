package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model.NotificationModel;
import com.model.PostModel;
import com.service.CreatePostService;
import com.service.FollowingService;
import com.service.UserRegistrationService;


@Controller
public class CreatePostPageController{
	
	@Autowired
	CreatePostService postSer;
	
	@Autowired
	FollowingService followSer;
	
	@Autowired
	UserRegistrationService userSer;
	
	@RequestMapping("/postcreatepage")
	public String getPostCreatePage() {
		return "postcreatepage";
	}

	
	@RequestMapping(path="/postsubmit", method=RequestMethod.POST)
	public String submitPost(HttpServletRequest request, @RequestParam("postimagefile") MultipartFile file)throws IOException {
		try {
		  String post=request.getParameter("postname"); // fetch post
		  String fileName= file.getOriginalFilename(); // fetch image file name
		
		  // access user id from session
		   HttpSession session = request.getSession(false);
		  int userID = Integer.parseInt(session.getAttribute("userID").toString());
		  PostModel pmodel = new PostModel();
		  pmodel.setPost(post);
		  pmodel.setRegisterid(userID);
		  if(fileName.isEmpty()) {
			  // set default image name
			 pmodel.setImgname("person.png");
			 postSer.isaddUserNewPost(pmodel); // add post and imageName in database
			 session.setAttribute("postMsg", "Post Upload Successful");
			 
			 // notification logic
			 List<Integer> al = followSer.fetchAllFollowingUserIds(userID);
			 if(al!=null) {
				 NotificationModel notify = new NotificationModel();
				 notify.setNotification("added new post.");
				 notify.setRegisterid(userID);
				 userSer.isAddNotification(al, notify);
			 }
			 
			 return "postcreatepage"; // postcreatepage.jsp
			 
		  }else {
			  // set user post image
			pmodel.setImgname(fileName); 
			 boolean postresult = postSer.isaddUserNewPost(pmodel);
			 if(postresult) {
				 // upload photo in server (server as a folder)
				 
				 String path = request.getServletContext().getRealPath("")+ "resources" + File.separator + "Post_Images";
		            File uploadDir = new File(path);
		            if (!uploadDir.exists()) {
		                uploadDir.mkdirs();
		            }
		            File destinationFile = new File(path + File.separator + fileName);
		            file.transferTo(destinationFile);
				 session.setAttribute("postMsg", "Post Upload Successful");
				 
				 // notification logic
				 List<Integer> al = followSer.fetchAllFollowingUserIds(userID);
				 if(al!=null) {
					 NotificationModel notify = new NotificationModel();
					 notify.setNotification("added new post.");
					 notify.setRegisterid(userID);
					 userSer.isAddNotification(al, notify);
				 }
				 
				 return "postcreatepage"; // postcreatepage.jsp 
			 }
			 return "postcreatepage";
			 
		  }
		}catch(Exception e) {
			System.out.println("create post page controller error :"+e);
			 HttpSession session = request.getSession(false);
            session.setAttribute("postMsg", "Post Upload Failed");
			return "postcreatepage";
		}
		
		
	}
	
	
	
}
