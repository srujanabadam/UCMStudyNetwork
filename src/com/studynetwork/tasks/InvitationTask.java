package com.studynetwork.tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Group;
import com.studynetwork.entities.Invitation;
import com.studynetwork.entities.User;
import com.studynetwork.util.DatabaseHelper;

public class InvitationTask extends AsyncTask<Object,Void, List<Invitation>> {

	private AsyncTaskCompleteListener<List<Invitation>> listener;
	
	@Override
	protected List<Invitation> doInBackground(Object... params) {
		String option = (String)params[0];
		
		if(option.equals("list")){
			User user = (User)params[1];
			return getPendingInvitations(user.getId());
		}
		if (option.equals("changeStatus")){
			User user = (User)params[1];
			Invitation invitation = (Invitation)params[2];			
			if (invitation.changeStatus((Integer)params[3]))
				return getPendingInvitations(user.getId());							
		}		
		if(option.equals("sendInvitation")){
			Group group = (Group)params[1];			
			group.sendInvitations((int[])params[2]);
		}
		if(option.equals("sendInvitationView")){
			Group group = (Group)params[1];			
			group.sendInvitations((int[])params[2]);
		}
				
		return null;
	}
	
	private List<Invitation> getPendingInvitations(int userId){
		List<Invitation> invitations = new ArrayList<Invitation>();
		
		String query = "SELECT inv.id, inv.date, inv.group_id, inv.sender_id, m.user_id, u.first_name, u.last_name, " + 
							  "grp.name, grp.description " +
					   "FROM invitation inv INNER JOIN member m ON inv.sender_id = m.id " + 
					   		 "INNER JOIN auth_user u ON m.user_id = u.id " +	
					   		 "INNER JOIN \"group\" grp ON inv.group_id = grp.id " +
					   "WHERE inv.to_id = " + userId + " AND inv.status = 1 ORDER BY inv.date DESC ";
		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);
			while (rs.next()){				
				invitations.add(new Invitation(rs.getInt("id"), rs.getTimestamp("date"), 1, rs.getInt("group_id"), rs.getString("name"), 
								  rs.getInt("sender_id"), rs.getString("first_name") +" " + rs.getString("last_name"), userId));
			}			
			dh.CloseConnection();
			return invitations;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	protected void onPostExecute(List<Invitation> invitations) {
		 listener.onTaskComplete(invitations);         
   }
	
}
