package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.UserInfoModel;
import com.service.SearchService;

@Controller
public class SearchAnotherUserProfileController {

	@Autowired
	SearchService searchSer;
	
	
	@RequestMapping("/searchprofilepage")
	public String searchAnotherProfile(HttpServletRequest request , Model model) {
		List <UserInfoModel> list = searchSer.fetchAllUserDetails();
		model.addAttribute("searchdata", list);
		return "searchprofilepage";
	}
	
	
	
	
	
}
