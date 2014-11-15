package com.studynetwork.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.studynetwork.util.DatabaseHelper;

public class Group {
	
	private int id;
	private String name;
	private String description;
	private Date date;
	private int status;
	private int type;
	private int sectionId;
	
	public Group(){}
	
	public Group(int id){
		this.id = id;
		loadGroup();
	} 
	
	public Group(String name, String description, Date date, int status, int type, int sectionId){
		this.name = name;
		this.description = description;
		this.date = date;
		this.status = status;
		this.type = type;
		this.sectionId = sectionId;
	}
		
	public Group(int id, String name, String description, Date date, int status, int type, int sectionId){
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.status = status;
		this.type = type;
		this.sectionId = sectionId;
	}	

	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}		
	
	public int getType(){
		return type;
	}
	
	public Boolean save(){
		String query = "";
		if (id == 0){
			query = "INSERT INTO group(name, description, date, status, type, section_id) " + 
					"VALUES ('" + name + "','" + description + "'," + date  + "," + status + ","+type+"," + sectionId + ")";			
		}
		else{
			query = "UPDATE group SET name = '" + name + "', description = '" + description +  "' " +
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
	
	private void loadGroup(){
		String query = "SELECT name, description, date, status, type, section_id " +
					   "FROM \"group\" WHERE id = " + id;
		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);
			if (rs.next()){
				name = rs.getString("name");
				description = rs.getString("description");
				date = rs.getDate("date");
				status = rs.getInt("status");
				sectionId = rs.getInt("section_id");
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public List<Forum> getForums(){
		List<Forum> forums = new ArrayList();
		
		String query = "SELECT id, title, description, date, moderator_id " +
					  "FROM forum WHERE group_id = " + this.id;		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);
			while (rs.next()){
				forums.add(new Forum(rs.getInt("id"), rs.getString("title"), rs.getString("description"), 
									 rs.getDate("date"), this.id, rs.getInt("moderator_id")));
			}			
			return forums;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
}


