package com.studynetwork.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.studynetwork.util.DatabaseHelper;

public class Forum {
	
	private int id;
	private String title;
	private String description;
	private Date date;
	private int groupId;
	private int moderatorId;
	
	
	public Forum(String title, String description, Date date, int groupId, int moderatorId){
		this.title = title;
		this.description = description;
		this.date = date;
		this.groupId = groupId;
		this.moderatorId = moderatorId;
	}
	
	public Forum(int id, String title, String description, Date date, int groupId, int moderatorId){
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.groupId = groupId;
		this.moderatorId = moderatorId;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public Boolean Save(){
		String query = "";
		if (id == 0){
			query = "INSERT INTO forum(title, description, date, group_id, moderator_id) " + 
					"VALUES ('" + title + "','" + description  + "'," + date + "," + groupId + "," + moderatorId + ")";			
		}
		else{
			query = "UPDATE group SET title = '" + title + "', description = '" + description +  "' " +
					"WHERE id = " + id; 
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
		
	public List<Comment> getComments(){
		List<Comment> comments = new ArrayList();
		
		String query = "SELECT id, text, date, member_id " +
					   "FROM comment WHERE forum_id = " + this.id;		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);
			while (rs.next()){
				comments.add(new Comment(rs.getInt("id"), rs.getString("text"), rs.getDate("date"), this.id,
									   rs.getInt("member_id")));
			}			
			return comments;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
}
