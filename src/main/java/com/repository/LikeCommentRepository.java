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

import com.model.PostModel;
import com.model.*;

@Repository
public class LikeCommentRepository {

	@Autowired
	JdbcTemplate template;
	
	/*get last added comment id*/
	
	public int getCommentId() {
		try {
			Integer commentid = template.queryForObject("select max(commentid) from commentmaster", new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			
			return (commentid>0) ? commentid : 0 ;
			
		}catch(Exception e) {
			System.out.println("like repo error :"+e);
			return -1;
		}
	}
	
	/*comment logic*/
	public boolean isAddComment(final PostModel model) {
		try {
			int value = template.update("insert into commentmaster values('0',?,(select curdate()),?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, model.getComment());
					ps.setInt(2, model.getRegisterid());
				}
			});
			
			final int commentID=this.getCommentId();
			value = template.update("insert into postcommentjoin values (?,?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, model.getPostid());
					ps.setInt(2, commentID);
				}
			});
			
			return (value>0)?true:false;
		}catch(Exception e) {
			System.out.println("like repo error :"+e);
			return false;
		}
	}
	
	// we fetch comment count of post
	public int getCommentCount(int postid) {
		try {
			Integer pid = template.queryForObject("select count(cm.commentid) from commentmaster cm "
					+ "inner join postcommentjoin pcj on pcj.commentid=cm.commentid "
					+ "inner join postmaster pm on pm.postid=pcj.postid "
					+ "where pm.postid=?", new Object[] {postid}, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);	
				}
			});
			return (pid>0) ? pid : 0;
		}catch(Exception e) {
			System.out.println("error "+e);
			return -1;
		}
	}
	
	
	// fetch post comment details 
		public List<PostLayoutModel> getCommentDetails(int postid) {
			final List<PostLayoutModel> commentlist = new ArrayList<PostLayoutModel>(); // store user comment details
			try {
				// fetch particular post comments 
				List<CommentModel> list= template.query("select cm.registerid,cm.comment,cm.commentdate from commentmaster cm "
						+ "inner join postcommentjoin pj on pj.commentid=cm.commentid "
						+ "inner join postmaster pm on pm.postid=pj.postid "
						+ "where pm.postid=?", new Object[] {postid}, new RowMapper<CommentModel>() {
					@Override
					public CommentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						PostLayoutModel pm = new PostLayoutModel();
						pm.setRegisterid(rs.getInt(1));
						pm.setComment(rs.getString(2));
						pm.setCommentDate(rs.getDate(3));
						return pm;	
					}
				});
				
				// fetch details comment user
				if(list.size()>0) {
					for(final CommentModel pl : list) {
						template.queryForObject("select username,profileimg from registrationmaster where registerid=?", new Object[] {pl.getRegisterid()},new RowMapper<Void>() {
							@Override
							public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
								PostLayoutModel pm = new PostLayoutModel();
								pm.setUsername(rs.getString(1)); // set username
								pm.setProfileimage(rs.getString(2)); // set profile photo name
								pm.setRegisterid(pl.getRegisterid()); // set registerid
								pm.setComment(pl.getComment());       // set comment
								pm.setCommentDate(pl.getCommentDate()); // set comment date
								commentlist.add(pm);
								return null;
							}					
						});
					}
				}
				return (commentlist.size()>0) ? commentlist : null;
			}catch(Exception e) {
				System.out.println("error commet"+e);
				return null;
			}
		}
	
	
	// fetch last like
	public int getLikeId() {
		try {
			Integer like = template.queryForObject("select max(likeid) from likemaster", new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			return (like>0)? like : 0;
		}catch(Exception e) {
			System.out.println("like repo error :"+e);
			return -1;
		}
	}
	
	
	// add like in database
	public boolean isAddLike(final int postid,final int registerid) {
		try {
			
			int v = template.update("insert into likemaster value('0',?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, registerid);
				}
			});
			
			
			final int likeid=this.getLikeId();
			v = template.update("insert into likepostjoin values(?,?)", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, likeid);
					ps.setInt(2, postid);
				}
			});
			
			return (v>0)?true:false;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return false;
		}
	}
	
	// fetch like count of post
	public int fetchLikeCount(int postid) {
		try {
			int likecount = template.queryForObject("select count(lm.likeid) from likemaster lm "
					+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
					+ "inner join postmaster pm on pm.postid=lpj.postid "
					+ "where pm.postid=?", new Object[] {postid}, new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getInt(1);   // return post like count
						}
					});
			return (likecount>0) ? likecount : 0;
			
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	
	// check person like or not
	public int checkLike(int postid,int userID) {
		Integer like = template.queryForObject("select count(lm.registerid) from likemaster lm "
					+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
					+ "where lpj.postid=? and lm.registerid=?", new Object[] {postid,userID}, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});     
		return (like>0) ? like : 0;
	}
	
	// unlike post logic
		public int unLikePost(int postid,int userID) {
			try {
				
				List<Integer> like = template.query("select lm.likeid from likemaster lm "
						+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
						+ "where lpj.postid=? and lm.registerid=?", new Object[] {postid,userID}, new RowMapper<Integer>() {
							@Override
							public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getInt(1);
							}
						});
				int v=0;
				for(final Integer unlike : like) {
					v = template.update("delete from likemaster where likeid=?", new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setInt(1, unlike);
						}
					});
				}
				return (v>0) ? 1 : 0;
				
			}catch(Exception e) {
				System.out.println("error unlike :"+e);
				return 0;
			}
		}
	
}
