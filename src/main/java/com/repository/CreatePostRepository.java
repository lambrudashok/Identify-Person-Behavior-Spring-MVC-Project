package com.repository;

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

import com.model.PostLayoutModel;
import com.model.PostModel;


@Repository
public class CreatePostRepository {

	@Autowired
	JdbcTemplate template;
	
	//generate postid
		public int autoIncrementPostID() {
			
			String value = ("select max(postid) from postmaster");
			List<Integer> postid= template.query(value, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return (int)rs.getInt(1);
				}
			});
			return (postid.size()>0) ?postid.get(0):-1;	
			
		}
		
		// store post and post image name in database
		public boolean isaddUserNewPost(final PostModel model) {
			
				int postid=this.autoIncrementPostID();
				final int postID=postid+1;
				
				int v =template.update("insert into postmaster values(?,?,(select curdate()),?)",new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, postID);
						ps.setString(2, model.getPost());
						ps.setString(3, model.getImgname());
					}
				});
				
				v = template.update("insert into postregistrationjoin values (?,?)", new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, postID);
						ps.setInt(2, model.getRegisterid());
					}
				});

				return (v>0) ? true : false;
			}
		
		
		
		
		/*view All posts or like,comment date wise decreasing order particular user profile page*/
		public List<PostLayoutModel> ViewAllPosts(final int userID){
			List<PostLayoutModel> list= new LinkedList<PostLayoutModel>(); // store particular user post id ,post,like,comment count
			try {
			List<Integer> alPost =new ArrayList<Integer>();   // store post id particular user
			
			// we fetch post id and store in ArrayList
			alPost = template.query("select pm.postid from postmaster pm "
						+ "inner join postregistrationjoin prj on pm.postid=prj.postid "
						+ "inner join registrationmaster rm on rm.registerid=prj.registerid where rm.registerid=? "
						+ "order by pm.postdate desc", new PreparedStatementSetter() {

							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setInt(1, userID);
							}
							
						},new RowMapper<Integer>() {

							@Override
							public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getInt(1);
							}
						});
				
			
				// we fetch post id ,post , count like ,comment count for particular post
				if(alPost.size()>0) {               // we check user posts not found
					for(Integer lc:alPost) {
						
						final PostLayoutModel pmodel = new PostLayoutModel();
						
						template.queryForObject("select username,profileimg from registrationmaster where registerid=?", 
								new Object[] {userID}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setUsername(rs.getString(1)); // set username
								pmodel.setProfileimage(rs.getString("profileimg")); // set profile photo
								return null;
							}
						});
						
						pmodel.setPostid(lc);      // set post id
						
						template.queryForObject("select post,imgname from postmaster where postid=?", 
								new Object[] {lc}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setPost(rs.getString(1));   // set post
								pmodel.setImgname(rs.getString(2)); // set post imgname
								return null;
							}
						});
						
						// we fetch like count of post
						template.queryForObject("select count(lm.likeid) from likemaster lm "
								+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
								+ "inner join postmaster pm on pm.postid=lpj.postid "
								+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setLikeCount(rs.getInt(1));   // set count like post 
								return null;
							}
						});
						
						
						// we fetch comment count of post
						template.queryForObject("select count(cm.commentid) from commentmaster cm "
								+ "inner join postcommentjoin pcj on pcj.commentid=cm.commentid "
								+ "inner join postmaster pm on pm.postid=pcj.postid "
								+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setCommentCount(rs.getInt(1)); //  set comment count of post
								return null;
							}
						});
						
						// check person like or not
						Integer like=template.queryForObject( "SELECT COUNT(lm.registerid) FROM likemaster lm " +
		                        "INNER JOIN likepostjoin lpj ON lpj.likeid=lm.likeid " +
		                        "WHERE lpj.postid=? AND lm.registerid=?", new Object[] {lc,userID}, new RowMapper<Integer>() {
								@Override
								public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
									return rs.getInt(1);
									
								}
							});
						pmodel.setLike(like>0?like:0);
						
					  list.add(pmodel);
					}
				}
				
				return (list.size()>0) ? list : null;
				}catch(Exception e) {
					System.out.println("error :"+e);
					return null;
				}
		}
		
		
		
		/*view All posts or like,comment date wise decreasing order particular user another user profile or following home page*/
		public List<PostLayoutModel> ViewAllPosts(final int registerid ,final int userID){
			List<PostLayoutModel> list= new LinkedList<PostLayoutModel>(); // store particular user post id ,post,like,comment count
			try {
			List<Integer> alPost =new ArrayList<Integer>();   // store post id particular user
			
			// we fetch post id and store in ArrayList
			alPost = template.query("select pm.postid from postmaster pm "
						+ "inner join postregistrationjoin prj on pm.postid=prj.postid "
						+ "inner join registrationmaster rm on rm.registerid=prj.registerid where rm.registerid=? "
						+ "order by pm.postdate desc", new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setInt(1, registerid);
							}
						},new RowMapper<Integer>() {

							@Override
							public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getInt(1);
							}
						});
				// we fetch post id ,post , count like ,comment count for particular post
				if(alPost.size()>0) {               // we check user posts not found
					for(Integer lc:alPost) {
						
						final PostLayoutModel pmodel = new PostLayoutModel();
						
						template.queryForObject("select username,profileimg from registrationmaster where registerid=?", 
								new Object[] {registerid}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setUsername(rs.getString(1)); // set username
								pmodel.setProfileimage(rs.getString("profileimg")); // set profile photo
								return null;
							}
						});
						
						pmodel.setPostid(lc);      // set post id
						
						template.queryForObject("select post,imgname from postmaster where postid=?", 
								new Object[] {lc}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setPost(rs.getString(1));   // set post
								pmodel.setImgname(rs.getString(2)); // set post imgname
								return null;
							}
						});
						
						// we fetch like count of post
						template.queryForObject("select count(lm.likeid) from likemaster lm "
								+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
								+ "inner join postmaster pm on pm.postid=lpj.postid "
								+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setLikeCount(rs.getInt(1));   // set count like post 
								return null;
							}
						});
						
						
						// we fetch comment count of post
						template.queryForObject("select count(cm.commentid) from commentmaster cm "
								+ "inner join postcommentjoin pcj on pcj.commentid=cm.commentid "
								+ "inner join postmaster pm on pm.postid=pcj.postid "
								+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {

							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setCommentCount(rs.getInt(1)); //  set comment count of post
								return null;
							}
						});
						
						// check person like or not
						Integer like=template.queryForObject( "SELECT COUNT(lm.registerid) FROM likemaster lm " +
		                        "INNER JOIN likepostjoin lpj ON lpj.likeid=lm.likeid " +
		                        "WHERE lpj.postid=? AND lm.registerid=?", new Object[] {lc,userID}, new RowMapper<Integer>() {

								@Override
								public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
									return rs.getInt(1);
									
								}
							});
						pmodel.setLike(like>0?like:0);
						
					  list.add(pmodel);
					}
				}
				
				return (list.size()>0) ? list : null;
				}catch(Exception e) {
					System.out.println("error :"+e);
					return null;
				}
		}
		
		
		/*view All posts or like,comment all application users home page*/
		public List<PostLayoutModel> ViewAllApplicationPosts(int userID){
			List<PostLayoutModel> list= new LinkedList<PostLayoutModel>(); // store particular application user post id ,post,like,comment count
			
			try {
				List<Integer> alPost = new ArrayList<Integer>();   // store post id users
				
				// we fetch post id and store in ArrayList
				alPost = template.query("select pm.postid from postmaster pm "
						+ "inner join postregistrationjoin prj on pm.postid=prj.postid "
						+ "inner join registrationmaster rm on rm.registerid=prj.registerid "
						+ "order by pm.postdate desc", new RowMapper<Integer>() {
							@Override
							public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getInt(1); 
							}
						});
				// we fetch post id ,post , count like ,comment count of user post
				if(alPost.size()>0) {               // we check  posts not found
					for(Integer lc:alPost) {
						
						final PostLayoutModel pmodel = new PostLayoutModel();
						
						template.queryForObject("select rm.username,rm.profileimg from registrationmaster rm "
								+ "inner join postregistrationjoin prj on prj.registerid=rm.registerid "
								+ "inner join postmaster pm on pm.postid=prj.postid "
								+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {
									@Override
									public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
										pmodel.setUsername(rs.getString(1)); // set username
										pmodel.setProfileimage(rs.getString("profileimg")); // set profile photo name
										return null;
									}
								});
						
						pmodel.setPostid(lc);      // set post id
						
						template.queryForObject("select post,imgname from postmaster where postid=?", new Object[] {lc}, new RowMapper<Void>() {
							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								pmodel.setPost(rs.getString(1));   // set post
								pmodel.setImgname(rs.getString(2)); // set post image name
								return null;
							}
						});
						// we fetch like count of post
						template.queryForObject("select count(lm.likeid) from likemaster lm "
								+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
								+ "inner join postmaster pm on pm.postid=lpj.postid "
								+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {
									@Override
									public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
										pmodel.setLikeCount(rs.getInt(1));   // set count like post 
										return null;
									}
								});
						// we fetch comment count of post
						template.queryForObject("select count(cm.commentid) from commentmaster cm "
								+ "inner join postcommentjoin pcj on pcj.commentid=cm.commentid "
								+ "inner join postmaster pm on pm.postid=pcj.postid "
								+ "where pm.postid=?", new Object[] {lc}, new RowMapper<Void>() {
									@Override
									public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
										pmodel.setCommentCount(rs.getInt(1)); //  set comment count of post
										return null;
									}
								});
						// check login person like or not
						Integer like=template.queryForObject( "SELECT COUNT(lm.registerid) FROM likemaster lm " +
		                        "INNER JOIN likepostjoin lpj ON lpj.likeid=lm.likeid " +
		                        "WHERE lpj.postid=? AND lm.registerid=?", new Object[] {lc,userID}, new RowMapper<Integer>() {

								@Override
								public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
									return rs.getInt(1);
								}
							});
						pmodel.setLike(like>0?like:0); // set like or not
						
					  list.add(pmodel);
					}
				}
				
				return (list.size()>0) ? list:null;
			}catch(Exception e) {
				System.out.println("post master repository error :"+e);
				return null;
			}
		}
		
		
		
		
		/*Delete post from database*/
		public int deletePost(final int postID) {
			try {
				
				int value = template.update("delete from postmaster where postid=?", new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, postID);
					}
				});
				
				return (value>0)?1:0;
			}catch(Exception e) {
				System.out.println("post repo error :"+e);
				return -1;
			}
		}
}
