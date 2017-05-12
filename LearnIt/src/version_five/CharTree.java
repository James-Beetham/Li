package version_five;

import java.util.LinkedList;
import java.util.List;

public class CharTree<E> {
	private Node root;

	public CharTree() {
		root = new Node(null, null);
	}

	public void add(String s, E data) {
		if (s == null || data == null)
			throw new IllegalArgumentException("String or data was null.");
		find(s).data.add(data);
	}

	@SuppressWarnings("unchecked")
	public List<E> get(String s) {
		if (s == null)
			throw new IllegalArgumentException("String was null.");
		return find(s).data;
	}

	private Node find(String s) {
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
