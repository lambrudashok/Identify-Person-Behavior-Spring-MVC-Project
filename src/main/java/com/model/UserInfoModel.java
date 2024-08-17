package com.model;

public class UserInfoModel extends FollowerModel{

	private int registerid;
	private String username;
	private String name;
	private int status;
	private String profileimage;
	
	public int getRegisterid() {
		return registerid;
	}
	public void setRegisterid(int registerid) {
		this.registerid = registerid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
}
