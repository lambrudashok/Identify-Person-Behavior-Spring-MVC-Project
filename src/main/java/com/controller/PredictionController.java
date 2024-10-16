package com.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.PostLayoutModel;
import com.model.PredictionBehaviorModel;
import com.model.UserInfoModel;
import com.service.PredictionService;
import com.service.SearchService;


@Controller
public class PredictionController {

	@Autowired
	SearchService searchSer;
   	
   	@Autowired
   	PredictionService predictSer;
	
	
   	@RequestMapping("/predictionadminpage")
   	public String getPredictionpage(Model model) {
   		List <UserInfoModel> list = searchSer.fetchAllUserDetails();
   		model.addAttribute("list", list);
   		return "predictionadminpage";
   	}
   	
   	
   	// search user for prediction
   	@RequestMapping(value="/predictionadmincontroller", method=RequestMethod.POST)
   	@ResponseBody
   	public String searchPredictionUser(HttpServletRequest request) {
   		String name=request.getParameter("n");
		List <UserInfoModel> list = searchSer.fetchAllUserDetails(name);
		String str="";
		str=str+"<div class='info'>";
		if(list!=null){
			for(UserInfoModel userInfo:list){
				str=str+"<a class='userappinfo' id='userappinfo' href='predictionpostpage?id="+userInfo.getRegisterid()+"' >"; 
				str=str+"<div class='photo'>";
				str=str+"<img alt='' src='"+request.getContextPath()+"/resources/Profile_Images/"+userInfo.getProfileimage()+"'>";
				str=str+"</div>";  //photo
				str=str+"<div class='userdetails'>";
				str=str+"<div class='namediv'>";
				str=str+"<div id='name'>"+userInfo.getName()+"</div>";
				str=str+"<div id='username'>"+userInfo.getUsername()+"</div>";
				str=str+"</div>";
				str=str+"</div>"; // userdetails 
				str=str+"</a>";  // userappinfo
			}
		}else{
			str=str+"<h4>User Not Found</h4>";
		}
		str=str+"</div>";
		return str;
   	}
   	
   	//show particular user posts
   	@RequestMapping("/predictionpostpage")
   	public String getPredictionPostPage(HttpServletRequest request, Model model) {
   		int registerId= Integer.parseInt(request.getParameter("id"));
   		List<PostLayoutModel> list =predictSer.viewAllUserPosts(registerId);
   		model.addAttribute("list", list);
   		model.addAttribute("registerId", registerId);
   		return "predictionpostpage";
   	}
   	
   	
   	//single post prediction
   	@RequestMapping("/predictionsinglepost")
   	public String getSinglePostPrediction(HttpServletRequest request, Model model) {
   		int postid=Integer.parseInt(request.getParameter("postid"));
		// prediction person using post
		String post=predictSer.ViewPost(postid);
		// fetch person behavior single post prediction 
		PredictionBehaviorModel predict=predictSer.predictPersonBehavior(post);
		model.addAttribute("openessToExperience", predict.getOpenessToExperience()); 
		model.addAttribute("conscientiousness",predict.getConscientiousness());
		model.addAttribute("extroversion", predict.getExtroversion());
		model.addAttribute("agreeableness", predict.getAgreeableness());
		model.addAttribute("neuroticism", predict.getNeuroticism());
		return "predictionchartpage"; //predictionchartpage.jsp
   	}
   	
   	
   	//over all Prediction
   	@RequestMapping(value="/predictionoverall", method=RequestMethod.POST)
   	public String getOverAllPredictionUser(HttpServletRequest request , Model model) {
   		int registerId=Integer.parseInt(request.getParameter("overallpredictionbtn"));
		// OverAll prediction logic
		// posts , comments, like of post
		String[] unlabelledInformation=predictSer.getAllPostsCommentsLikes(registerId);
		PredictionBehaviorModel predict=predictSer.predictOverAllPersonBehavior(unlabelledInformation);
		model.addAttribute("openessToExperience", predict.getOpenessToExperience()); 
		model.addAttribute("conscientiousness",predict.getConscientiousness());
		model.addAttribute("extroversion", predict.getExtroversion());
		model.addAttribute("agreeableness", predict.getAgreeableness());
		model.addAttribute("neuroticism", predict.getNeuroticism());
   		return "predictionchartpage";
   	}
   	
   	
}
