package version_3;

import java.util.Arrays;
import java.util.Iterator;

public class Index implements Iterable {

	public static void main(String[] args) {
		String[] arr = {"group1", "-group2 3", " -group3", " -group4", "-group5", " -group6", "group7", ""};
		Index i = new Index(arr);
		i.print();
	}

	private Group root;

	public Index(String[] index) {
		index = index.clone();
		root = new Group("root");
		setGroups(index);
	}

	/**
	 * Sets up the groups from source strings such that every new line is the
	 * name of a group. Ie: "group1\n-group2\n -group3\n -group4\n-group5\n"
	 * root has children group1, group1 has children group2 and group5, group2
	 * has children group3 and group4.
	 * 
	 * @param groups
	 *            array of strings with nested groups
	 * @return remaining strings after empty line breaks
	 */
	private String[] setGroups(String[] groups) {
		Group cur = root;
		int level = -1;
		int count = 0;
		for (int i = 0; i < groups.length; i++) {
			if (groups[i] == null)
				throw new NullPointerException("Line [" + i + "] was null. Last added Group was " + cur.getName());
			if (groups[i].length() == 0)
				break;
			int newLevel = Math.max(groups[i].lastIndexOf("-") + 1, 0);
			String name = groups[i].substring(groups[i].indexOf("-") + 1);
			int length = 0;
			if (groups[i].lastIndexOf(" ") > groups[i].indexOf("-")) {
				length = Integer.parseInt(groups[i].substring(groups[i].lastIndexOf(" ") + 1));
				name = name.substring(0, groups[i].lastIndexOf(" ") - 1);
			}
			Group g = new Group(name);
			g.setLength(length);

			while (level + 1 > newLevel) {
				cur = cur.getParent();
				level--;
			}
			cur.addChild(g);
			level = newLevel;
			cur = g;
			groups[i] = null;
			count++;
		}

		String[] returnStrings = new String[groups.length - count];
		count = 0;
		for (String group : groups) {
			if (group != null) {
				returnStrings[count] = group;
				count++;
			}
		}

		return returnStrings;
	}

	public Group getGroup(String groupPath) {
		if (groupPath == null)
			throw new NullPointerException("Path for the group can not be null.");
		if (groupPath.length() == 0)
			return root;
		String[] groupNames = groupPath.split("/");
		Group cur = root;
		for (String group : groupNames) {
			if (group.equals(".."))
				cur = cur.getParent();
			else {
				if (cur.getChild() == null)
					return null;
				cur = cur.getChild();
				boolean exists = false;
				while (exists == false && cur != null) {
					// System.out.println("[" + group + "," + cur.getName());
					if (cur.getName().equals(group))
						exists = true;
					else
						cur = cur.getNext();
				}
				if (!exists)
					return null;
			}
		}
		return cur;
	}

	public String[] getGroups(String groupPath) {
		Group cur = getGroup(groupPath);
		// System.out.println("(" + groupPath + ", " + cur.getName());
		if (cur == null || cur.getChild() == null)
			return new String[0];
		cur = cur.getChild();
		String output = "";
		while (cur != null) {
			output += cur.getName() + (cur.getNext() == null ? "" : ",");
			cur = cur.getNext();
		}
		return output.split(",");
	}

	public String[] getNestedGroups(String groupPath) {
		// TODO
		return null;
	}

	public String[] getNodes(String groupPath) {
		// TODO
		return null;
	}

	public String toString() {
		if (root.getChild() == null)
			return "";
		String output = "";
		output += toStringHelperGroup(root.getChild(), 0);
		return output;
	}

	public String toString(String path) {
		Group g = getGroup(path);
		if (g.getChild() != null)
			return toStringHelperGroup(g.getChild(), 0);
		else
			return "";
	}

	public void print() {
		System.out.println(printHelper(root, 0));
	}

	private String printHelper(Group g, int level) {
		String output = "";
		output += repeatedString("\t", level) + g.getName() + "\n";
		if (g.getNote() != null)
			output += printHelper(g.getNote(), level + 1);
		if (g.getChild() != null)
			output += printHelper(g.getChild(), level + 1);
		if (g.getNext() != null)
			output += printHelper(g.getNext(), level);
		return output;
	}

	private String printHelper(Note n, int level) {
		String output = "";
		output += repeatedString("\t", level) + n.getInfo() + "\n";
		if (n.getNext() != null)
			output += printHelper(n.getNext(), level);
		return output;
	}

	private String toStringHelperGroup(Group g, int level) {
		String output = repeatedString(" ", level - 1) + (level == 0 ? "" : "-") + g.getName() + " " + g.getLength() + "\n";
		if (g.getChild() != null)
			output += toStringHelperGroup(g.getChild(), level + 1);
		if (g.getNext() != null)
			output += toStringHelperGroup(g.getNext(), level);
		return output;
	}

	private String repeatedString(String toRepeat, int length) {
		String output = "";
		for (int i = 0; i < length; i++) {
			output += toRepeat;
		}
		return output;
	}

