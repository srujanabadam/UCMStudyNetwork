package com.studynetwork.tasks;

import java.util.List;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Comment;
import com.studynetwork.entities.Forum;

import android.content.Context;
import android.os.AsyncTask;

public class ForumCommentsTask extends AsyncTask<Object,Void, List<Comment>> {

	private AsyncTaskCompleteListener<List<Comment>> listener;
	
	public ForumCommentsTask (Context context, AsyncTaskCompleteListener<List<Comment>> listener){
		//this.context = context;
		this.listener = listener;		
	}
	
	@Override
	protected List<Comment> doInBackground(Object... params) {
		String option = (String)params[0];
		Forum forum = (Forum)params[1];
		
		if (option.equals("view"))					
			return forum.getComments();	
		
		if (option.equals("comment")){						
			if (forum.addComment(0, ""))
				return forum.getComments();
		}
		
		return null;
	}
	
	protected void onPostExecute(List<Comment> comments) {
		 listener.onTaskComplete(comments);         
    }
	
}
