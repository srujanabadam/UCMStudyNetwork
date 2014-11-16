package com.studynetwork.entities;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.studynetwork.util.DatabaseHelper;

public class Invitation {
	private int id;
	private Timestamp dateTime;
	private int status; 
	private int groupId;
	private String groupName;
	private int senderId;
	private String senderName;
	private int toId;
	
	public Invitation(int id, Timestamp dateTime, int status, int groupId, String groupName, int senderId, String senderName, int toId){
		this.id = id;
		this.dateTime = dateTime;
		this.status = status;
		this.groupId = groupId;
		this.groupName = groupName;
		this.senderId = senderId;
		this.senderName = senderName;
		this.toId = toId;
	}
	
	public int getId(){
		return this.id;
	}
	
	public Timestamp dateTime(){
		return this.dateTime;
	}
	
	public int getStatus(){
		return this.status;
	}
	
	public int getGroupId(){
		return this.groupId;
	}
	
	public String getGroupName(){
		return this.groupName;
	}
	
	public int getSenderId(){
		return this.senderId;
	}
	
	public String getSenderName(){
		return this.senderName;
	}
	
	public int getToId(){
		return this.toId;
	}
		
	public boolean changeStatus(int status){
		this.status = status;
		
		String query = "UPDATE invitation SET status = " + this.status + " " + 
					   "WHERE id = " + this.id;
		
		Object[] queryParams =  {};
		
		DatabaseHelper dh = new DatabaseHelper();
		try {
			dh.openConnection();
			dh.executeUpdate(query, queryParams);
			dh.CloseConnection();
			
			if (this.status == 2){
				Group group = new Group(this.groupId);
				group.addMember(this.toId);
			}			
			return true;
		} catch (SQLException e) {				
			e.printStackTrace();
			return false;
		}		
		
	}
		
	
}


