package com.example.ucmstudynetwork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private String username;
	private String password;

	private EditText editTextUsername;
	private EditText editTextPassword;	
	
	 @Override
	   public void onCreate(Bundle savedInstanceState) 
	   {
		  super.onCreate(savedInstanceState); // call superclass's version
	      setContentView(R.layout.login_page); // inflate the GUI	   
	      
	      if ( savedInstanceState == null ) // the app just started running
	      {
	         username = "";
	         password = "";
	                  
	      } // end if
	      else // app is being restored from memory, not executed from scratch
	      {
	    	  username = savedInstanceState.getString(USERNAME);	      
	    	  password = savedInstanceState.getString(PASSWORD);	
	      }
	      
	      editTextUsername = (EditText)findViewById(R.id.editTextUsername);
	      editTextUsername.addTextChangedListener(usernameWatcher);
	      
	      editTextPassword = (EditText)findViewById(R.id.editTextPassword);
	      editTextPassword.addTextChangedListener(passwordWatcher);
	      
	      Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
	      buttonLogin.setOnClickListener(buttonLoginListener);
	      
	      Button buttonfrgtPwd = (Button) findViewById(R.id.buttonfrgtpwd);
	      buttonfrgtPwd.setOnClickListener(buttonfrgtpwdListener);
	      
	   }
	 OnClickListener buttonfrgtpwdListener = new OnClickListener()	 
	 {
		@Override
		public void onClick(View v) 
		{	
			Intent intent = new Intent(MainActivity.this,ForgotPassword.class);
	        startActivity(intent);
		}
	 };
	 
	 OnClickListener buttonLoginListener = new OnClickListener()	 
	 {	 	     
	     
		@Override
		public void onClick(View v) 
		{		     
			if(username.toString().length()!=0)
			{				
				if(password.toString().length()!=0)
				{
					Intent intent = new Intent(MainActivity.this, TestAllgroups.class); 
					startActivity(intent);
				}
				else
				{
		            // create a new AlertDialog Builder
		            AlertDialog.Builder builder = 
		               new AlertDialog.Builder(MainActivity.this);
		      
		            // set dialog title & message, and provide Button to dismiss
		            builder.setTitle(R.string.errorTitlepwd); 
		            builder.setMessage(R.string.errorMessagepwd);
		            builder.setPositiveButton(R.string.errorButton, null); 
		            builder.show(); // display the Dialog
		         } // end else			

			}
			else
	         {
	            // create a new AlertDialog Builder
	            AlertDialog.Builder builder = 
	               new AlertDialog.Builder(MainActivity.this);
	      
	            // set dialog title & message, and provide Button to dismiss
	            builder.setTitle(R.string.errorTitleUname); 
	            builder.setMessage(R.string.errorMessageUname);
	            builder.setPositiveButton(R.string.errorButton, null); 
	            builder.show(); // display the Dialog
	         } // end else


			
		}
	 };

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
	   super.onSaveInstanceState(outState);
	   
	   outState.putString(USERNAME, username);
	   outState.putString(PASSWORD, password);
	  
	} // end method onSaveInstanceState
	

	 private TextWatcher passwordWatcher = new TextWatcher() 
	 {
	      // called when the user enters a number
	      @Override
	      public void onTextChanged(CharSequence s, int start,int before, int count) 
	      {         
	         // convert billEditText's text to a double
	         try
	         {
	        	 password = s.toString();
	         } // end try
	         catch (Exception e)
	         {
	        	 password = ""; // default if an exception occurs
	         } // end catch 
	         
      } // end method onTextChanged

		@Override
		public void afterTextChanged(Editable arg0) 
		{}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3)
		{}
	 };
	 
	 private TextWatcher usernameWatcher = new TextWatcher() 
	 {
	      // called when the user enters a number
	      @Override
	      public void onTextChanged(CharSequence s, int start,int before, int count) 
	      {         
	         // convert billEditText's text to a double
	         try
	         {
	        	 username = s.toString();
	         } // end try
	         catch (Exception e)
	         {
	        	 username = ""; // default if an exception occurs
	         } // end catch 
	         
      } // end method onTextChanged

		@Override
		public void afterTextChanged(Editable arg0) 
		{}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3)
		{}
	 };

}