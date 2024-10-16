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
import com.model.PostLayoutModel;
import com.model.RegistrationModel;
import com.model.ReportProblemModel;

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
	
	
	// fetch all user details
	public List<RegistrationModel> fetchAllUserDetails(){
		try {
			List<RegistrationModel> list = template.query("select * from registrationmaster", new RowMapper<RegistrationModel>() {
				@Override
				public RegistrationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					RegistrationModel model = new RegistrationModel();
					model.setRegisterid(rs.getInt("registerid"));
					model.setUsername(rs.getString("username"));
					model.setEmail(rs.getString("email"));
					model.setPassword(rs.getString("password"));
					model.setCustomername(rs.getString("customername"));
					model.setProfileimgname(rs.getString("profileimg"));
					model.setStatus(rs.getString("status"));
					return model;
				}
			});
			return (list.size()>0) ? list :null;
		}catch(Exception e) {
			System.out.println("admin repo error :"+e);
			return null;
		}
	}
	
	//delete user
	public int deleteUser(final int registerid) {
		try {
			int v = template.update("delete from registrationmaster where registerid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerid);
				}
			});
			return (v>0)?1:0;
		}catch(Exception e) {
			System.out.println("adimin repo error :"+e);
			return 0;
		}
	}
	
	
	//check freeze user account
	public String checkFreezeUser(int registerid) {
		try {
			String status = template.queryForObject("select status from registrationmaster where registerid=?", new Object[] {registerid}, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("status");
				}
			});
			return status;
		}catch(Exception e) {
			System.out.println("error freeze :"+e);
			return null;
		}
	}
	
	
	//freeze user account
	public int freezeUserAccount(final int registerid) {
		try {
			int v = template.update("update registrationmaster set status='freeze' where registerid=?", new PreparedStatementSetter() {			
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerid);
				}
			});
			return (v>0)?1:0;
		}catch(Exception e) {
			System.out.println("error freeze :"+e);
			return 0;
		}
	}
	
	
	//unfreeze user account
	public int unFreezeUserAccount(final int registerid) {
		try {
			int v = template.update("update registrationmaster set status='no' where registerid=?", new PreparedStatementSetter() {			
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerid);
				}
			});
			return (v>0)?1:0;
		}catch(Exception e) {
			System.out.println("error freeze :"+e);
			return 0;
		}
	}
		
		
	
	// delete user from delete request
	public int deleteUserRequestAccount(final int registerid) {
		try {
			int v = template.update("delete from registrationmaster where registerid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerid);
				}
			});
			v = template.update("delete from deleterequest where registerid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerid);
				}
			});
			return (v>0)?1:0;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	
	
	
	/*view All posts application users*/
	public List<PostLayoutModel> ViewAllUserPosts(){
		List<PostLayoutModel> list= new LinkedList<PostLayoutModel>(); // store  user post id ,post, username ,image
		try {
			// store post id users
			List<Integer> alPost = template.query("select pm.postid from registrationmaster rm "
					+ "inner join postregistrationjoin prj on prj.registerid=rm.registerid "
					+ "inner join postmaster pm on pm.postid=prj.postid "
					+ "order by postdate desc", new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getInt(1);
						}
					});
			
			// we fetch username , post , image
			if(alPost.size()>0) {               // check  posts not found
				for(Integer lc:alPost) {
					
					final PostLayoutModel pmodel = new PostLayoutModel();
					
					template.queryForObject("select rm.username from registrationmaster rm "
							+ "inner join postregistrationjoin prj on prj.registerid=rm.registerid "
							+ "inner join postmaster pm on pm.postid=prj.postid "
							+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {
								@Override
								public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
									pmodel.setUsername(rs.getString(1)); // set username
									return null;
								}
							});
				
					pmodel.setPostid(lc);      // set post id
					
					template.queryForObject("select post,imgname from postmaster where postid=?", new Object[] {lc}, new RowMapper<Void>() {
						@Override
						public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
							pmodel.setPost(rs.getString(1));   // set post
							pmodel.setImgname(rs.getString(2)); // set imgname
							return null;
						}
					});
				
				  list.add(pmodel);
				}
			}
			
			return (list.size()>0) ? list:null;
		}catch(Exception e) {
			System.out.println("admin repository error :"+e);
			return null;
		}
	}
	
	
	// fetch delete account  requests  users
	public List<RegistrationModel> fetchDeleteUserAccountReuests(){
		List <RegistrationModel> model = new ArrayList<RegistrationModel>(); // store all details requested user
		try {
			// store requests id
			List<Integer> al = template.query("select registerid from deleterequest", new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			
			if(!al.isEmpty()) { // check requests not found
				for(Integer info:al) {
					final RegistrationModel rm = new RegistrationModel();
					
					template.queryForObject("select registerid,customername,username,profileimg from registrationmaster "
							+ "where registerid=?", new Object[] {info}, new RowMapper<Void>() {
								@Override
								public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
									rm.setRegisterid(rs.getInt("registerid"));           // set registerid
									rm.setCustomername(rs.getString("customername"));      // set name
									rm.setUsername(rs.getString("username"));         // set username
									rm.setProfileimgname(rs.getString("profileimg")); // set profile photo
									return null;
								}
							});
				
					//store delete request date
					Date userDate =template.queryForObject("select date from deleterequest where registerid=?", new Object[] {info}, new RowMapper<Date>() {
						@Override
						public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
							rm.setDate(rs.getDate("date"));   // set date
							return rs.getDate(1);
						}
					});
					
					// logic for remaining days
					//fetch current date
					Date currentDate=this.currentDateFunction();
					
					// calculate remaining days
					int uyear=userDate.getYear();
					int umonth=userDate.getMonth();
					int uday=userDate.getDate();
					
					int cyear=currentDate.getYear();
					int cmonth=currentDate.getMonth();
					int cday=currentDate.getDate();
					
					int year=(cyear+1900)-(uyear+1900);
					int month=(cmonth+1)-(umonth+1);
					int day=(cday-uday);
					
					int date=Math.abs(year*(365)-month*(30)-day*(1));
					int remain=31-date;
					if(remain<0) {
						remain=0;
					}
					rm.setRemain(remain);                       // set remaining days
			
					model.add(rm);  // set user details
				}
			}
			
			return (model.size()>0) ? model :null;
			
		}catch(Exception e) {
			System.out.println("remaining days error :"+e);
			return null;
		}
	}
	
	
	
	// fetch current date
	public Date currentDateFunction() {
		try {
			Date d=template.queryForObject("select curdate()", new RowMapper<Date>() {
				@Override
				public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getDate(1);
				}
			});
			return d;
		}catch(Exception e) {
			System.out.println("admin repo error :"+e);
			return null;
		}
	}
	
	// fetch user login details date time 
	public List<LoginModel> viewUserLoginDetails(){
		try {
			List<LoginModel> al = template.query("select * from loginmaster where registerid<>'null' order by loginid desc", new RowMapper<LoginModel>() {
				@Override
				public LoginModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					LoginModel model =new LoginModel();
					model.setLoginid(rs.getInt("registerid"));
					model.setUsername(rs.getString("username"));
					model.setPassword(rs.getString("password"));
					model.setDate(rs.getDate("date"));
					model.setTime(rs.getTime("time"));
					return model;
				}
			});
			return (al.size()>0)? al : null;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return null;
		}
	}
	
	
	//fetch reports 
	public List<ReportProblemModel> getReportsUser(){
		try {
			List<ReportProblemModel> list = template.query("select reportid,title,description,date,status,registerid from reportproblemmaster", 
					new RowMapper<ReportProblemModel>() {
				@Override
				public ReportProblemModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReportProblemModel rp = new ReportProblemModel();
					rp.setReportid(rs.getInt(1));
					rp.setTitle(rs.getString(2));
					rp.setDescription(rs.getString(3));
					rp.setDate(rs.getDate(4));
					rp.setStatus(rs.getString(5));
					rp.setRegisterid(rs.getInt(6));
					return rp;
				}
			});
			if(list!=null) {
				for(ReportProblemModel rpm :list) {
					String username = template.queryForObject("select username from registrationmaster where registerid=?", 
							new Object[] {rpm.getRegisterid()}, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString(1);
						}
					});
					rpm.setUsername(username);
				}
			}
			return (list.size()>0)?list:null;
		}catch(Exception e) {
			System.out.println("report admin error :"+e);
			return null;
		}
	}
	
	
	// solve problem update status
	public int solveReportProblemStatus(final int reportid) {
		try {
			int v = template.update("update reportproblemmaster set status='solve' where reportid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, reportid);
				}
			});
			return (v>0)?1:0;
		}catch(Exception e) {
			System.out.println("report admin :"+e);
			return 0;
		}
	}
	
	// check reported problem status solve or not
	public String checkReportProblem(int reportid) {
		try {
			String status = template.queryForObject("select status from reportproblemmaster where reportid=?",new Object[] {reportid}, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			return status;
		}catch(Exception e) {
			System.out.println("report admin :"+e);
			return null;
		}
	}
	
}
