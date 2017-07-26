package version_1;

import java.util.Iterator;

/**
 * Data structure for holding statistics.
 * 
 * @author JamesBeetham
 */
public class Statistics implements Iterable {

	public Statistics() {

	}

	@Override
	public Iterator iterator() {
		return new StatIterator();
	}

	private class StatIterator implements Iterator {

		public StatIterator() {

		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	private class Variable {
		public boolean continuum;
		public double start;
		public double end;
		/**
		 * Constructs a new variable
		 * 
		 * @param continuum
		 *            true if values can be double
		 * @param start
		 *            possible first values (inclusive)
		 * @param end
		 *            possible last values (inclusive)
		 */
		public Variable(boolean continuum, double start, double end) {
			if (!continuum && ((int) start != start || (int) end != end))
				throw new IllegalArgumentException("When making a variable that can't be a continuum, start and end must be integers.");
			this.continuum = continuum;
			this.start = start;
			this.end = end;
		}
		/**
		 * Constructs new variable in the form of a list.
		 * 
		 * @param start
		 * @param end
		 */
		public Variable(int start, int end) {
			continuum = false;
			this.start = start;
			this.end = end;
		}
	}

}
