package com.studynetwork.entities;

public class User {
	
	private int id;
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;	
	
	public User(int id, String userName, String email, String firstName, String lastName){
		this.id = id;
		this.userName = userName;		
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}			
	
	public Boolean changePassword(){		
		return true;
	}
	
	public int getId(){
		return id;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	
}
