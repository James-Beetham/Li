package version_1;

/**
 * Learns how long people remember.
 * 
 * @author JamesBeetham
 */
public class Analyzer {
	public static final int STAT_VARIABLES = 3;

	private LiPoint[] stats;
	private double[] variableRanges;

	/**
	 * Constructs a new analyzer and analyzes based on stats given.
	 * 
	 * @param statsArr
	 *            2d array: first element of each column is 1 true, 0 false, -1
	 *            for doesn't exist
	 */
	public Analyzer(double[][] statsArr, double[] variableRanges) {
		if (statsArr == null)
			throw new NullPointerException("statsArr was null.");
		if (statsArr.length == 0)
			throw new IllegalArgumentException("statsArr was empty");
		if (statsArr[0].length - 1 != STAT_VARIABLES)
			throw new IllegalArgumentException(
					"stats in statsArr was incorrect length. Number of variables should be " + STAT_VARIABLES + ", but was " + statsArr[0].length);
		if (variableRanges.length != STAT_VARIABLES)
			throw new IllegalArgumentException(
					"variableRanges array length was incorrect. Should've been " + STAT_VARIABLES + ", but was " + variableRanges.length);
		this.variableRanges = variableRanges.clone();
		stats = new LiPoint[statsArr.length];
		for (int i = 0; i < statsArr.length; i++) {
			double[] vars = new double[statsArr[0].length - 1];
			for (int j = 1; j < statsArr[0].length; j++)
				vars[j - 1] = statsArr[i][j];
			stats[i] = new LiPoint(vars, statsArr[i][0] == 1);
		}
		// TODO
	}

	/**
	 * Finds the next time for a question.
	 * 
	 * @return time the question should next be asked
	 */
	public int getNextTime(double percent) {
		int time = 0;
		while (Math.abs(getPercent(time, new double[]{10}) - percent) > .05)
			time++;
		return time;
	}

	/**
	 * Finds the percent for the given time using the gradient
	 * @param time
	 * @param coordinates
	 * @return
	 */
	private double getPercent(int time, double[] coordinates) {
		// TODO
		return -1;
	}

	/**
	 * Finds the average percent correct given the coordinates at point p.
	 * Negative elements are ignored.
	 * 
	 * @param p
	 *            point
	 * @return
	 */
	private double getAverage(LiPoint p) {

		return 1;
	}

	/**
	 * Stores stats
	 * 
	 * @author JamesBeetham
	 */
	private class LiPoint {
		public double[] coords;
		public boolean correct;
		public LiPoint(double[] coords, boolean correct) {
			if (coords.length != STAT_VARIABLES)
				throw new IllegalArgumentException("coords array length must be " + STAT_VARIABLES + ", but was " + coords.length);
			this.coords = coords.clone();
			this.correct = correct;
		}

		public LiPoint clone() {
			return new LiPoint(coords, correct);
		}
	}

	/**
	 * Stores and calculates the gradient for the stats.
	 * 
	 * @author JamesBeetham
	 */
	private class Gradient {
		private double[][] weights;
		private LiPoint[] points;
		public Gradient(int resolution, LiPoint[] points, int[] variableRanges) {
			weights = new double[points[0].coords.length][resolution];
			for (int j = 0; j < points[0].coords.length; j++) {
				for (int i = 0; i < weights.length; i++) {

				}
			}
			this.points = points;
		}
		/**
		 * Calculates or loads the gradient.
		 */
		public void load() {
			// TODO
		}
		/**
		 * Finds the predicted chance of success based on weights of the
		 * gradient.
		 * 
		 * @return chance of success
		 */
		public double predictPercent() {

			return -1;
		}
		/**
		 * Finds the accuracy for the current weights.
		 * 
		 * @return standard deviation
		 */
		private double getAccuracy() {
			// TODO
			return -1;
		}

	}

}
