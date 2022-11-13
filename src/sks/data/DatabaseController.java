
package sks.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sks.to.Word;

public class DatabaseController {

	// DB Connection URL
	private String urlStr = "jdbc:mysql://localhost:3306/word_game";

	// DB User Name
	private String userName = "root";



	public List<Word> getQuestions() throws Exception {		
		return this.getQuestions(1, 10);		
	}
	
	
	/**
	 * A method to get the total count of records in the word table
	 * 
	 * 
	 * 
	 * 
	 * @return record comments
	 * @throws Exception
	 */
	public List<Word> getQuestions(int level, int maxSize) {
		List<Word> wordList = new ArrayList<Word>();
		
		try (Connection connection = DriverManager.getConnection(urlStr, userName, userName)) {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM wordData WHERE levels = " + level + " ORDER BY RAND() LIMIT " + maxSize + " ;";
			ResultSet results = statement.executeQuery(query);

			while (results.next()) {

				int id = results.getInt("wordId");
				int lev = results.getInt("levels");
				String answer = results.getString("answer");
				String scrambled = results.getString("scrambled");

				Word word = new Word(id, lev, answer, scrambled);
				wordList.add(word);

			}

		} catch (Exception someException) {
			someException.printStackTrace();
		}

		return wordList;
	}



}
