package com.studynetwork.tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Group;
import com.studynetwork.entities.Quiz;
import com.studynetwork.util.DatabaseHelper;

public class QuizTask extends AsyncTask<Object,Void, List<Quiz>> {

	private AsyncTaskCompleteListener<List<Quiz>> listener;
	
	@Override
	protected List<Quiz> doInBackground(Object... params) {
		String option = (String)params[0];
		
		if (option.equals("list")){		
			Group group = (Group)params[1];
			return getQuizzes(group.getId());
		}
		if (option.equals("take")){
			Quiz q = (Quiz)params[1];
			q.getQuestions();
			List<Quiz> quiz = new ArrayList<Quiz>();
			quiz.add(q);
			return quiz;
		}
		if (option.equals("save")){
			Quiz q = (Quiz)params[1];
			if (q.takeQuiz()){
				List<Quiz> quiz = new ArrayList<Quiz>();
				quiz.add(q);
				return quiz;
			}
				
		}
		return null;	
	}
	
	private List<Quiz> getQuizzes(int groupId){
		List<Quiz> quizzes = new ArrayList<Quiz>();
		
		String query = "SELECT q.id, q.name, q.date, q.created_by_id " +
					   "FROM quiz q INNER JOIN member m ON q.ceated_by_id = m.id " +
					   "WHERE m.group_id = " + groupId + " ORDER BY q.date DESC ";		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);
			while (rs.next()){
				quizzes.add(new Quiz(rs.getInt("id"), rs.getString("name"), rs.getDate("date"), 
									 rs.getInt("created_by_id")));
			}			
			return quizzes;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
		
	protected void onPostExecute(List<Quiz> quizzes) {
		 listener.onTaskComplete(quizzes);         
    }
	
}
