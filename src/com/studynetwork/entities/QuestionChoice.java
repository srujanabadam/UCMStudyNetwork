package com.studynetwork.entities;

public class QuestionChoice {
	
	private int id;
	private String text;
	private boolean correct;
	
	
	public QuestionChoice(int id, String text, boolean correct){
		this.id = id;
		this.text = text;
		this.correct = correct;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getText(){
		return this.text;
	}
	
	public Boolean isCorrect(){
		return this.correct;
	}
	
}
