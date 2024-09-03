package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.NotificationModel;
import com.model.ReportProblemModel;
import com.service.UserRegistrationService;

@Controller
public class ReportProblemController {

	@Autowired
	UserRegistrationService userSer;
	
	@RequestMapping("/reportproblempage")
	public String getReportProblemPage() {
		return "reportproblempage";
	}
	
	
	// when user send report
	@RequestMapping("/sendreportproblemuser")
	public String getUserReport(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false); // fetch user id from session
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		String title = request.getParameter("title"); // fetch title
		String description = request.getParameter("description"); // get description user
		ReportProblemModel report = new ReportProblemModel();
		report.setTitle(title);
		report.setDescription(description);
		report.setStatus("pending");
		report.setRegisterid(userID);
		boolean success = userSer.isAddReportProblemUser(report);
		
		// when report store then send notification user
		NotificationModel notify = new NotificationModel();
		notify.setNotification("'"+title+"', Your report has been sent for review. Thank you for reporting the issue.");
		notify.setRegisterid(userID);
		userSer.isAddNotification(1, notify);
		
		model.addAttribute("msg", "Your report has been submitted successfully. Thank you for your feedback");
		return "reportproblempage";
	}
	
	
	
	
	
	
}
