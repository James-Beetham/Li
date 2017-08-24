package version_6;

/**
 * A question is able to be asked and has a record of all responses. Recommended
 * that other question classes extend BasicQuestion
 * 
 * @author James-Beetham
 */
public interface IQuestion {
	/**
	 * Check if this question is valid.
	 * 
	 * @return false if it is invalid.
	 */
	public boolean isValid();

	/**
	 * Get the question to be asked.
	 * 
	 * @return text with inline commands to be parsed by the GUI
	 */
	public String ask();

	/**
	 * Adds a new record to this question.
	 * 
	 * @param input
	 *            what the user answered
	 * @return how correct they were
	 */
	public double addRecord(String input);

}
