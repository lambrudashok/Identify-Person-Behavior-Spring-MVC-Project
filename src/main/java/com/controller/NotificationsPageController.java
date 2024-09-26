package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	// delete notification
	@RequestMapping("/deletenotification")
	public String deleteNty(HttpServletRequest request,Model model) {
		int nid = Integer.parseInt(request.getParameter("nid")); 
		int v=userSer.deleteUserNotification(nid); // delete user notification
		return getAllNotificationsPage(request, model);
	}
	
	
}
