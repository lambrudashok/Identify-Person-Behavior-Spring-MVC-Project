package com.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.model.UserInfoModel;


@Repository
public class SearchRepository {

	@Autowired
	JdbcTemplate template;
	
	/*fetch all user details for searching*/
	public List<UserInfoModel> fetchAllUserDetails(){
		try {
			// store all user  details
			List<UserInfoModel> list = template.query("select registerid,username,customername,profileimg from registrationmaster", new RowMapper<UserInfoModel>() {
				@Override
				public UserInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserInfoModel user = new UserInfoModel();
					user.setRegisterid(rs.getInt("registerid"));
					user.setUsername(rs.getString("username"));
					user.setName(rs.getString("customername"));
					user.setProfileimage(rs.getString("profileimg"));
					return user;
				}
			});
					
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("search repo error :"+e);
			return null;
		}
	}
	
	
	
	/*fetch all user details for searching using id home page loading*/
	public List<UserInfoModel> fetchAllUserDetails(int registerid){
		try {
			final List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all user  details
			
			// store not following user
			template.query("select registerid,username,customername,profileimg from registrationmaster "
					+ "where registerid NOT IN (select fm.followingregisterid  from registrationmaster rm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
					+ "where rm.registerid=?)", new Object[] {registerid}, new RowMapper<UserInfoModel>() {
						@Override
						public UserInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
							UserInfoModel user = new UserInfoModel();
							user.setRegisterid(rs.getInt("registerid"));
							user.setUsername(rs.getString("username"));
							user.setName(rs.getString("customername"));
							user.setProfileimage(rs.getString("profileimg"));
							user.setStatus(0);
							list.add(user);
							return null;
						}
					});
			// store following user
			template.query("select registerid,username,customername,profileimg from registrationmaster "
					+ "where registerid IN (select fm.followingregisterid  from registrationmaster rm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
					+ "where rm.registerid=?)", new Object[] {registerid}, new RowMapper<UserInfoModel>() {
						@Override
						public UserInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
							UserInfoModel user = new UserInfoModel();
							user.setRegisterid(rs.getInt("registerid"));
							user.setUsername(rs.getString("username"));
							user.setName(rs.getString("customername"));
							user.setProfileimage(rs.getString("profileimg"));
							user.setStatus(1);
							list.add(user);
							return null;
						}
					});		
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	
	
	
	
//	/*fetch all user details for searching using name*/
//	public List<UserInfoModel> fetchAllUserDetails(String name ,int registerid){
//		List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all user  details
//		try {
//						
//			
//			// store not following user
//			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster "
//					+ "where username like (?) and registerid NOT IN (select fm.followingregisterid  from registrationmaster rm "
//					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
//					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
//					+ "where rm.registerid=?)");
//			ps.setString(1, name+"%");
//			ps.setInt(2, registerid);
//			rs=ps.executeQuery();
//			while(rs.next()) {
//				UserInfoModel user = new UserInfoModel();
//				user.setRegisterid(rs.getInt("registerid"));
//				user.setUsername(rs.getString("username"));
//				user.setName(rs.getString("customername"));
//				user.setProfileimage(rs.getString("profileimg"));
//				user.setStatus(0);
//				list.add(user);
//			}
//			
//			// store following user	
//			ps = con.prepareStatement("select registerid,username,customername,profileimg from registrationmaster "
//					+ "where username like (?) and registerid IN (select fm.followingregisterid  from registrationmaster rm "
//					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
//					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
//					+ "where rm.registerid=?)");
//			ps.setString(1, name+"%");
//			ps.setInt(2, registerid);
//			rs=ps.executeQuery();
//			while(rs.next()) {
//				UserInfoModel user = new UserInfoModel();
//				user.setRegisterid(rs.getInt("registerid"));
//				user.setUsername(rs.getString("username"));
//				user.setName(rs.getString("customername"));
//				user.setProfileimage(rs.getString("profileimg"));
//				user.setStatus(1);
//				list.add(user);
//			}
//			
//			return (list.size()>0) ? list : null;
//		}catch(Exception e) {
//			System.out.println("following repo error :"+e);
//			return null;
//		}
//	}
//	
//	
	/*fetch all user details for searching using name JSON search profile page used*/
	public List<UserInfoModel> fetchAllUserDetails(final String name){
		try {
			// store all user  details
			final List<UserInfoModel> list = template.query("select registerid,username,customername,profileimg from registrationmaster where username like (?) ", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, name+"%");
				}
			}, new RowMapper<UserInfoModel>() {

				@Override
				public UserInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserInfoModel user = new UserInfoModel();
					user.setRegisterid(rs.getInt("registerid"));
					user.setUsername(rs.getString("username"));
					user.setName(rs.getString("customername"));
					user.setProfileimage(rs.getString("profileimg"));
					return user;
				}
			});
					
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("search repo error :"+e);
			return null;
		}
	}
}
