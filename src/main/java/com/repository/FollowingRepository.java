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
public class FollowingRepository {

	@Autowired
	JdbcTemplate template;
	
	
	/*fetch last following id*/
	public int getFollowingID() {
		try {
			Integer following = template.queryForObject("select max(followingid) from followingmaster", new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			return (following>0) ? following : 0;
			
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return -1;
		}
		
	}
	
	/*fetch last follower id*/
	public int getFollowerID() {
		try {
			Integer follower = template.queryForObject("select max(followerid) from followermaster", new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			
			return (follower>0) ? follower :0;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return -1;
		}
		
	}
	
	
		
	
	/*insert data following user in database */
	public boolean isAddFollowingUser(final int registerId,final int followid) {
		try {
			// insert follower data
			int v = template.update("insert into  followermaster values('0',?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerId);
				}
			});
			//insert following data
			v = template.update("insert into followingmaster values('0',?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, followid);
				}
			});
			
			final int followeruserID=this.getFollowerID();
			final int followinguserID=this.getFollowingID();
			
			v = template.update("insert into userfollowingfollowerjoin (registerid,followerid) values (?,?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, followid);
					ps.setInt(2, followeruserID);
				}
			});
			
			v = template.update("insert into  userfollowingfollowerjoin (registerid,followingid) values(?,?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerId);
					ps.setInt(2, followinguserID);	
				}
			});
			return (v>0)?true:false;
				
		}catch(Exception e) {
			System.out.println("Following add repo error :"+e);
			return false;
		}
	}
	
	
	/*fetch follower all user*/
	public List<UserInfoModel> fetchAllFollowerUser(int userID){
		List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all follower user  details
		try {
			
			 // store all follower user id
			List<Integer> al = template.query("select fm.followerregisterid as 'followerid' from followermaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followerid=fm.followerid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where rm.registerid=?", new Object[] {userID}, new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							// TODO Auto-generated method stub
							return rs.getInt(1);
						}
					});
			
			for(Integer user:al) {
				
				final UserInfoModel umodel = new UserInfoModel(); // store particular user info
				 
				umodel.setFollowerregisterid(user);  // set follower user id
				
				template.queryForObject("select * from registrationmaster where registerid=?", new Object[] {user}, new RowMapper<Void>() {

					@Override
					public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
						umodel.setUsername(rs.getString("username"));  // set username
						umodel.setName(rs.getString("customername"));  // set customer name
						umodel.setProfileimage(rs.getString("profileimg")); // set profile image name
						return null;
					}
				});
				
				list.add(umodel);  // store user info
			}
			
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	

	
	/*fetch following all user*/
	public List<UserInfoModel> fetchAllFollowingUser(final int userID){
		List<UserInfoModel> list = new ArrayList<UserInfoModel>(); // store all following user  details
		try {
			
			// store all following user id
			List<Integer> al = template.query("select fm.followingregisterid as 'followingid' from followingmaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followingid=fm.followingid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where rm.registerid=?", new PreparedStatementSetter() {
					
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setInt(1, userID);
						}
					}, new RowMapper<Integer>() {

						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							
							return rs.getInt(1);
						}
					});
			
			for(Integer user:al) {
				
				final UserInfoModel umodel = new UserInfoModel(); // store particular user info
				 
				umodel.setFollowingregisterid(user);  // set following user id
				
				template.queryForObject("select * from registrationmaster where registerid=?", new Object[] {user}, 
						new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								umodel.setUsername(rs.getString("username"));  // set username
								umodel.setName(rs.getString("customername"));  // set customer name
								umodel.setProfileimage(rs.getString("profileimg")); // set profile image name
								return null;
							}
						});
				
				list.add(umodel);  // store user info
			}
			
			return (list.size()>0) ? list : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	
	
	/*following user remove*/
	public int removeFollowingUser(int followingUserID ,int registerId) {
		try {
			
			// fetch following user id
			final Integer following = template.queryForObject("select fm.followingid as 'followingid' from followingmaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followingid=fm.followingid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where fm.followingregisterid=? and rm.registerid=?", new Object[] {followingUserID,registerId}, new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt("followingid");
					}
				});
			
			// delete following user
			int v = template.update("delete from followingmaster where followingid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, following);
				}
			});
					
			// fetch another user follower id
			final Integer follower = template.queryForObject("select fm.followerid as 'followerid' from followermaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followerid=fm.followerid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where fm.followerregisterid=? and rm.registerid=?", new Object[] {registerId,followingUserID}, new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt("followerid");
					}
				});
			
			// delete follower user
			 v = template.update("delete from followermaster where followerid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, follower);
				}
			});
			
			return (v>0) ? 1 :0;
		}catch(Exception e) {
			System.out.println("following Repo error :"+e);
			return -1;
		}
	}
	
	/*fetch following all user only id*/
	public List<Integer> followingUserIDs(final int userID){
		try {
			List<Integer> al =new ArrayList<Integer>(); // store all following user id
			
			al = template.query("select fm.followingregisterid as 'followingid' from followingmaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followingid=fm.followingid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where rm.registerid=?", new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setInt(1, userID);
						}
					}, new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getInt(1);
						}
					});
			
			return (al.size()>0) ? al : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	
	
	
	// check another profile user following
	public int checkFollowingStatus(int followingid,int registerid) {
		try {
			
			Integer status = template.queryForObject("select count(fm.followingregisterid)  from registrationmaster rm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.registerid=rm.registerid "
					+ "inner join followingmaster fm on fm.followingid=uffj.followingid "
					+ "where fm.followingregisterid=? and rm.registerid=?", new Object[] {followingid,registerid}, new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getInt(1);
						}
					});
			return (status>0)?1:0;
			
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	
	
	/*fetch following user id only for notification*/
	public List<Integer> fetchAllFollowingUserIds(final int userID){
		try {
			// store all following user id
			List<Integer> al = template.query("select fm.followingregisterid as 'followingid' from followingmaster fm "
					+ "inner join userfollowingfollowerjoin uffj on uffj.followingid=fm.followingid "
					+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
					+ "where rm.registerid=?", new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setInt(1, userID);
						}
					}, new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getInt(1);
						}
					});
			return (al.size()>0) ? al : null;
		}catch(Exception e) {
			System.out.println("following repo error :"+e);
			return null;
		}
	}
	
	
	
}
