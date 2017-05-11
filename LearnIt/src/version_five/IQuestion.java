package version_five;

import java.util.LinkedList;

/**
 * 
 * @author JamesBeetham
 *
 */
public interface IQuestion {

	public LinkedList<Stat> getStats();
	/**
	 * Uses Engine e to ask a question, get an answer, and record the statistic.
	 * 
	 * @param e
	 *            engine to use
	 */
	public void ask(IEngine e);
	/**
	 * Generates list of keywords from the class name, content, and other
	 * things.
	 * 
	 * @return array of strings of keywords that should link back to this
	 *         question.
	 */
	public String[] getKeywords();
	/**
	 * Returns a string representation of all information stored in this
	 * question. This is the string to be used in a question constructor that
	 * will generate this same question using the parser in the constructor of
	 * this class.
	 * 
	 * @return String representation of this
	 */
	public String getString();

	public static String[] trimSplit(String str, String regex) {
		String[] arr = str.split(regex);
		for (int i = 0; i < arr.length; i++)
			arr[i] = arr[i].trim();
		return arr;
	}
}
