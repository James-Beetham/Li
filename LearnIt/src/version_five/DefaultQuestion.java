package version_five;

import java.util.LinkedList;

public class DefaultQuestion implements IQuestion {
	private LinkedList<Stat> stats;
	private String question;
	private String answer;
	private long nextTime;

	// TODO figure out type stuff
	/**
	 * Constructs a new DefaultQuestion from specified info. The string should
	 * start with the question, then have a ":", then have an answer, then have
	 * another ":" and any stats that were with the question.
	 * 
	 * @param info
	 *            String used to generate this question
	 */
	public DefaultQuestion(String info) {
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
		e.print(question);
		if (e.get().equals(answer))
			stats.add(new Stat(1));
		else
			stats.add(new Stat(0));
	}

	@Override
	public String[] getKeywords() {
		String list = "defaultquestion,default question," + question + "," + answer;
		// TODO add more things to the list
		return list.split(",");
	}

	@Override
	public String getString() {
		String stat = "";
		for (Stat s : stats) {
			stat += s.toString() + ",";
		}
		stat.substring(0, stat.length() - 1);
		return this.getClass().toString().substring(6) + "\t" + answer + ":" + question + ":" + stat;
	}

	@Override
	public int compareTo(IQuestion arg0) {
		return (int) (nextTime - arg0.getNextTime());
	}

	@Override
	public long getNextTime() {
		return nextTime;
	}

	@Override
	public void setNextTime(long time) {
		nextTime = time;
	}

}
