package com.model;

public class LikeModel {

	private int likeid;
	private int registerid;
	private int likeCount;
	private int like;
	
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getLikeid() {
		return likeid;
	}
	public void setLikeid(int likeid) {
		this.likeid = likeid;
	}
	public int getRegisterid() {
		return registerid;
	}
	public void setRegisterid(int registerid) {
		this.registerid = registerid;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}
