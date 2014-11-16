package com.studynetwork.tasks;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.studynetwork.AsyncTaskCompleteListener;
import com.studynetwork.entities.Group;
import com.studynetwork.entities.Quiz;

public class QuizTask extends AsyncTask<Object,Void, List<Quiz>> {

	private AsyncTaskCompleteListener<List<Quiz>> listener;
	
	@Override
	protected List<Quiz> doInBackground(Object... params) {
		String option = (String)params[0];
		
		if (option.equals("list")){		
			Group group = (Group)params[1];
			return group.getQuizzes();
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
	
	protected void onPostExecute(List<Quiz> quizzes) {
		 listener.onTaskComplete(quizzes);         
    }
	
}
