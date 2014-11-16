package com.studynetwork.entities;

import java.util.ArrayList;
import java.util.List;

public class Question {
	
	private int id;
	private String question;
	private int type;
	private int quizId;
	
	private List<QuestionChoice> choices = new ArrayList();	
	private QuestionChoice answerChoice;	
	private String  answerText;
	
	public Question(int id, String question, int type, int quizId){
		this.id = id;
		this.question = question;
		this.type = type;
		this.quizId = quizId;
	}
	
	public String getQuestion(){
		return this.question;
	}
	
	public int getType(){
		return this.type;
	}
	
	public void addChoice(int choiceId, String text, boolean correct){
		choices.add(new QuestionChoice(choiceId, text, correct));
	}
	
	public void setAnswerChoice(QuestionChoice answerChoice){
		this.answerChoice = answerChoice;
	}
	public QuestionChoice getAnswerChoice(){
		return this.answerChoice;
	}
		
	public void setAnswerText(String answerText){
		this.answerText = answerText;
	}
	public String getAnswerText(){
		return this.answerText;
	}
	
	public String getInsertQuery(){
		String query = "INSERT INTO take_quiz_answer(obs, is_correct, answer_id, question_id, take_quiz_id) " +
					   "VALUES (" + this.answerText  + "," + answerChoice.isCorrect() + "," + this.answerChoice.getId() + "," +
					   			 this.id + ",? );";		
		return query;
	}
		
}
