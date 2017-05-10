package version_five;

import java.util.LinkedList;

public interface Question {
	/**
	 * Returns the type of this class of question. List of available types are given 
	 * @return
	 */
	public static Type getType() {
		return new Type("interface");
	}
	public LinkedList<Stat> getStats();
	public String ask(Engine e);
	public String[] getKeywords();
	public String toString();
}
