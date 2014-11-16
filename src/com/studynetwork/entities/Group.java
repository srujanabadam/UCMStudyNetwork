package com.studynetwork.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.studynetwork.util.DatabaseHelper;

public class Group {
	
	private int id;
	private String name;
	private String description;
	private Date date;
	private int status;
	private int type;
	private int sectionId;
	
	private Member activeMember;
	
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
	
	public Member getActiveMember(){
		return this.activeMember;
	}
	
	public void setActiveMember(Member m){
		this.activeMember = m;
	}
	
	public Boolean save(){
		String query = "";
		Object[] queryParams;
		if (id == 0){
			query = "INSERT INTO group(name, description, date, status, type, section_id) " + 
					"VALUES ('" + name + "','" + description + "'," + date  + "," + status + ","+type+"," + sectionId + ")";			
			queryParams = getInsertQueryParameters();
		}
		else{
			query = "UPDATE group SET name = '" + name + "', description = '" + description +  "' " +
					"WHERE id = " + id;
			queryParams = getUpdateQueryParameters();
		}
		
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
	
	private Object[] getInsertQueryParameters(){
		Object[] params = {this.name, this.description, this.date, this.status, this.type, this.sectionId};
		return params;
	}
	
	private Object[] getUpdateQueryParameters(){
		Object[] params = {this.name, this.description};
		return params;
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

	public boolean addMember(int userId){
		String query = "INSERT INTO member (is_admin, date_joined, group_id, user_id)  " + 
					   "VALUES (?,?,?,?) ";
		Date today = new Date();		
		Object[] queryParams =  {false, new java.sql.Timestamp(today.getTime()) , this.id, userId};
			
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
	
	public boolean sendInvitations(int[] userId){
		String query = "";		
		for (int i = 0; i < userId.length; i++){
			query +=  "INSERT INTO invitation(status, group_id, sender_id, to_id) " + 
					  "VALUES (1," + this.id + "," + this.activeMember.getId() + "," + userId[i] + "); ";
		}					   
		
		DatabaseHelper dh = new DatabaseHelper();
		try {
			dh.openConnection();
			dh.executeBatch(query);			
			dh.CloseConnection();
								
			return true;
		} catch (SQLException e) {				
			e.printStackTrace();
			return false;
		}		
		
	} 
		
	
}


