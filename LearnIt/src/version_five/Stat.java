package version_five;

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
	public Stat(double value) {
		time = System.currentTimeMillis();
		this.value = value;
	}
	public Stat(long time, double value) {
		this.time = time;
		this.value = value;
	}
	public long getTime() {
		return time;
	}
	public double getValue() {
		return value;
	}
}
