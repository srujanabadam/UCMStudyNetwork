package com.studynetwork.tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Announcement;
import com.studynetwork.entities.Group;
import com.studynetwork.util.DatabaseHelper;

public class AnnouncementTask extends AsyncTask<Object,Void, List<Announcement>> {

	private AsyncTaskCompleteListener<List<Announcement>> listener;
	
	@Override
	protected List<Announcement> doInBackground(Object... params) {
		String option = (String)params[0];
		
		if (option.equals("list")){
			return getAnnouncements((Group)params[1]);
		}
		if (option.equals("create")){
			Announcement ann = (Announcement)params[1];
			if(ann.save())
				return getAnnouncements((Group)params[1]);			
		}
		if (option.equals("readed")){
			Announcement ann = (Announcement)params[1];
			if (ann.setReaded((Integer)params[2])){
				List<Announcement> annL = new ArrayList<Announcement>();
				annL.add(ann);
				return annL;
			}			
		}
		
		return null;
	}
				
	private List<Announcement> getAnnouncements(Group group){
		List<Announcement> announcements = new ArrayList<Announcement>();
		
		String query = "SELECT a.id, a.title, a.content, a.date, a.posted_by_id, coalesce(ra.readed,False) AS readed " +
					   "FROM announcement a INNER JOIN member m ON a.posted_by_id = m.id " +
					   	     "LEFT JOIN " + 
					   	     "(SELECT readed, announcement_id AS annId FROM read_announcement WHERE member_id = " + group.getActiveMember().getId()  + ") AS ra " + 
					   	     "ON a.id = ra.annId " +
					   "WHERE m.group_id = " + group.getId() + " ORDER BY a.date DESC ";
		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);
			while (rs.next()){
				announcements.add(new Announcement(rs.getInt("id"), rs.getString("title"), rs.getString("content"), 
								  rs.getTimestamp("date"), rs.getInt("posted_by_id"), rs.getBoolean("readed")));
			}			
			dh.CloseConnection();
			return announcements;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}						
	}
	
	protected void onPostExecute(List<Announcement> announcements) {
		 listener.onTaskComplete(announcements);         
   }
	
}
