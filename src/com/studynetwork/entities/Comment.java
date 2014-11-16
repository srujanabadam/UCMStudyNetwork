package com.studynetwork.entities;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.studynetwork.util.DatabaseHelper;

public class Comment {
	
	private int id;
	private String text;
	private Timestamp dateTime;
	private int forumId;
	private int memberId;
	
	public Comment(int id, String text, Timestamp dateTime, int forumId, int memberId){
		this.id = id;
		this.text = text;
		this.dateTime = dateTime;
		this.forumId = forumId;
		this.memberId = memberId;
	}

	public Comment(String text, int forumId, int memberId){		
		this.text = text;	
		this.dateTime = new Timestamp(Calendar.getInstance().getTime().getTime()); 
		this.forumId = forumId;
		this.memberId = memberId;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getText(){
		return this.text;
	}
	
	public Timestamp getDate(){
		return this.dateTime;
	}
	
	public int getMemberId(){
		return this.memberId;
	}
	
	public Boolean Save(){
		String query = "";
		if (id == 0){
			query = "INSERT INTO comment(text, date, forumId, member_id) " + 
					"VALUES (?,?,?,?) ";			
		}				
		DatabaseHelper dh = new DatabaseHelper();
		try {
			dh.openConnection();
			dh.executeUpdate(query, getParameters());
			dh.CloseConnection();
			return true;
		} catch (SQLException e) {				
			e.printStackTrace();
			return false;
		}	
	}
	
	private Object[] getParameters(){
		Object[] params = {this.text, this.dateTime, this.forumId, this.memberId};
		return params;
	} 
	
}
