package version_four;

public interface INavigator<E> {
	public boolean hasParent();
	public boolean hasChild();
	public boolean hasNext();
	public boolean hasPrev();
	public E parent();
	public E child();
	public E next();
	public E prev();
	/**
	 * Gets the width of the current element (how many prev / next).
	 * 
	 * @return number of elements in this row
	 */
	public int width();
	/**
	 * Gets the level of the current element (how many parents).
	 * 
	 * @return number of elements above
	 */
	public int level();
	/**
	 * Gets the total number of elements in the tree.
	 * @return number of elements in the tree
	 */
	public int length();
}
