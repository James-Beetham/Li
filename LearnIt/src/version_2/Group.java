package version_2;

public class Group {
	private String name;
	private Group parent;
	private Group child;
	private Group next;
	private Group prev;
	private Note note;
	/**
	 * Construct new single group with specified name.
	 * 
	 * @param name
	 *            name of this group
	 */
	public Group(String name) {
		this.name = name;
		parent = null;
		child = null;
		next = null;
		prev = null;
		note = null;
	}

	/**
	 * Removes this group from the tree if no children.
	 * 
	 * @return true if successful
	 */
	public boolean remove() {
		// TODO
		return false;
	}

	/**
	 * Links this group under the specified group.
	 * 
	 * @param parent
	 *            new parent of this group
	 * @return true if successful
	 */
	public boolean link(Group parent) {
		// TODO
		return false;
	}

	/**
	 * Add a new list of notes under this group.
	 * 
	 * @param n
	 *            note to add
	 * @return true if successful
	 */
	public boolean add(Note n) {
		// TODO
		return false;
	}
}

class Note {
	private Word keyWord;
	private Note next;
	private Note prev;
	private String info;
	private Group group;
	private String type;
	/**
	 * Constructs a new note with the information from info.
	 * 
	 * @param info
	 */
	public Note(String info) {
		keyWord = null;
		next = null;
		prev = null;
		this.info = info;
		group = null;
		update();
	}
	/**
	 * Adds keyword to this note.
	 * 
	 * @param keyWord
	 *            word to add
	 * @return true if successful
	 */
	public boolean add(Word keyWord) {
		// TODO
		return false;
	}
	/**
	 * Sets the parent of this note.
	 * 
	 * @param parent
	 *            group that is the parent
	 * @return true if successful
	 */
	public boolean set(Group parent) {
		// TODO
		return false;
	}
	/**
	 * Adds the note after this note in the list.
	 * 
	 * @param note
	 * @return true if successful
	 */
	public boolean add(Note note) {
		// TODO
		return false;
	}
	/**
	 * Updates type variable.
	 * 
	 * @return true if successful
	 */
	private boolean update() {
		// TODO set info
		return false;
	}
}

class Word {
	private String word;
	private Word next;
	private Word prev;
	/**
	 * Constructs a new unlinked word from string specified.
	 * 
	 * @param word
	 *            string to use as this word
	 */
	public Word(String word) {
		this.word = word;
		next = null;
		prev = null;
	}
	/**
	 * Links this word to the end of the list specified by word.
	 * 
	 * @param word
	 *            word to add this word behind
	 * @return true if successful
	 */
	public boolean link(Word word) {
		// TODO
		return false;
	}
}