package version_6;

/**
 * Stores a note's text and can generate a hash from the note.
 * 
 * @author James-Beetham
 *
 */
public class Note {
	/**
	 * Where this note is stored.
	 */
	private String path;
	/**
	 * Text for this note.
	 */
	private String text;

	public Note(String path, String text) {
		this.path = path;
		this.text = text;
	}

	public String getHash() {
		return Note.genHash(text);
	}

	public String getText() {
		return text;
	}

	public String getPath() {
		return path;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Converts a String to an int. Used to link note text and questions.
	 * 
	 * @param source
	 *            source text (of the note)
	 * @return an int
	 */
	public static String genHash(String source) {
		// String incase of a change to sha1, md5, or source text.
		return "" + source.hashCode();
	}
}
