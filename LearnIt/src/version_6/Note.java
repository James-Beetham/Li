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
		// TODO generate hash from text
		return null;
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
}
