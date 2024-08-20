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
	
	/*view All posts or like,comment date wise decreasing order particular user profile page*/
	public List<PostLayoutModel> ViewAllPosts(int userID){
		return postRepo.ViewAllPosts(userID);
	}
	
	/*view All posts or like,comment date wise decreasing order particular user another user profile*/
	public List<PostLayoutModel> ViewAllPosts(int registerid, int userID){
		return postRepo.ViewAllPosts(registerid,userID);
	}
	
	/*Delete post from database*/
	public int deletePost(int postID) {
		return postRepo.deletePost(postID);
	}
	
	
	/*fetch following user posts all according to particular user*/
	public List<PostLayoutModel> fetchFollowingAllUserPost(List<Integer> al,int userID){
		List<PostLayoutModel> data = new LinkedList<PostLayoutModel>();
		if(al!=null) {
			for(Integer userFollowing:al) {
				List<PostLayoutModel> list=postRepo.ViewAllPosts(userFollowing,userID);
				if(list!=null) {
					for(PostLayoutModel pm:list) {
						data.add(pm);
					}
				}
			}
			return (data.size()>0)?data:null;
		}else {
			return null;
		}
	}
	
	
	/*view All posts or like,comment all application users home page*/
	public List<PostLayoutModel> ViewAllApplicationPosts(int userID){
		return postRepo.ViewAllApplicationPosts(userID);
	}
	
}