	/**
	 * Groups other Group and nodes (acts as a folder and file).
	 * 
	 * @author JamesBeetham
	 *
	 */
	protected class Group {
		private String name;
		private Group parent;
		private Group child;
		private Group next;
		private Group prev;
		private Note note;
		private int length;
		public Group(String name) {
			setName(name);
			setParent(null);
			child = null;
			next = null;
			prev = null;
			note = null;
			length = 0;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			if (name == null)
				throw new NullPointerException("Group name can not be null.");
			this.name = name;
		}
		public Group getParent() {
			return parent;
		}
		public void setParent(Group parent) {
			this.parent = parent;
		}
		public Group getChild() {
			return child;
		}
		public void setChild(Group child) {
			this.child = child;
		}
		public void addChild(Group child) {
			if (child == null)
				throw new NullPointerException("Adding a child to a group can not be null.");
			child.setParent(this);
			if (this.child == null) {
				this.setChild(child);
				return;
			}
			Group cur = this.child;
			while (cur.getNext() != null)
				cur = cur.getNext();
			cur.setNext(child);
			child.setPrev(cur);
			child.setParent(this);
		}
		public Group getNext() {
			return next;
		}
		public void setNext(Group next) {
			this.next = next;
		}
		public Group getPrev() {
			return prev;
		}
		public void setPrev(Group prev) {
			this.prev = prev;
		}
		public Note getNote() {
			if (note == null && length == 0)
				loadNotes();
			return note;
		}
		public void loadNotes() {
			String[] lines = FileManager.loadFile(getPath());
			int newLength = 0;
			Note prev = null;
			for (String line : lines) {
				Note n = new Note();
				n.setInfo(line);
				n.setParent(this);
				if (prev != null)
					prev.setNext(n);
				length++;
			}
		}
		public void setNote(Note note) {
			if (this.note == null && note != null)
				length++;
			this.note = note;
		}
		public String getPath() {
			// TODO test
			Group cur = this;
			String path = "/" + cur.getName();
			while (cur.getParent() != null) {
				cur = cur.getParent();
				path = "/" + cur.getName() + path;
			}
			return path;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			if (length < 0)
				throw new IllegalArgumentException("Length must be positive but was: " + length);
			this.length = length;
		}
		public void remove() {
			// TODO test
			Group cur;
			if (child != null) {
				cur = child;
				while (cur != null) {
					cur.setParent(parent);
					cur = cur.getNext();
				}
			}
			if (child != null) {
				child.setPrev(prev);
				if (prev != null)
					prev.setNext(child);
				cur = child;
				while (cur.getNext() != null)
					cur = cur.getNext();
				cur.setNext(next);
				if (next != null)
					next.setPrev(cur);

			}
			if (prev != null)
				prev.setNext(next);
			if (next != null)
				next.setPrev(prev);
			if (parent.getChild() == this)
				parent.setChild(child);
		}
		public int getLevel() {
			int level = 0;
			Group cur = this;
			while (cur != null) {
				cur = cur.getParent();
				level++;
			}
			return level;
		}
		@Override
		public String toString() {
			String sPrev = (prev == null) ? "null" : prev.getName();
			String sNext = (next == null) ? "null" : next.getName();
			String sParent = (parent == null) ? "null" : parent.getName();
			String sChild = (child == null) ? "null" : child.getName();
			return "[" + name + "]:[prev: " + sPrev + ", next: " + sNext + ", parent: " + sParent + ", child: " + sChild + "], [notes: " + length
					+ "]";

		}

	}

	/**
	 * Contains the keywords for the note and info from notes files.
	 * 
	 * @author JamesBeetham
	 * 
	 */
	protected class Note {
		private Word keyWord;
		private Note next;
		private Group parent;
		private String info;
		private Type type;
		private Question question;
		private int numQuestions;
		public Note() {
			keyWord = null;
			next = null;
			parent = null;
			info = null;
			type = null;
			question = null;
			numQuestions = -1;
		}
		public Word getKeyWord() {
			return keyWord;
		}
		public void setKeyWord(Word keyWord) {
			this.keyWord = keyWord;
		}
		public Note getNext() {
			return next;
		}
		public void setNext(Note next) {
			this.next = next;
		}
		public Group getParent() {
			return parent;
		}
		public void setParent(Group parent) {
			this.parent = parent;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getType() {
			if (type == null)
				loadType();
			return type.toString();
			// TODO type toString
		}
		public void loadType() {
			// TODO
		}
		public void setType(Type type) {
			this.type = type;
		}
		public Question getQuestion() {
			if (question == null && numQuestions != 0)
				generateQuestions();
			return question;
		}
		public void setQuestion(Question question) {
			this.question = question;
		}
		private Question generateQuestions() {
			return null;
			// TODO
		}
	}

	/**
	 * Used for storing keyword locations.
	 * 
	 * @author JamesBeetham
	 * 
	 */
	protected class Word {
		private String string;
		private Word next;
		public String getString() {
			return string;
		}
		public void setString(String string) {
			this.string = string;
		}
		public Word getNext() {
			return next;
		}
		public void setNext(Word next) {
			this.next = next;
		}
		public Word(String string) {
			this.string = string;
			next = null;
		}
	}

