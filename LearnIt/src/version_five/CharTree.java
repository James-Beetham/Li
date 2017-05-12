package version_five;

import java.util.LinkedList;

public class CharTree<E> {
	private Node root;

	public CharTree() {
		root = new Node(null, null);
	}

	public void add(String s, E data) {
		find(s).data.add(data);
	}

	@SuppressWarnings("unchecked")
	public E[] get(String s) {
		return (E[]) find(s).data.toArray();
	}

	private Node find(String s) {
		Node cur = root;
		for (Character c : s.toCharArray()) {
			if (cur.child == null)
				cur.child = new Node(c, null);
			cur = cur.child;
			while (!cur.letter.equals(c)) {
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
			letter = this.letter;
			this.data = new LinkedList<E>();
			if (data != null)
				this.data.add(data);
		}
	}

	public String toString() {
		String output = "";
		String tabs = "";
		
		
		// TODO
		return output;
	}
	
}
