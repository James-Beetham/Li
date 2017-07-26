package version_5;

import java.util.LinkedList;
import java.util.List;

/**
 * Character tree for linking a list of values to a keyword.
 * 
 * @author JamesBeetham
 *
 * @param <E>
 *            element to use as data (contained within a linked list)
 */
public class CharTree<E> {
	private Node root;

	/**
	 * Constructs a new tree of Characters which link to a list of questions.
	 */
	public CharTree() {
		root = new Node(null, null);
	}

	/**
	 * Add an element to the location at String s.
	 * 
	 * @param s
	 *            String to be used as a Character array for location, can't be
	 *            null
	 * @param data
	 *            element to add to the list of values at the location specified
	 *            by String s
	 */
	public void add(String s, E data) {
		if (s == null)
			throw new IllegalArgumentException("String can't be null.");
		find(s).data.add(data);
	}

	/**
	 * Returns the list of elements stored at the location specified by String
	 * s.
	 * 
	 * @param s
	 *            String (array of Characters) used to find the location, can't
	 *            be null
	 * @return List of elements at that location
	 */
	public List<E> get(String s) {
		if (s == null)
			throw new IllegalArgumentException("String was null.");
		return find(s).data;
	}

	/**
	 * Private function for finding the node from a String
	 * 
	 * @param s
	 *            String specifying location
	 * @return Node that is specified by the location
	 */
	private Node find(String s) {
		if (s == null)
			throw new IllegalArgumentException("String was null.");
		Node cur = root;
		for (Character c : s.toCharArray()) {
			if (cur.child == null)
				cur.child = new Node(c, null);
			cur = cur.child;

			while (!c.equals(cur.letter)) {
				if (cur.next == null)
					cur.next = new Node(c, null);
				cur = cur.next;
			}
		}
		return cur;
	}

	/**
	 * Container class for storing a Character and a LinkedList.
	 * 
	 * @author JamesBeetham
	 *
	 */
	private class Node {
		public Character letter;
		public LinkedList<E> data;
		public Node child;
		public Node next;

		public Node(Character letter, E data) {
			child = null;
			next = null;
			this.letter = letter;
			this.data = new LinkedList<E>();
			if (data != null)
				this.data.add(data);
		}
	}

	public String toString() {
		return toStringHelper(root, "");
	}

	private String toStringHelper(Node cur, String tabs) {
		String output = tabs + cur.letter;
		if (cur.data.size() != 0) {
			output += ": ";
			for (E d : cur.data)
				output += d.toString() + ", ";
			output.substring(0, output.length() - 1);
		}
		output += "\n";
		if (cur.child != null)
			output += toStringHelper(cur.child, tabs + "\t");
		if (cur.next != null)
			output += toStringHelper(cur.next, tabs);
		return output;
	}

}
