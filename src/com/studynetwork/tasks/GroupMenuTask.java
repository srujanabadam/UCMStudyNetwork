package com.studynetwork.tasks;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Group;
import com.studynetwork.entities.User;

public class GroupMenuTask extends AsyncTask<Object,Void, List<Object>> {
	
	private Context context;
    private AsyncTaskCompleteListener<List<Object>> listener;
	
	public GroupMenuTask(Context context, AsyncTaskCompleteListener<List<Object>> listener){
		this.context = context;
		this.listener = listener;
	}
	
	@Override
	protected List<Object> doInBackground(Object... params) {
		String menu = (String)params[0];
		Group group = (Group)params[1];
		
		if (menu.equalsIgnoreCase("forum")){
			group.getForums();
		}
				
		return null;
	}
	
	 protected void onPostExecute(List<Object> objects) {
		 listener.onTaskComplete(objects);         
     }
		
}
