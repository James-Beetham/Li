package version_five;

import java.util.LinkedList;

public class DefaultQuestion implements IQuestion {
	private LinkedList<Stat> stats;
	private String question;
	private String answer;

	// TODO figure out type stuff
	/**
	 * Constructs a new DefaultQuestion from specified info. The string should
	 * start with the what to ask, then have a colon, then have the answer. The
	 * statistics are formatted within brackets [] as an array with every even
	 * index (i = 0, 2, 4, 6...) being the time, and every odd index being the
	 * value.
	 * 
	 * @param info
	 *            String used to generate this question
	 */
	public DefaultQuestion(String info) {
		String[] parts = info.split(":");
		question = parts[0];
		parts = parts[1].split("[");
		answer = parts[0];
		if (parts.length == 1)
			return;
		// TODO load stats
	}

	@Override
	public LinkedList<Stat> getStats() {
		return stats;
	}

	@Override
	public void ask(IEngine e) {
		
	}

	@Override
	public String[] getKeywords() {
		// TODO
		return null;
	}

	@Override
	public String getString() {
		return null;
	}

}
