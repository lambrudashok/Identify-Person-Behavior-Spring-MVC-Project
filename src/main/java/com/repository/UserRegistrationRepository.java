package com.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.model.ProfileInformationModel;
import com.model.RegistrationModel;
import com.model.UserInfoModel;

@Repository
public class UserRegistrationRepository {

	@Autowired
	JdbcTemplate template;
	
	/* new user insert data in registration master table*/
	public boolean isAddNewUserRegistration(final RegistrationModel register) {
		
		int v = template.update("insert into registrationmaster (customername,email,username,password) values (?,?,?,?)", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, register.getCustomername());
				ps.setString(2, register.getEmail());
				ps.setString(3, register.getUsername());
				ps.setString(4, register.getPassword());	
			}
		});
		return (v>0) ?true:false;
	}
	
	/*check email duplicate or not */
	public boolean searchEmail(final String email) {
		List<Integer> v = template.query("select registerid from registrationmaster where email=?", new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, email);
			}
			
		}, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("registerid");
			}
		});
		return (v.size()>0) ?true:false;
	}
	
	
	/*check username duplicate or not */
	public boolean searchUsername(final String username) {
		List<Integer> list = template.query("select registerid from registrationmaster where username=?", new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, username);
			}	
		},new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("registerid");
			}
		});
		return (list.size()>0) ?true:false;
	}
	
	/*profile information show*/
	public List<ProfileInformationModel> profileInformation(int registerid) {
		
		List<ProfileInformationModel> list = new ArrayList<ProfileInformationModel>();
			final ProfileInformationModel accountInfo = new ProfileInformationModel();
			
			// store customer name and username in model
			String userInfo= ("select customername, username, profileimg from registrationmaster where registerid=?");
	        template.query(userInfo, new Object[]{registerid}, new RowMapper<Void>() {
	            @Override
	            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
	                accountInfo.setCustomername(rs.getString("customername"));
	                accountInfo.setUsername(rs.getString("username"));
	                accountInfo.setProfilephoto(rs.getString("profileimg"));
					return null;
	            }
	        });
			
			// we store following count
	        String followingCount = ("select count(fm.followingid) as 'following count' from followingmaster fm " 
			      + "inner join userfollowingfollowerjoin uffj on uffj.followingid=fm.followingid " 
	              + "inner join registrationmaster rm on rm.registerid=uffj.registerid " 
	              + "where rm.registerid=?");
	        template.query(followingCount, new Object[]{registerid}, new RowMapper<Void>() {
	            @Override
	            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
	                accountInfo.setFollowingCount(rs.getInt("following count"));
					return null;
	            }
	        });
			
			// we store follower count
			 String followerCount = ("select count(fm.followerid) as 'follower count' from followermaster fm "
			 		+ "inner join userfollowingfollowerjoin uffj on uffj.followerid=fm.followerid "
			 		+ "inner join registrationmaster rm on rm.registerid=uffj.registerid "
			 		+ "where rm.registerid=?");
		        template.query(followerCount, new Object[]{registerid}, new RowMapper<Void>() {
		            @Override
		            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
		                accountInfo.setFollowerCount(rs.getInt("follower count"));
						return null;
		            }
		        });
			
			// store post count
			String postCount =("select count(pm.postid) as 'post count' from postmaster pm "
					+ "inner join postregistrationjoin prj on prj.postid=pm.postid "
					+ "inner join registrationmaster rm on rm.registerid=prj.registerid "
					+ "where rm.registerid=?");
			template.query(postCount, new Object[] {registerid}, new RowMapper<Void>() {
				@Override
				public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
					accountInfo.setPostCount(rs.getInt("post count"));
					return null;
				}
			});
			
			
			// store bio
			String bio = ("select bm.bio from biomaster bm "
					+ "inner join bioregistrationjoin brj on brj.bioid=bm.bioid "
					+ "inner join registrationmaster rm on rm.registerid=brj.registerid "
					+ "where brj.registerid=?");
			template.query(bio, new Object[] {registerid},new RowMapper<Void>() {
				@Override
				public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
					accountInfo.setBio(rs.getString(1));
					return null;
				}
			});
			
			list.add(accountInfo);
			return (list.size()>0)?list:null;
		
	}
	
	
	// fetch username and name particular user
	public UserInfoModel getUserInfo(int registerid){
		final UserInfoModel model = new UserInfoModel();
		template.query("select username,customername,profileimg from registrationmaster where registerid=?", new Object[] {registerid},new RowMapper<Void>() {
			@Override
			public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
				model.setUsername(rs.getString("username"));
				model.setName(rs.getString("customername"));
				model.setProfileimage(rs.getString("profileimg"));
				return null;
			}
			
		});
		return model;
	}
	
	
	//update or add profile image
	public boolean isAddProfilePhoto(final RegistrationModel model) {
		try {
			
			int v = template.update("update registrationmaster set profileimg=? where registerid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, model.getProfileimgname());
					ps.setInt(2, model.getRegisterid());
				}
			});
			
			return (v>0)?true:false;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return false;
		}
	}
	
	//update username
	public int isUpdateUsername(final String username,final int registerid) {
		try {
			int v = template.update("update registrationmaster set username=? where registerid=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, username);
					ps.setInt(2, registerid);
				}
			});
			
			return (v>0)?1:0;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	
	//update email
		public int isUpdateEmail(final String email, final int registerid) {
			try {
				int v = template.update("update registrationmaster set email=? where registerid=?", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, email);
						ps.setInt(2, registerid);
					}
				});
				
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
		
	//update customer name
		public int isUpdateCustomerName(final String name, final int registerid) {
			try {
				int v = template.update("update registrationmaster set customername=? where registerid=?", new PreparedStatementSetter() {	
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, name);
						ps.setInt(2, registerid);
					}
				});
				
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
	
		
		/*generate bio id end user*/
		public int bioIDGenerate() {
			try {
				Integer bio = template.queryForObject("select max(bioid) from biomaster", new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt(1);
					}
				});
				return (bio>0)?bio:0;
			}catch(Exception e) {
				System.out.println("bio reop error :"+e);
				return -1;
			}
		}
		
		/*Add Bio*/
		public boolean isaddBio(final String bio, final int registerid) {
			try {
				int v = template.update("insert into biomaster values('0',?)", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, bio);
					}
				});
				
				// get bioid
				final int bioid=this.bioIDGenerate();
				v = template.update("insert into bioregistrationjoin values (?,?)", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, bioid);
						ps.setInt(2, registerid);
					}
				});
				
				return (v>0)?true:false;
			}catch(Exception e) {
				System.out.println("bio repo error :"+e);
				return false;
			}
		}
		
		/*search bio in database*/
		public int searchBio(int registerId) {
			try {
				Integer bioid = template.queryForObject("select bm.bioid from biomaster bm "
						+ "inner join bioregistrationjoin brj on brj.bioid=bm.bioid "
						+ "inner join registrationmaster rm on rm.registerid=brj.registerid "
						+ "where rm.registerid=?", new Object[] {registerId}, new RowMapper<Integer>() {
							@Override
							public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getInt(1);
							}
						});	
				return (bioid>0)?bioid:0;
			}catch(Exception e) {
				System.out.println("bio repo error :"+e);
				return -1;
			}
		}
		
		
		//update bio
		public int isUpdateBio(final String bio, final int bioid) {
			try {
				int v = template.update("update biomaster set bio=? where bioid=?", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, bio);
						ps.setInt(2, bioid);
					}
				});
				
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
		
		
		/*check request account delete*/
		public int checkRequestDelete(int registerId) {
			try {
				Integer uid = template.queryForObject("select count(registerid) from deleterequest where registerid=?", new Object[] {registerId}, new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt(1);
					}
				});
				return (uid>0)?uid:0;
			}catch(Exception e) {
				System.out.println("request repo :"+e);
				return -1;
			}
		}
		
		/*recover delete requested account*/
		public int recoverAccount(final int register) {
			try {
				int v = template.update("delete from deleterequest where registerid=?", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, register);
					}
				});
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("register repo :"+e);
				return -1;
			}
		}
		
		
		/*delete account user*/
		public int deleteUserAccount(final int registerId) {
			try {
				int v = template.update("insert into deleterequest values(?,(select curdate()))", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, registerId);
					}
				});
				return (v>0)?1:0;
			}catch(Exception e) {
				System.out.println("register repo error :"+e);
				return -1;
			}
		}
	
}
