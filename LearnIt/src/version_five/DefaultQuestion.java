package version_five;

import java.util.LinkedList;

/**
 * Default question that has a specific question and answer.
 * 
 * @author JamesBeetham
 *
 */
public class DefaultQuestion implements IQuestion {
	private LinkedList<Stat> stats;
	private String question;
	private String answer;
	private long nextTime;

	/**
	 * Constructs a new DefaultQuestion from specified info. The string should
	 * start with the question, then have a ":", then have an answer, then have
	 * another ":" and any stats that were with the question.
	 * 
	 * @param info
	 *            String used to generate this question
	 */
	public DefaultQuestion(String info) {
		if (info == null)
			throw new IllegalArgumentException("String info can not be null.");
		stats = new LinkedList<Stat>();
		String[] parts = IQuestion.trimSplit(info, ":");
		question = parts[0];
		answer = parts[1];
		if (parts.length == 2)
			return;
		parts = IQuestion.trimSplit(parts[2], ",");
		for (int i = 0; i < parts.length - 1; i += 2)
			stats.add(new Stat(Long.parseLong(parts[i]), Double.parseDouble(parts[i + 1])));
	}

	@Override
	public LinkedList<Stat> getStats() {
		return stats;
	}

	@Override
	public void ask(IEngine e) {
		if (e == null)
			throw new IllegalArgumentException("Engine can not be null.");
		e.print(question + "?\n->)");
		if (e.get().equals(answer))
			stats.add(new Stat(1));
		else
			stats.add(new Stat(0));
	}

	@Override
	public String[] getKeywords() {
		String list = "defaultquestion";
		String questionCopy = question + "";
		for (int i = 0; i < questionCopy.length(); i++)
			if (questionCopy.charAt(i) == '[') {
				String tag = "";
				for (int j = i + 1; j < questionCopy.length() && questionCopy.charAt(j) != ']'; j++)
					tag += questionCopy.charAt(j);
				list += "," + tag;
			}
		return list.split(",");
	}

	@Override
	public String getString() {
		String stat = "";
		if (stats.size() != 0) {
			for (Stat s : stats) {
				stat += s.toString() + ",";
			}
			stat.substring(0, stat.length() - 1);
		}
		return this.getClass().toString().substring(6) + "\t" + question + ":" + answer + ":" + stat;
	}

	@Override
	public int compareTo(IQuestion arg0) {
		if (arg0 == null)
			throw new IllegalArgumentException("Can not compare to a null question.");
		return (int) (nextTime - arg0.getNextTime());
	}

	@Override
	public long getNextTime() {
		long guessedTime = System.currentTimeMillis() + DefaultAnalyzer.MILLIS_IN_A_DAY * getStats().size();
		return (nextTime + guessedTime) / 2;
	}

	@Override
	public void setNextTime(long time) {
		nextTime = time;
	}

	@Override
	public String toString() {
		return getString();
	}

}
