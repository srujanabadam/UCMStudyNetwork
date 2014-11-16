package com.studynetwork.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.studynetwork.util.DatabaseHelper;

public class Quiz {
	
	private int id;
	private String name;
	private Date date;
	private int createdBy;
	
	private int memberTakingId;
	private int grade;
	
	private List<Question> questions = new ArrayList<Question>();
		
	private DatabaseHelper dbHelper;
	
 	public Quiz(int id, String name, Date date, int createdBy){
		this.id = id;
		this.name = name;
		this.date = date;
		this.createdBy = createdBy;
	}
	
 	public String getName(){
 		return this.name;
 	}
 	
 	public Date getDate(){
 		return this.date;
 	}
 	
 	public List<Question> getQuestions(){
 		if (questions.size() == 0)
 			loadQuestions();	
 		return questions;
 	}
 	
 	private void loadQuestions(){
 		String query = "SELECT q.id, q.question, q.type, qc.id AS choiceId, qc.text, qc.is_correct " +
					   "FROM question q INNER JOIN question_choice qc ON q.id = qc.question_id " +
					   "WHERE quiz_id = " + this.id + " ORDER BY q.id ";		
		DatabaseHelper dh = new DatabaseHelper();
		try{
			int lastId = 0;
			dh.openConnection();
			ResultSet rs = dh.getQueryResultSet(query);			
			while (rs.next()){
				if (rs.getInt("id") != lastId){
					questions.add(new Question(rs.getInt("id"), rs.getString("question"), rs.getInt("type"), this.id));
					lastId = rs.getInt("id");
				}							
				questions.get(questions.size()-1).addChoice(rs.getInt("choiceId"), rs.getString("text"), rs.getBoolean("correct"));				
			}						
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
 	}
 	
 	public boolean takeQuiz(){
 		String query = "INSERT INTO quiz(date, grade, member_id, quiz_id) " + 
 					   "VALUES (?,?,?,?)";
 		 		
 		dbHelper = new DatabaseHelper();
		try {
			dbHelper.openConnection();
			dbHelper.executeUpdate(query, getInsertQueryParameters());			
			
			saveAnswers(getIdTakeQuiz());
			return true;
		} catch (SQLException e) {				
			e.printStackTrace();
			return false;
		}	 		 		
 	}
 	
 	private Object[] getInsertQueryParameters(){
		Object[] params = {this.date, this.grade, this.memberTakingId, this.id};
		return params;
	}
 	
 	private int getIdTakeQuiz(){
 		String query = "SELECT id FROM take_quiz WHERE member_id = " + this.memberTakingId + " AND quiz_id = " + this.id;
 		try {
			ResultSet rs = dbHelper.getQueryResultSet(query);
			if (rs.next())
				return rs.getInt("id");			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
 		return -1;
 	}
 	 	
 	private boolean saveAnswers(int takeQuizId){
 		if (takeQuizId > 0){
 			String query = "";
	 		for (int i = 0; i < questions.size(); i++){
	 			query += questions.get(i).getInsertQuery(); 
	 		} 			
	 		query = query.replace("?", "" + takeQuizId );	 		
 			try {				
	 			dbHelper.executeBatch(query);
	 			dbHelper.CloseConnection();
				return true;
			} catch (SQLException e) {				
				e.printStackTrace();
				return false;
			}	 		 		
 		}
 		else
 			return false;
 	}
 	
 	
 	
}
