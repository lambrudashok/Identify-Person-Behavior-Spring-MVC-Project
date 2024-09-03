package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.UserInfoModel;
import com.repository.FollowingRepository;

@Service
public class FollowingService {

	@Autowired
	FollowingRepository followingRepo;
	
	/*insert data following user in database */
	public boolean isAddFollowingUser(int registerId,int followid) {
		return followingRepo.isAddFollowingUser(registerId,followid);
	}
	
	/*fetch following all user details*/
	public List<UserInfoModel> fetchAllFollowingUser(int userID){
		return followingRepo.fetchAllFollowingUser(userID);
	}
	
	/*following user remove*/
	public int removeFollowingUser(int followingUserID ,int registerId) {
		return followingRepo.removeFollowingUser(followingUserID, registerId);
	}
	
	/*fetch follower all user*/
	public List<UserInfoModel> fetchAllFollowerUser(int userID){
		return followingRepo.fetchAllFollowerUser(userID);
	}
	
	/*fetch following all user only id*/
	public List<Integer> followingUserIDs(int userID){
		return followingRepo.followingUserIDs(userID);
	}
	
	// check another profile user following
	public int checkFollowingStatus(int followingid,int registerid) {
		return followingRepo.checkFollowingStatus(followingid,registerid);
	}
	
	/*fetch following user id only for notification*/
	public List<Integer> fetchAllFollowingUserIds(final int userID){
		return followingRepo.fetchAllFollowingUserIds(userID);
	}
	
	
	
}
