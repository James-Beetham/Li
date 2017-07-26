package version_5;

/**
 * Stores a time and a value for a question. As questions are asked they
 * generate stats. Eg: A question is asked, the user gets it wrong, the stat is
 * stored as 0 for value (incorrect). If they get it right, is stored as 1. If
 * there is a question where partial correctness can be given, value should be
 * some decimal between 0 and 1 (or not if you have some other implementation in
 * Analyzer).
 * 
 * @author JamesBeetham
 *
 */
public class Stat {
	private long time;
	private double value;

	/**
	 * Construct new statistic from given value. Will set the time to the time
	 * returned from System.currentTimeMillis().
	 * 
	 * @param value
	 *            a double value to set for correctness (recommended to be
	 *            between 0 and 1: 0 is incorrect and 1 is correct)
	 */
	public Stat(double value) {
		time = System.currentTimeMillis();
		this.value = value;
	}

	/**
	 * Constructs a new statistic from given time and value.
	 * 
	 * @param time
	 *            time at which this statistic was recorded as given by
	 *            System.currentTimeMillis()
	 * @param value
	 *            how correct the user was: 0 is incorrect 1 is correct, but can
	 *            be anywhere between or above
	 */
	public Stat(long time, double value) {
		this.time = time;
		this.value = value;
	}

	/**
	 * Time this was recorded.
	 * 
	 * @return time this statistic was recorded
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Value of correctness of this statistic
	 * 
	 * @return correctness (usually between 0 and 1 where 0 is wrong and 1 is
	 *         right
	 */
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return time + "," + value;
	}
}
