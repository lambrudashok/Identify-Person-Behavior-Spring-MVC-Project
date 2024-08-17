package com.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.PostLayoutModel;
import com.model.PostModel;
import com.repository.CreatePostRepository;


@Service
public class CreatePostService {

	@Autowired
	CreatePostRepository postRepo;
	
	/*add new post and image name*/
	public boolean isaddUserNewPost(PostModel model) {
		return postRepo.isaddUserNewPost(model);
	}
	
	/*view All posts or like,comment date wise decreasing order particular user*/
	public List<PostLayoutModel> ViewAllPosts(int userID){
		return postRepo.ViewAllPosts(userID);
	}
	
	/*Delete post from database*/
	public int deletePost(int postID) {
		return postRepo.deletePost(postID);
	}
	
	
//	/*fetch following user posts all according to particular user*/
//	public List<PostLayoutModel> fetchFollowingAllUserPost(List<Integer> al){
//		List<PostLayoutModel> data = new LinkedList<PostLayoutModel>();
//		if(al.size()>0) {
//			for(Integer userFollowing:al) {
//				List<PostLayoutModel> list=postRepo.ViewAllPosts(userFollowing);
//				if(list!=null) {
//					for(PostLayoutModel pm:list) {
//						data.add(pm);
//					}
//				}
//			}
//			return (data.size()>0)?data:null;
//		}else {
//			return null;
//		}
//	}
//	
//	
//	/*view All posts or like,comment all application users*/
//	public List<PostLayoutModel> ViewAllPosts(){
//		return postRepo.ViewAllPosts();
//	}
	
}
