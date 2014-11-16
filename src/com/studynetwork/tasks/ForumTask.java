package com.studynetwork.tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Forum;
import com.studynetwork.entities.Group;
import com.studynetwork.entities.User;
import com.studynetwork.util.DatabaseHelper;

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
				return getForums(group.getId());			
		}
			
		if (option.equalsIgnoreCase("view"))
			return getForums(group.getId());
		
		return null;
	}
	
	private List<Forum> getForums(int groupId){
		List<Forum> forums = new ArrayList<Forum>();
		
		String query = "SELECT id, title, description, date, moderator_id " +
					  "FROM forum WHERE group_id = " + groupId + " GROUP BY date DESC" ;		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);
			while (rs.next()){
				forums.add(new Forum(rs.getInt("id"), rs.getString("title"), rs.getString("description"), 
									 rs.getDate("date"), groupId, rs.getInt("moderator_id")));
			}			
			return forums;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	protected void onPostExecute(List<Forum> forums) {
		 listener.onTaskComplete(forums);         
    }
		
}
