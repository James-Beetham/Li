package version_five;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Analyzes stats from a bunch of questions, predicting when the question needs
 * to be asked again based on past performance and the equation: r = e^(-t / s)
 * where r is the percentage the question will be answered correctly, t is time,
 * and s is the weight. A best fit line is generated by changing s and checking
 * the standard deviation from all statistics from questions that have been
 * exposed "x" number of times.
 * 
 * @author JamesBeetham
 *
 */
public class DefaultAnalyzer implements IAnalyzer {

	private LinkedList<IQuestion> questions;
	private ArrayList<Double> calculatedWeights;
	private double percent;
	private static final double MINIMUM_STEP = .005;

	public DefaultAnalyzer(double percent) {
		setup(percent, new LinkedList<IQuestion>());
	}

	public DefaultAnalyzer(double percent, LinkedList<IQuestion> questions) {
		setup(percent, questions);
	}

	private void setup(double percent, LinkedList<IQuestion> questions) {
		if (percent > 1 || percent < 0)
			throw new IllegalArgumentException("Percent needs to be between 0 and 1, but was: " + percent);

		this.percent = percent;
		this.questions = questions;
		calculatedWeights = new ArrayList<Double>();
		calculatedWeights.add(new Double(1));
		updateWeights();
	}

	@Override
	public long nextTime(IQuestion q) {
		int exposureNumber = q.getStats().size();
		if (exposureNumber <= 0) {
			return 0;
		}
		updateWeight(exposureNumber);
		long oldTime = q.getStats().get(exposureNumber - 1).getTime();
		return (long) (-1 * calculatedWeights.get(exposureNumber).doubleValue() * Math.log(percent) + oldTime);
	}

	public void updateWeights() {
		for (int exposureNumber = 0; numExposureNumber(exposureNumber) != 0; exposureNumber++) {
			updateWeight(exposureNumber);
		}
	}

	private void updateWeight(int exposureNumber) {
		double prevNumber = 1;

		if (numExposureNumber(exposureNumber) < 5 && exposureNumber != 1) {
			prevNumber = calculatedWeights.get(calculatedWeights.size() - 1).doubleValue();
			while (calculatedWeights.size() <= exposureNumber)
				calculatedWeights.add(new Double(prevNumber));
			return;
		}
		while (calculatedWeights.size() <= exposureNumber)
			calculatedWeights.add(new Double(prevNumber));
		if (getStd(exposureNumber, 1) == 0)
			return;
		System.out.println("bye");
		double min = 0 + Double.MIN_NORMAL;
		double max = Double.MAX_VALUE;
		double s = max / 2;
		while (max - min > s * MINIMUM_STEP * 2) {
			double dif = (s - min) / 2;
			double sStd = getStd(exposureNumber, s);
			double upperStd = getStd(exposureNumber, s + dif);
			double lowerStd = getStd(exposureNumber, s - dif);

			if (upperStd < sStd)
				min = s;
			else if (lowerStd < sStd)
				max = sStd;
			else {
				min = s - dif;
				max = s + dif;
			}
		}
		calculatedWeights.set(exposureNumber, new Double(s));
	}

	private int numExposureNumber(int number) {
		int count = 0;
		for (IQuestion q : questions)
			if (q.getStats().size() - 1 >= number)
				count++;
		return count;
	}

	private double getStd(int exposureNumber, double s) {
		if (numExposureNumber(exposureNumber) == 0)
			return 0;
		double sum = 0;
		int count = 0;
		for (IQuestion q : questions) {
			if (q.getStats().size() - 1 >= exposureNumber) {
				count++;
				Stat stat = q.getStats().get(exposureNumber);
				double guessedPercent = Math.pow(Math.E, -1 * stat.getTime() / s);
				sum += Math.abs(guessedPercent - stat.getValue());
			}
		}
		return Math.pow((1 / count) * sum, .5);
	}
}