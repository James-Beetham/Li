package version_6;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Object storing all of the notes for the program. TODO get and set functions
 * 
 * @author James-Beetham
 */
public class Notes {
	public static void main(String[] args) {
		
	}

	/**
	 * ArrayList of all the different groups of notes (subjects).
	 */
	private ArrayList<TreeMap<Double, Note>> notes;

	/**
	 * Names of the subjects such that indexes match with the TreeMaps in the notes
	 * ArrayList. TODO not very elegant
	 */
	private ArrayList<String> subjects;

	/**
	 * Constructs a new empty set of notes.
	 */
	public Notes() {
		notes = new ArrayList<TreeMap<Double, Note>>();
		subjects = new ArrayList<String>();
	}

	/**
	 * Constructs a new set of notes from parameter. Notes are constructed
	 * 
	 * @param data
	 *            string with the information to build the notes from
	 */
	public Notes(String data) {
		notes = new ArrayList<TreeMap<Double, Note>>();
		subjects = new ArrayList<String>();
	}

	/**
	 * Add new empty subject.
	 * 
	 * @param subject
	 *            name of the subject
	 * @return true if successfully added, false if the subject name is taken
	 */
	public boolean addSubject(String subject) {
		if (subjects.contains(subject))
			return false;
		subjects.add(subject);
		notes.add(new TreeMap<Double, Note>());
		return true;
	}

	/**
	 * Stores the note information.
	 * 
	 * @author James-Beetham
	 *
	 */
	private class Note {
		private String source;
		private ArrayList<Record> records;

		public Note(String src) {
			source = src;
			records = new ArrayList<Record>();
		}
	}

	/**
	 * Records a specific time and other information.
	 * 
	 * @author James-Beetham
	 *
	 */
	private class Record {
		private String info;

		public Record() {
			info = "" + System.currentTimeMillis();
		}

		/**
		 * Creates a new record storing String info. Parameter must begin with a time
		 * stamp (System.currentTimeMillis()).
		 * 
		 * @param info
		 *            information to store
		 */
		public Record(String info) {
			this.info = info;
		}

		public long getTime() {
			Scanner scan = new Scanner(info);
			return scan.nextLong();
		}
	}
}
