package com.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.PostModel;
import com.service.CreatePostService;


@Controller
public class CreatePostPageController{
	
	@Autowired
	CreatePostService postSer;
	
	@RequestMapping("/postcreatepage")
	public String getPostCreatePage() {
		return "postcreatepage";
	}
	
	
	@RequestMapping(path="/postsubmit", method=RequestMethod.POST)
	public String submitPost(HttpServletRequest request, @RequestParam("postimagefile") CommonsMultipartFile file) {
		
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
			 return "postcreatepage"; // postcreatepage.jsp
			 
		  }else {
			  // set user post image
			pmodel.setImgname(fileName); 
			
			 boolean postresult = postSer.isaddUserNewPost(pmodel);
			 if(postresult) {
				 // upload photo in server (server as a folder)
				 String path = request.getServletContext().getRealPath("") +"resources\\Post_Images";
				 
				 byte[] bytes =file.getBytes();  // convert image to bytes
				 FileOutputStream f = new FileOutputStream(path + File.separator +fileName);
				 f.write(bytes);
				 f.close();
				
				 session.setAttribute("postMsg", "Post Upload Successful");
				
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
