package sks.to;

public class Word {

	// Class attributes
	int id;
	String answerWord;
	String scrambledWord;
	int level;
	
	boolean isShowed = false;  // was the question showed to users
	boolean isAnswerCorrect = false; // Did user answer correctly

	// Default constructors
	public Word() {
	}

	// Constructor with arguments
	public Word(int id, int level, String answerWord, String scrambledWord) {
		super();
		this.id = id;
		this.level = level;
		this.answerWord = answerWord;
		this.scrambledWord = scrambledWord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswerWord() {
		return answerWord;
	}

	public void setAnswerWord(String answerWord) {
		this.answerWord = answerWord;
	}

	public String getScrambledWord() {
		return scrambledWord;
	}

	public void setScrambledWord(String scrambledWord) {
		this.scrambledWord = scrambledWord;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isShowed() {
		return isShowed;
	}

	public void setShowed(boolean isShowed) {
		this.isShowed = isShowed;
	}

	public boolean isAnswerCorrect() {
		return isAnswerCorrect;
	}

	public void setAnswerCorrect(boolean isAnswerCorrect) {
		this.isAnswerCorrect = isAnswerCorrect;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", answerWord=" + answerWord + ", scrambledWord=" + scrambledWord + ", level=" + level
				+ ", isShowed=" + isShowed + ", isAnswerCorrect=" + isAnswerCorrect + "]";
	}



}
