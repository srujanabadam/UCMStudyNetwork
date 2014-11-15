package com.studynetwork.tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Group;
import com.studynetwork.entities.User;
import com.studynetwork.util.DatabaseHelper;

public class ManageGroupTask extends AsyncTask<User,Void, List<Group>> {
	
	private Context context;
	private AsyncTaskCompleteListener<List<Group>> listener;
	
	public ManageGroupTask(Context context, AsyncTaskCompleteListener<List<Group>> listener){
		this.context = context;
		this.listener = listener;
	}

	@Override
	protected List<Group> doInBackground(User... user) {
		return getGroups(user[0].getId());
	}
	
	protected void onPostExecute(List<Group> groups) {
		 listener.onTaskComplete(groups);         
    }
	
	/**
	 * Returns the list of groups for the user
	 * */
	private List<Group> getGroups(int userId){
		String query = "SELECT grou.id, name, description, date, status, type, coalesce(section_id,0) AS secid, member.id, is_admin " +
					   "FROM \"group\" INNER JOIN member ON group.id = member.group_id " +
					   "WHERE user_id = " + userId; 
		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			return createGroupList(dh.getQueryResultSet(query));
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	private List<Group> createGroupList(ResultSet rs) throws SQLException{
		List<Group> groups = new ArrayList<Group>();
		 
		while (rs.next()){
			
			Group g = new Group(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDate("date"),
								rs.getInt("status"), rs.getInt("type"),  rs.getInt("secid"));
			groups.add(g);
		}	
		
		return groups;
	}
}
