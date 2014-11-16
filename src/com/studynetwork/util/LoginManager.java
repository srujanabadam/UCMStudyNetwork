package com.studynetwork.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.studynetwork.entities.User;

public class LoginManager {
			
	private User user;
	
	public LoginManager(){		
	}
	
	public boolean authenticate(String userName, String password){
		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			String query = "SELECT id, first_name, last_name, is_staff FROM auth_user " + 
						   "WHERE username = '" + userName.replace('-', ' ') + "'";
			ResultSet rs = dh.getQueryResultSet(query);			
			if (rs.next()){
				user = new User(rs.getInt("id"), userName, rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"));
				return true;	
			}						
		}
		catch(SQLException ex){			
			ex.printStackTrace();
		}
				
		return false;		
	}
	
	public User getUser(){
		return this.user;
	}
	
	
	
	
	
}
