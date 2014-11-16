package com.studynetwork.entities;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.studynetwork.util.DatabaseHelper;

public class Announcement {
	
	private int id;
	private String title; 
	private String content;
	private Timestamp dateTime;
	private int postedBy;
	
	private boolean readed;
	
	public Announcement(int id, String title, String content, Timestamp dateTime, int postedBy, boolean readed){
		this.id = id;
		this.title = title;
		this.content = content;
		this.dateTime = dateTime;
		this.postedBy = postedBy;		
		this.readed = readed;
	}
	
	public Announcement(String title, String content, Timestamp dateTime, int postedBy){
		this.title = title;
		this.content = content;
		this.dateTime = dateTime;
		this.postedBy = postedBy;		
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public Timestamp getDateTime(){
		return this.dateTime;
	}
	
	public boolean isReaded(){
		return this.readed;
	}
	
	public int getPostedBy(){
		return this.postedBy;
	}
	
	public boolean save(){		
		String query = "INSERT INTO announcement(title, content, date, posted_by_id) " + 
					   "VALUES (?,?,?,?,?)";
		Object[] queryParams =  {this.title, this.content, this.dateTime, this.postedBy};
		
		DatabaseHelper dh = new DatabaseHelper();
		try {
			dh.openConnection();
			dh.executeUpdate(query, queryParams);
			dh.CloseConnection();
			return true;
		} catch (SQLException e) {				
			e.printStackTrace();
			return false;
		}									
	}		
	
	public boolean setReaded(int idMember){
		String query = "INSERT INTO read_announcement(readed, announcement_id, member_id) " + 
				   	   "VALUES (?,?,?,?,?)";
		Object[] queryParams =  {true, this.id, idMember};
		
		DatabaseHelper dh = new DatabaseHelper();
		try {
			dh.openConnection();
			dh.executeUpdate(query, queryParams);
			dh.CloseConnection();
			return true;
		} catch (SQLException e) {				
			e.printStackTrace();
			return false;
		}		
	}
	
	
}
