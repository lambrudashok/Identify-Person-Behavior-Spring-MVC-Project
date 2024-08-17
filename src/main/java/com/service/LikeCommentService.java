package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.PostModel;
import com.repository.LikeCommentRepository;

@Service
public class LikeCommentService {

	@Autowired
	LikeCommentRepository likecommentRepo;
	
	/*comment logic*/
	public boolean isAddComment(PostModel model) {
		return likecommentRepo.isAddComment(model);
	}
	
	// we fetch comment count of post
	public int getCommentCount(int postid) {
		return likecommentRepo.getCommentCount(postid);
	}
	
//	// add like in database
//	public boolean isAddLike(int postid,int registerid) {
//		return likecommentRepo.isAddLike(postid, registerid);
//	}
//	
//	// check user like or not post
//	public int checkLike(int postid,int userID) {
//		return likecommentRepo.checkLike(postid,userID);
//	}
//	
//	// fetch like count of post
//	public int fetchLikeCount(int postid) {
//		return likecommentRepo.fetchLikeCount(postid);
//	}
//		
//	// unlike post logic
//	public int unLikePost(int postid,int userID) {	
//		return likecommentRepo.unLikePost(postid, userID);
//	}
}
