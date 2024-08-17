package com.repository;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.model.PostModel;

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
	
	
//	// fetch last like
//	public int getLikeId() {
//		try {
//			ps =con.prepareStatement("select max(likeid) from likemaster");
//			rs=ps.executeQuery();
//			if(rs.next()){
//				return rs.getInt(1);
//			}else {
//				return 0;
//			}
//		}catch(Exception e) {
//			System.out.println("like repo error :"+e);
//			return -1;
//		}
//	}
//	
//	
//	// add like in database
//	public boolean isAddLike(int postid,int registerid) {
//		try {
//			ps= con.prepareStatement("insert into likemaster value('0',?)");
//			ps.setInt(1, registerid);
//			int v=ps.executeUpdate();
//			
//			int likeid=this.getLikeId();
//			ps=con.prepareStatement("insert into likepostjoin values(?,?)");
//			ps.setInt(1, likeid);
//			ps.setInt(2, postid);
//			int v1=ps.executeUpdate();
//			return v1>0?true:false;
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return false;
//		}
//	}
//	
//	// fetch like count of post
//	public int fetchLikeCount(int postid) {
//		try {
//			ps =con.prepareStatement("select count(lm.likeid) from likemaster lm "
//					+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
//					+ "inner join postmaster pm on pm.postid=lpj.postid "
//					+ "where pm.postid=?");
//			ps.setInt(1, postid);
//			rs=ps.executeQuery();
//			if(rs.next()) {
//				return rs.getInt(1);   // return post like count 
//			}else {
//				return 0;
//			}
//		}catch(Exception e) {
//			System.out.println("error :"+e);
//			return 0;
//		}
//	}
//	
	
	// check person like or not
	public int checkLike(int postid,int userID) {
		
			
		Integer like = template.queryForObject("select lm.registerid from likemaster lm "
					+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
					+ "where lpj.postid=? and lm.registerid=?", new Object[] {postid,userID}, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});     
		return (like>0) ? like : 0;
	}
	
//	// unlike post logic
//		public int unLikePost(int postid,int userID) {
//			try {
//				int like=0;
//				ps=con.prepareStatement("select lm.likeid from likemaster lm "
//						+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
//						+ "where lpj.postid=? and lm.registerid=?");
//				ps.setInt(1, postid);
//				ps.setInt(2, userID);
//				rs=ps.executeQuery();
//				if(rs.next()) {
//					like=rs.getInt(1);
//				}
//				
//				ps=con.prepareStatement("delete from likemaster where likeid=?");
//				ps.setInt(1, like);
//				int v=ps.executeUpdate();
//				
//				return (v>0) ? 1 : 0;
//				
//			}catch(Exception e) {
//				System.out.println("error :"+e);
//				return 0;
//			}
//		}
	
}
