package sks.controller;

import java.util.List;

import sks.data.DatabaseController;
import sks.to.Word;

public class DataProcessor {

	List<Word> wordList = null;
	int currentIndex = 0;
	int score = 0;

	public void getQuestions(int inputLevel) {
		// Fetch records from DB based on user level preference
		DatabaseController data = new DatabaseController();
		wordList = data.getQuestions(inputLevel, 5);
	}
	
	public Word getNextQuestion() {	
		
		if(currentIndex >= wordList.size())
		{
			return null;
		}
		
		return wordList.get(currentIndex++);		
	}
	
	public void setUserAnswer(String answer) {
		
		if(wordList.get(currentIndex -1).getAnswerWord().equalsIgnoreCase(answer)) {
			wordList.get(currentIndex -1).setAnswerCorrect(true);
			score += 5;}
		}
/*		else if()
		{
			wordList.get(currentIndex -1).getAnswerWord();

		}
		
	}*/
	
	public int getUserScore() {
		return score;
	}
	
	public int getQuestionIndex() {
		return (this.currentIndex);
	}
	
	public int getTotalNumberOfQuestions() {
		return wordList.size();
	}

}
