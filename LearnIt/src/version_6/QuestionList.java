package version_6;

import java.util.ArrayList;

/**
 * Stores the list of selected questions and sorts them based on weights.
 * Weights update as questions are answered - questions are not removed from the
 * queue (they cycle).
 * 
 * TODO add support for threads (make the list a linked list), implement
 * toAddQueue thread, and a recursive weight updater thread - add weight checker
 * by creating a new chartree for keywords, finish constructor (weight the
 * questions as they come in)
 * 
 * @author James-Beetham
 *
 */
public class QuestionList {
	/**
	 * Generates a list of questions from a note's source text. Implement new
	 * question types here.
	 * 
	 * @param noteSource
	 *            String of the note's text
	 * @return list of questions
	 */
	public ArrayList<IQuestion> generateQuestions(String noteSource) {
		ArrayList<IQuestion> arr = new ArrayList<IQuestion>();
		arr.add(new BasicQuestion(noteSource));

		// Example: arr.add([construct your child of IQuestion here]);
		// TODO add more types of questions here.

		ArrayList<IQuestion> output = new ArrayList<IQuestion>();
		for (IQuestion q : arr) {
			if (q.isValid())
				output.add(q);
		}
		return output;
	}

	private ArrayList<IQuestion> questions;
	private FileManager fm;

	/**
	 * Constructs a new QuestionList using the paths specified. Each path points to
	 * a folder or file.
	 * 
	 * @param paths
	 *            list of paths of notes to add to the queue
	 */
	public QuestionList(FileManager fm, ArrayList<String> paths) {
		questions = new ArrayList<IQuestion>();
		this.fm = fm;

		for (String path : paths)
			for (String note : fm.getFile(path))
				questions.addAll(generateQuestions(note));
	}

	/**
	 * Take the next question from the queue. Must answer and return a value for
	 * this question to be "answered".
	 * 
	 * @return string containing the question to ask
	 */
	public String next() {
		return (questions.size() == 0) ? null : questions.get(0).toString();
	}

	/**
	 * Stores value to a record stored with the question, then moves the question to
	 * the end of the list to be re-weighted.
	 * 
	 * @param s
	 *            user's answer to question
	 */
	public void answer(String s) {
		questions.add(questions.get(0));
		questions.remove(0);
	}

}
