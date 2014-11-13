package com.example.ucmstudynetwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.Object;
import java.util.*;

import android.os.AsyncTask;

public class DatabaseConnector extends AsyncTask<Void,Void,String>
{
	 @Override
     protected String doInBackground(Void... params) 
	 {
         String retval = "";
         try 
         {
             Class.forName("org.postgresql.Driver");
         } 
         catch (ClassNotFoundException e) 
         {
             e.printStackTrace();                
             retval = e.toString() + " class";
             System.exit(1);
         }        
         return retval;
     }
	 
     @Override
     protected void onPostExecute(String value) 
     {
     }	
     
     public Connection OpenConnection()
     {
         String url = "jdbc:postgresql://10.0.2.2:5432/postgres";
         Connection conn = null;
         try 
         {
             conn = DriverManager.getConnection(url, "newuser", "12345");
         } 
         catch (SQLException e) 
         {
        	 String s = e+"conn";
             e.printStackTrace();
             System.out.println("$$$$$$$$$$$$$$$$$$$");
			 System.out.println(s);
			 System.out.println("$$$$$$$$$$$$$$$$$$$$");
         }         
         return conn;
     }
     
     public ResultSet task(Connection conn, String sql)
     {
    	Statement st;
    	ResultSet rs = null;
		try 
		{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return rs;
     }
     public void Closetask(ResultSet rs, Statement st)
     {
    	 try 
    	 {
			rs.close();
			st.close();    
		 } 
    	 catch (SQLException e) 
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
     }
    
     public void CloseConnection (Connection conn)
     {
    	 try 
    	 {
			conn.close();
		 } 
    	 catch (SQLException e) 
    	 {
			e.printStackTrace();
		 }    	  	 
     }  

}
