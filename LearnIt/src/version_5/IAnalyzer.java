package version_5;

/**
 * Interface for what Analyzers need (minimum).
 * 
 * @author JamesBeetham
 *
 */
public interface IAnalyzer {
	/**
	 * Returns the time the question should next be asked (such that when
	 * System.currentTimeMillis() == the value returned by this method, that is
	 * when the predicted probability for remembering will drop below specified
	 * percent.
	 * 
	 * @param q
	 *            question to predict from
	 * @return a time when this question should be asked again
	 */
	public long nextTime(IQuestion q);
}
