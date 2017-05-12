package version_five;

/**
 * Interface for the minimum things an engine needs.
 * 
 * @author JamesBeetham
 *
 */
public interface IEngine {

	/**
	 * Display the specified string.
	 * 
	 * @param s
	 *            String to display
	 */
	public void print(String s);

	/**
	 * Get the users input.
	 * 
	 * @return String of what the user input
	 */
	public String get();

	/**
	 * Get the question generated from specified String. Note this uses TestType
	 * to generate the actual question, but new Question Types need to be
	 * constructed and added here.
	 * 
	 * @param s
	 *            String of data the question will be interpreted from
	 * @return Question generated from the data
	 */
	public IQuestion getQuestion(String s);
}
