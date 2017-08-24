package version_6;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author James-Beetham
 *
 */
public class BasicQuestion implements IQuestion {
	protected String noteHash;
	protected String question;
	protected ArrayList<Record> records;

	/**
	 * Constructs a new question from given data. isValid method should return false
	 * if the data entered is not valid.
	 * 
	 * @param data
	 *            source text from the note
	 */
	public BasicQuestion(String data) {
		if (data == null) {
			noteHash = null;
			records = null;
			question = null;
		}
		noteHash = Note.genHash(data);
		records = new ArrayList<Record>();
		question = data;
		// TODO parse String if it's input text versus a note's text?
	}

	public boolean isValid() {
		return noteHash == null || records == null || question == null;
	}

	public String ask() {
		return question;
	}

	public double addRecord(String input) {
		records.add(new Record(input));
		return getCorrectness(input);
	}

	/**
	 * Determines how correct the input is.
	 * 
	 * @param input
	 *            String the user entered
	 * @return number between -1 and 1 where 1 is correct and -1 is incorrect
	 */
	protected double getCorrectness(String input) {
		return -1;
	}

	@Override
	public String toString() {
		// TODO convert to string for storage
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
