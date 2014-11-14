package com.studynetwork.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseHelper {
	
	private static Connection connection;
	private static final String driver = "org.postgresql.Driver";
	private static final String connectionString = "jdbc:postgresql://10.0.2.2:5432/studynetwork";
	private static final String user = "snetwork";
	private static final String password = "studynetwork";
	
	public DatabaseHelper(){			
	}
	
	public void openConnection() throws SQLException{
		try {
            Class.forName(driver);
        } 
        catch (ClassNotFoundException e) {
        	System.out.println("#### --Class ###");
            e.printStackTrace(); 
            System.out.println("#### --Class Driveer -- ###");            
        }  
		connection = DriverManager.getConnection(connectionString, user, password);	
		connection.setAutoCommit(false);
		System.out.println("#### CONECTADO ###");
	}
		         
     public ResultSet getQueryResultSet(String query) throws SQLException{
    	 ResultSet rs;
    	 PreparedStatement st = connection.prepareStatement(query);
    	 rs = st.executeQuery();    	 	     	
         return rs;
     }
     
     /** 
      * Executes INSERT, UPDATE OR DELETE statements
     * @throws SQLException 
      * */
     public boolean executeUpdate(String query) throws SQLException {
    	PreparedStatement st = null;
    	try{
	    	st = connection.prepareStatement(query);
	        st.executeUpdate();
	        connection.commit();
        }
        catch(SQLException ex){
        	try{
        		connection.rollback();
        	}
        	catch(SQLException exr){
        		exr.printStackTrace();
        	}
        	return false;
        }
        finally{
        	if (st != null) st.close();
        }
    	return true;
     }
         
     public Boolean executeBatch(String query) throws SQLException{
    	 PreparedStatement st = null;
    	 try{
 	    	st = connection.prepareStatement(query);
 	        st.executeBatch();
 	        connection.commit();
         }
    	 catch(SQLException ex){
         	try{
         		connection.rollback();
         	}
         	catch(SQLException exr){
         		exr.printStackTrace();
         	}
         	return false;
         }
         finally{
         	if (st != null) st.close();
         }
    	 return true;
     }
     
     public void CloseConnection (){
    	 try {
			connection.close();
		 } 
    	 catch (SQLException e) {
			e.printStackTrace();
		 }    	  	 
     }  

}
