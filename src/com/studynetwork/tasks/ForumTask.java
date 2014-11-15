package com.studynetwork.tasks;

import java.util.List;

import android.os.AsyncTask;

public class ForumTask extends AsyncTask<Object,Void, List<Object>> {

	@Override
	protected List<Object> doInBackground(Object... params) {
		String option = (String)params[0];
		
		if (option.equals("create")){
			
		}
		if (option.equals("view")){
			
		}
		if (option.equals("comment")){
			
		}
		
		return null;
	}
	
	
}