	/**
	 * Used as individual nodes or "letters" for keyword search.
	 * 
	 * @author JamesBeetham
	 * 
	 */
	protected class Letter {
		private char c;
		private Letter parent;
		private Letter child;
		private Letter next;
		private Letter prev;
		public Letter(char c) {
			this.c = c;
			parent = null;
			child = null;
			next = null;
			prev = null;
		}
		public char getC() {
			return c;
		}
		public void setC(char c) {
			this.c = c;
		}
		public Letter getParent() {
			return parent;
		}
		public void setParent(Letter parent) {
			this.parent = parent;
		}
		public Letter getChild() {
			return child;
		}
		public void setChild(Letter child) {
			this.child = child;
		}
		public Letter getNext() {
			return next;
		}
		public void setNext(Letter next) {
			this.next = next;
		}
		public Letter getPrev() {
			return prev;
		}
		public void setPrev(Letter prev) {
			this.prev = prev;
		}
	}

	protected class Question {
		private String type;
		private Note note;
		private Stat[] stats;
		private int index;
		public Question(int index) {
			type = null;
			note = null;
			this.index = index;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Note getNote() {
			return note;
		}
		public void setNote(Note note) {
			this.note = note;
		}
		public Stat[] getStats() {
			return stats;
		}
		public void setStats(Stat[] stats) {
			this.stats = stats;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
	}

	protected class Type {
		private String separators;
		private Separator[] parts;
		private String[] hideLabel;
		private Answer[] answer;
		public Type(String[] args) {

		}
		public String getSeparators() {
			return separators;
		}
		public void setSeparators(String separators) {
			this.separators = separators;
		}
		public Separator[] getParts() {
			return parts;
		}
		public void setParts(Separator[] parts) {
			this.parts = parts;
		}
		public String[] getHideLabel() {
			return hideLabel;
		}
		public void setHideLabel(String[] hideLabel) {
			this.hideLabel = hideLabel;
		}
		public Answer[] getAnswer() {
			return answer;
		}
		public void setAnswer(Answer[] answer) {
			this.answer = answer;
		}
	}

	protected interface Answer {
		public Stat ask();
	}

	protected class Separator {
		private String label;
		private char separator;
		public Separator(String label, char separator) {
			this.label = label;
			this.separator = separator;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public char getSeparator() {
			return separator;
		}
		public void setSeparator(char separator) {
			this.separator = separator;
		}
	}

	protected class Stat {
		private Question question;
		private long time;
		private double answer;
		public Stat(Question question, double answer) {
			this.question = question;
			this.answer = answer;
			time = System.currentTimeMillis();
		}
		public Question getQuestion() {
			return question;
		}
		public void setQuestion(Question question) {
			this.question = question;
		}
		public long getTime() {
			return time;
		}
		public void setTime(long time) {
			this.time = time;
		}
		public double getAnswer() {
			return answer;
		}
		public void setAnswer(double answer) {
			this.answer = answer;
		}
	}

	public GroupIterator groupIterator() {
		return new GroupIterator();
	}

	protected class GroupIterator implements Iterator {
		Group cur;

		public GroupIterator() {
			cur = root;
		}

		@Override
		public boolean hasNext() {
			return cur.getNext() != null;
		}

		public boolean hasPrev() {
			return cur.getPrev() != null;
		}

		public boolean hasParent() {
			return cur.getParent() != null;
		}

		public boolean hasChild() {
			return cur.getChild() != null;
		}

		@Override
		public String next() {
			if (!hasNext())
				throw new IllegalStateException("No next to go to.");
			cur = cur.getNext();
			return cur.getName();
		}

		public String prev() {
			if (!hasPrev())
				throw new IllegalStateException("No previous to go to.");
			cur = cur.getPrev();
			return cur.getName();
		}

		public String parent() {
			if (!hasParent())
				throw new IllegalStateException("No parent to go to.");
			cur = cur.getParent();
			return cur.getName();
		}

		public String child() {
			if (!hasChild())
				throw new IllegalStateException("No child to go to.");
			cur = cur.getChild();
			return cur.getName();
		}

		public void remove() {
			if (cur == root)
				throw new IllegalStateException("Tried to remove root.");
			cur.remove();
			if (cur.getNext() != null)
				cur = cur.getNext();
			else if (cur.getPrev() != null)
				cur = cur.getPrev();
			else if (cur.getChild() != null)
				cur = cur.getChild();
			else
				cur = cur.getParent();
		}
		// TODO add, remove, edit?
	}

	@Override
	public IndexIterator iterator() {
		return new IndexIterator();
	}

	protected class IndexIterator implements Iterator {
		private Group cur;
		public IndexIterator() {
			cur = root;
		}

		@Override
		public boolean hasNext() {
			return next() != null;
		}

		@Override
		public Group next() {
			// TODO
			return null;
		}

	}

}
