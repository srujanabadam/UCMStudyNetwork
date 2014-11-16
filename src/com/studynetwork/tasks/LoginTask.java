package com.studynetwork.tasks;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.User;
import com.studynetwork.util.LoginManager;

import android.content.Context;
import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String,Void,User> {

	private Context context;
    private AsyncTaskCompleteListener<User> listener;
	
	public LoginTask(Context context, AsyncTaskCompleteListener<User> listener){
		this.context = context;
		this.listener = listener;
	}
	
	@Override
	protected User doInBackground(String... params) {
		
		LoginManager login = new LoginManager();		
		if (login.authenticate(params[0], params[1])){
			System.out.print("LOGGED IN!!!!!!!!!!!");
			return login.getUser();
		}
		else{
			System.out.print("NONONONO... LOGGED IN!!!!!!!!!!!");
			return login.getUser();
		}					
		
	}
		
	protected void onPostExecute(User user) {
		 listener.onTaskComplete(user);         
     }
	
}
