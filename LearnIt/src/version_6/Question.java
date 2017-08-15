package version_6;

import java.util.ArrayList;

/**
 * TODO make it easy to add different types of questions (to discuss).
 * 
 * Stores a question and all the records for the question. Also stores the hash
 * of Note this Question was generated from.
 * 
 * @author James-Beetham
 *
 */
public class Question {
	protected String noteHash;
	protected ArrayList<Record> records;

	// TODO constructor from note that constructs multiple questions for the one
	// note (static function that returns a list of questions?)

	/**
	 * Constructs a new question from given data.
	 * 
	 * @param data
	 */
	public Question(String data) {
		// TODO parse String returned by toString()
	}

	/**
	 * Adds a new record to this question.
	 * 
	 * @param input
	 *            what the user answered
	 * @return how correct they were
	 */
	public double addRecord(String input) {
		records.add(new Record(input));
		return getCorrectness(input);
	}

	protected double getCorrectness(String input) {
		// TODO determine how correct the input is.
		return -1;
	}

	@Override
	public String toString() {
		return null;
	}

	/**
	 * Stores records of past responses to question. Includes a time stamp and user
	 * input.
	 * 
	 * @author James-Beetham
	 *
	 */
	protected class Record {
		private String input;
		private long timeStamp;

		public Record(String input) {
			this.input = input;
			timeStamp = System.currentTimeMillis();
		}

		public Record(String input, long timeStamp) {
			this.input = input;
			this.timeStamp = timeStamp;
		}

		public String getInput() {
			return input;
		}

		public long getTimeStamp() {
			return timeStamp;
		}
	}
}
