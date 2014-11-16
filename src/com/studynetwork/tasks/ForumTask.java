package com.studynetwork.tasks;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Forum;
import com.studynetwork.entities.Group;
import com.studynetwork.entities.User;

public class ForumTask extends AsyncTask<Object,Void, List<Forum>> {
	
	private Context context;
    private AsyncTaskCompleteListener<List<Forum>> listener;
	
	public ForumTask(Context context, AsyncTaskCompleteListener<List<Forum>> listener){
		this.context = context;
		this.listener = listener;		
	}
	
	@Override
	protected List<Forum> doInBackground(Object... params) {
		String option = (String)params[0];
		Group group = (Group)params[1];		
	
		if (option.equalsIgnoreCase("create")){
			Forum forum = (Forum)params[2];
			if (forum.save())
				return group.getForums();			
		}
			
		if (option.equalsIgnoreCase("view"))
			return group.getForums();
		
		return null;
	}
	
	protected void onPostExecute(List<Forum> forums) {
		 listener.onTaskComplete(forums);         
    }
		
}
