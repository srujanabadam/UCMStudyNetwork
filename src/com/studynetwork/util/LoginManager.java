package com.studynetwork.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager {
			
	
	public LoginManager(){		
	}
	
	public boolean authenticate(String userName, String password){
		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			String query = "SELECT first_name, last_name, is_staff FROM auth_user " + 
						   "WHERE username = '" + userName.replace('-', ' ') + "'";
			ResultSet rs = dh.getQueryResultSet(query);			
			if (rs.next()){
				return true;	
			}						
		}
		catch(SQLException ex){			
			ex.printStackTrace();
		}
				
		return false;		
	}
	
	
	
	
	
}
