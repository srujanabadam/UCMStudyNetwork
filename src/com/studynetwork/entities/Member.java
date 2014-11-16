package com.studynetwork.entities;

import java.util.Date;

public class Member {
	
	private int id;
	private Boolean isAdmin;
	private Date dateJoined;
	private int groupId;
	private int userId;
	
	public Member(int id, Boolean isAdmin, int groupId, int userId){
		this.id = id;
		this.isAdmin = isAdmin;
		this.groupId = groupId;
		this.userId = userId;
	}
	
	public int getId(){
		return this.id;
	}
	
	public Boolean getIsAdmin(){
		return this.isAdmin;
	}
	
	
	
	
}