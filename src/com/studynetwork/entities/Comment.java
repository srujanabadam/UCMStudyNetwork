package com.studynetwork.entities;

import java.sql.SQLException;
import java.util.Date;

import com.studynetwork.util.DatabaseHelper;

public class Comment {
	
	private int id;
	private String text;
	private Date date;
	private int forumId;
	private int memberId;
	
	public Comment(int id, String text, Date date, int forumId, int memberId){
		this.id = id;
		this.text = text;
		this.date = date;
		this.forumId = forumId;
		this.memberId = memberId;
	}

	public Comment(String text, Date date, int forumId, int memberId){		
		this.text = text;
		this.date = date;
		this.forumId = forumId;
		this.memberId = memberId;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getText(){
		return this.text;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public int getMemberId(){
		return this.memberId;
	}
	
	public Boolean Save(){
		String query = "";
		if (id == 0){
			query = "INSERT INTO comment(text, date, forumId, member_id) " + 
					"VALUES ('" + this.text + "'," + this.date + "," + this.forumId + "," + this.memberId + ")";			
		}				
		DatabaseHelper dh = new DatabaseHelper();
		try {
			dh.openConnection();
			dh.executeUpdate(query);
			dh.CloseConnection();
			return true;
		} catch (SQLException e) {				
			e.printStackTrace();
			return false;
		}	
	}
	
}
