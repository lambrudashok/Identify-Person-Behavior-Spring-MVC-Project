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

import com.model.NotificationModel;
import com.service.UserRegistrationService;

@Controller
public class NotificationsPageController {
	
	@Autowired
	UserRegistrationService userSer;
	
	// notifications page controller
	@RequestMapping("/notificationspage")
	public String getAllNotificationsPage(HttpServletRequest request ,Model model) {
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		List<NotificationModel> list = userSer.getAllUserNotification(userID);
		int Ncount = userSer.getNotificationUserCount(userID);
		model.addAttribute("list", list);
		model.addAttribute("Ncount", Ncount);
		return "notificationspage";
	}
	
	@RequestMapping(path="/viewallautomatic",method = RequestMethod.POST)
	@ResponseBody
	  public void viewAllNotificationAutomatic(HttpServletRequest request ,Model model) {
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		int vie = userSer.notificationUserViewAutomatic(userID); // automatic view notification
		
	}
	
	
	// delete notification
	@RequestMapping("/deletenotification")
	public String deleteNty(HttpServletRequest request,Model model) {
		int nid = Integer.parseInt(request.getParameter("nid")); 
		int v=userSer.deleteUserNotification(nid); // delete user notification
		return getAllNotificationsPage(request, model);
	}
	
	
	// clear all notifications user
	@RequestMapping("/clearallnotification")
	public String clearAllNotifications(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		int v=userSer.clearAllNotificationsUser(userID); // clear all notifications user
		return getAllNotificationsPage(request, model);
	}
	
	// menus notification desktop count
	@RequestMapping(path="/countnotification",method = RequestMethod.POST)
	@ResponseBody
	public String getCountNotification (HttpServletRequest request ,Model model) {
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		int Ncount = userSer.getNotificationUserCount(userID);
		model.addAttribute("Ncount", Ncount);
		model.addAttribute("countNty", Ncount);
		if(Ncount>0) {
		String str="";
		str=str+"<i class='fa-solid fa-circle' id='dot'></i>";
		str=str+"<h6>"+Ncount+"</h6>";
		return str;
		}else {
			String str="";
			return str;
		}
	}
	
	
	// menus notification mobile dot
	@RequestMapping(path="/countnotificationNty",method = RequestMethod.POST)
	@ResponseBody
	public String getCountNotificationMobile (HttpServletRequest request ,Model model) {
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		int countNty = userSer.getNotificationUserCount(userID);
		model.addAttribute("countNty", countNty);
		if(countNty>0) {
		String str="";
		str=str+"<i class='fa-solid fa-circle' id='dot'></i>";
		return str;
		}else {
			String str="";
			return str;
		}
	}
	


	
	
}
