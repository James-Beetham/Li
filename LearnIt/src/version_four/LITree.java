package version_four;

import java.util.List;
/**
 * 
 * @author JamesBeetham
 *
 * @param <E>
 */
public class LITree<E> {
	private Container root;

	public LITree() {
		setup();
	}

	public LITree(List<E> arr) {
		setup();
		for (E e : arr) {
			// TODO add all elements from arr to this tree
		}
	}
	
	private void setup() {
		root = new Container(null);
	}

	public void add(List<E> path) {
		
	}

	private class Container {
		public E data;
		public Container parent;
		public Container child;
		public Container next;
		public Container prev;
		public Container(E data) {
			this.data = data;
			parent = null;
			child = null;
			next = null;
			prev = null;
		}
	}
}
