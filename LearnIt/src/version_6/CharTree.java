package version_6;

/**
 * Maps hashes generated from notes to questions.
 * 
 * @author James-Beetham
 */
public class CharTree<T> {
	private Node root;

	public CharTree() {
		root = new Node();
	}

	public CharTree(String data) {
		// TODO does this need to be a thing? (do we need to be able to save a
		// chartree?)
	}

	/**
	 * Add the data to the tree using the hash as a key.
	 * 
	 * @param hash
	 *            String key, can't be null
	 * @param data
	 *            to store
	 * @return true if successfully added data
	 */
	public boolean add(String hash, T data) {
		if (data == null)
			return false;

		getNode(hash).data = data;
		return true;
	}

	/**
	 * Removes the node specified by hash.
	 * 
	 * @param hash
	 *            string telling which node to remove
	 * @return true if successfully removed
	 */
	public boolean remove(String hash) {
		if (hash == null)
			return false;
		Node n = getNode(hash);
		remove(n);
		return n != null;
	}

	private void remove(Node n) {
		if (n == null)
			return;
		n.data = null;
		while (n.next == null && n.child == null) {
			n.parent.child = null;
			n.prev.next = null;
			n = n.parent;
		}
	}

	private Node getNode(String hash) {
		if (hash == null)
			return null;
		Node cur = root;
		for (int i = 0; i < hash.length(); i++) {
			if (cur.child == null) {
				// generate new child
				Node n = new Node();
				n.c = new Character(hash.charAt(i));
				cur.child = n;
				n.parent = cur;
			}
			cur = cur.child;

			// find the next character
			boolean findable = true;
			while (cur.c.charValue() != hash.charAt(i)) {
				if (cur.next == null) {
					findable = false;
					break;
				}
			}
			// if a new path needs to be generated
			if (!findable) {
				Node n = new Node();
				n.c = new Character(hash.charAt(i));
				cur.next = n;
				n.parent = n.parent;
				n.prev = cur;
				cur = cur.next;
			}
		}

		return cur;
	}

	/**
	 * Finds the data stored under the hash.
	 * 
	 * @param hash
	 *            string to search at, can't be null
	 * @return data stored there
	 */
	public T get(String hash) {
		if (hash == null)
			return null;
		return getNode(hash).data;
	}

	private class Node {
		public Character c;
		public T data;
		public Node next;
		public Node parent;
		public Node child;
		public Node prev;

		public Node() {
			c = null;
			data = null;
			next = null;
			child = null;
			parent = null;
			prev = null;
		}
	}

}
