package com.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.model.LoginModel;

@Repository
public class AdminRepository {

	@Autowired
	JdbcTemplate template;
	
	/*check admin login available or not in database*/
	public int checkAdminLogin(final LoginModel login) {
		
	 List<Integer> registerId = template.query("select adminid from adminmaster where username=? and password=?",
		        new PreparedStatementSetter() {
		            @Override
		            public void setValues(PreparedStatement ps) throws SQLException {
		                ps.setString(1, login.getUsername());
		                ps.setString(2, login.getPassword());
		            }
		        },new RowMapper<Integer>() {
		            @Override
		            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		                return rs.getInt("adminid");
		            }
		        }
		    );
		    return registerId.size()>0 ?  registerId.get(0) : -1;
	}
	
//	
//	// fetch all user details
//	public List<RegistrationModel> fetchAllUserDetails(){
//		List<RegistrationModel> list = new ArrayList<RegistrationModel>();
//		try {
//			ps=con.prepareStatement("select * from registrationmaster");
//			rs=ps.executeQuery();
//			while(rs.next()) {
//				RegistrationModel model = new RegistrationModel();
//				model.setRegisterid(rs.getInt("registerid"));
//				model.setUsername(rs.getString("username"));
//				model.setEmail(rs.getString("email"));
//				model.setPassword(rs.getString("password"));
//				model.setCustomername(rs.getString("customername"));
//				model.setProfileimgname(rs.getString("profileimg"));
//				list.add(model);
//			}
//			return (list.size()>0) ? list :null;
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return null;
//		}
//	}
//	
//	//delete user
//	public int deleteUser(int registerid) {
//		try {
//			ps=con.prepareStatement("delete from registrationmaster where registerid=?");
//			ps.setInt(1, registerid);
//			int v=ps.executeUpdate();
//			return (v>0)?1:0;
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return 0;
//		}
//	}
//	
//	
//	// delete user from delete request
//	public int deleteUserRequestAccount(int registerid) {
//		try {
//			ps=con.prepareStatement("delete from registrationmaster where registerid=?");
//			ps.setInt(1, registerid);
//			int v=ps.executeUpdate();
//			
//			ps=con.prepareStatement("delete from deleterequest where registerid=?");
//			ps.setInt(1, registerid);
//			v=ps.executeUpdate();
//			
//			return (v>0)?1:0;
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return 0;
//		}
//	}
//	
//	
//	
//	
//	/*view All posts application users*/
//	public List<PostLayoutModel> ViewAllUserPosts(){
//		List<PostLayoutModel> list= new LinkedList<PostLayoutModel>(); // store  user post id ,post, username ,image
//		
//		ArrayList<Integer> alPost =new ArrayList<Integer>();   // store post id users
//		
//		try {
//			// we fetch post id and store in ArrayList
//			
//			ps=con.prepareStatement("select pm.postid from registrationmaster rm "
//					+ "inner join postregistrationjoin prj on prj.registerid=rm.registerid "
//					+ "inner join postmaster pm on pm.postid=prj.postid "
//					+ "order by postdate desc");
//			
//			rs=ps.executeQuery();
//			while(rs.next()) {
//				alPost.add(rs.getInt(1));
//			}
//			
//			// we fetch username , post , image
//			if(alPost.size()>0) {               // check  posts not found
//				for(Integer lc:alPost) {
//					
//					PostLayoutModel pmodel = new PostLayoutModel();
//										
//					ps=con.prepareStatement("select rm.username from registrationmaster rm "
//							+ "inner join postregistrationjoin prj on prj.registerid=rm.registerid "
//							+ "inner join postmaster pm on pm.postid=prj.postid "
//							+ "where pm.postid=?");
//					ps.setInt(1, lc);
//					rs=ps.executeQuery();
//					if(rs.next()) {
//						pmodel.setUsername(rs.getString(1)); // set username
//					}				
//					
//					pmodel.setPostid(lc);      // set post id
//					
//					ps=con.prepareStatement("select post,imgname from postmaster where postid=?");
//					ps.setInt(1, lc);
//					rs=ps.executeQuery();
//					if(rs.next()) {
//						pmodel.setPost(rs.getString(1));   // set post
//						pmodel.setImgname(rs.getString(2)); // set imgname
//					}
//				
//				  list.add(pmodel);
//				}
//			}
//			
//			return (list.size()>0) ? list:null;
//		}catch(Exception e) {
//			System.out.println("post master repository error :"+e);
//			return null;
//		}
//	}
//	
//	
//	// fetch delete account  requests  users
//	public List<RegistrationModel> fetchDeleteUserAccountReuests(){
//		
//		List <RegistrationModel> model = new ArrayList<RegistrationModel>(); // store all details requested user
//		try {
//			ArrayList<Integer> al = new ArrayList<Integer>(); // store requests id
//			ps=con.prepareStatement("select registerid from deleterequest");
//			rs=ps.executeQuery();
//			while(rs.next()) {
//				al.add(rs.getInt(1));
//			}
//			
//			if(!al.isEmpty()) { // check requests not found
//				for(Integer info:al) {
//					RegistrationModel rm = new RegistrationModel();  
//					ps=con.prepareStatement("select registerid,customername,username,profileimg from registrationmaster "
//							+ "where registerid=?");
//					ps.setInt(1, info);
//					rs=ps.executeQuery();
//					if(rs.next()) {
//						rm.setRegisterid(rs.getInt("registerid"));           // set registerid
//						rm.setCustomername(rs.getString("customername"));      // set name
//						rm.setUsername(rs.getString("username"));         // set username
//						rm.setProfileimgname(rs.getString("profileimg"));
//					}
//					//store delete request date
//					ps=con.prepareStatement("select date from deleterequest where registerid=?");
//					ps.setInt(1, info);
//					rs=ps.executeQuery();
//					//user date requested
//					Date userDate=null;
//					if(rs.next()) {
//						rm.setDate(rs.getDate("date"));   // set date
//						userDate=rs.getDate(1);
//					}
//					// logic for remaining days
//					//fetch current date
//					Date currentDate=this.currentDateFunction();
//					
//					// calculate remaining days
//					int uyear=userDate.getYear();
//					int umonth=userDate.getMonth();
//					int uday=userDate.getDate();
//					
//					int cyear=currentDate.getYear();
//					int cmonth=currentDate.getMonth();
//					int cday=currentDate.getDate();
//					
//					int year=(cyear+1900)-(uyear+1900);
//					int month=(cmonth+1)-(umonth+1);
//					int day=(cday-uday);
//					
//					int date=Math.abs(year*(365)-month*(30)-day*(1));
//					int remain=31-date;
//					if(remain<0) {
//						remain=0;
//					}
//					rm.setRemain(remain);                       // set remaining days
//			
//					
//					
//					model.add(rm);  // set user details
//				}
//			}
//			
//			return (model.size()>0) ? model :null;
//			
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return null;
//		}
//	}
//	
//	// fetch current date
//	public Date currentDateFunction() {
//		
//		try {
//			ps=con.prepareStatement("select curdate()");
//			rs=ps.executeQuery();
//			if(rs.next()) {
//				return rs.getDate(1);
//			}else {
//				return null;
//			}
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return null;
//		}
//	}
//	
//	// fetch user login details date time 
//	public List<LoginModel> viewUserLoginDetails(){
//		List<LoginModel> al = new ArrayList<LoginModel>();
//		try {
//			ps=con.prepareStatement("select * from loginmaster where registerid<>'null' order by date desc");
//			rs=ps.executeQuery();
//			while(rs.next()){
//			LoginModel model =new LoginModel();
//			model.setLoginid(rs.getInt("registerid"));
//			model.setUsername(rs.getString("username"));
//			model.setPassword(rs.getString("password"));
//			model.setDate(rs.getDate("date"));
//			model.setTime(rs.getTime("time"));
//			al.add(model);
//			}
//			return (al.size()>0?al:null);
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return null;
//		}
//	}
	
}
