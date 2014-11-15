package com.studynetwork;

import com.example.ucmstudynetwork.R;
import com.studynetwork.tasks.LoginTask;

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

public class MainActivity extends Activity implements OnClickListener{
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private String username;
	private String password;

	private EditText editTextUsername;
	private EditText editTextPassword;	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState); // call superclass's version
	      setContentView(R.layout.login_page); // inflate the GUI	   
	      
	      if ( savedInstanceState == null ){
	         username = "";
	         password = "";
	      } 
	      else{
	    	  username = savedInstanceState.getString(USERNAME);	      
	    	  password = savedInstanceState.getString(PASSWORD);	
	      }
	      
	      editTextUsername = (EditText)findViewById(R.id.editTextUsername);
	      editTextUsername.addTextChangedListener(usernameWatcher);
	      
	      editTextPassword = (EditText)findViewById(R.id.editTextPassword);
	      editTextPassword.addTextChangedListener(passwordWatcher);
	      
	      Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
	      //buttonLogin.setOnClickListener(buttonLoginListener);
	      buttonLogin.setOnClickListener(this);
	      
	      Button buttonfrgtPwd = (Button) findViewById(R.id.buttonfrgtpwd);
	      //buttonfrgtPwd.setOnClickListener(buttonfrgtpwdListener);	      
	      buttonfrgtPwd.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.buttonLogin:
				clickLoginButton();
				break;
			
			case R.id.buttonfrgtpwd:
				clickFfgtpwdButton();
				break;
		} 		
	}
	
	private void clickLoginButton(){
		if (username.toString().trim().length()!=0){
			if(password.toString().trim().length()!=0){
				//new LoginTask(this, new TaskCompleteListener()).execute(username, password);
				Intent intent = new Intent(this, Allgroups.class); 					
				startActivity(intent);
			}
			else{
				showAlertDialog(R.string.errorTitlepwd, R.string.errorMessagepwd, R.string.errorButton);
			}
		}
		else{
			showAlertDialog(R.string.errorTitleUname, R.string.errorMessageUname, R.string.errorButton);
		}
				
	} 
	
	private void clickFfgtpwdButton(){
		Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
        startActivity(intent);
	}
	private void showAlertDialog(int title, int message, int button){
	      AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	      
	      builder.setTitle(title); 
	      builder.setMessage(message);
	      builder.setPositiveButton(button, null); 
	      builder.show(); // display the Dialog
	}
		

	@Override
	protected void onSaveInstanceState(Bundle outState){
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
	 
	 private class TaskCompleteListener implements AsyncTaskCompleteListener<String>{
	 
        
		 
		@Override
        public void onTaskComplete(String result){
        	editTextUsername.setText(result);
        }
     }
	 	 	
	 
}