package com.studynetwork.tasks;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.util.LoginManager;

import android.content.Context;
import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String,Void,Boolean> {

	private Context context;
    private AsyncTaskCompleteListener<String> listener;
	
	public LoginTask(Context context, AsyncTaskCompleteListener<String> listener){
		this.context = context;
		this.listener = listener;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		
		LoginManager login = new LoginManager();		
		if (login.authenticate(params[0], params[1])){
			System.out.print("LOGGED IN!!!!!!!!!!!");
			return true;
		}
		else{
			System.out.print("NONONONO... LOGGED IN!!!!!!!!!!!");
			return false;
		}					
		
	}
		
	 protected void onPostExecute(Boolean result) {
		 listener.onTaskComplete(""+result);         
     }
	
}
