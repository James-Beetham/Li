package version_five;

import java.io.File;
import java.util.List;

public abstract class IFileManager {
	protected String path;

	public IFileManager() {
		path = "";
	}

	public IFileManager(String path) {
		this.path = path;
		if (!new File(path).exists())
			throw new IllegalArgumentException("File path [" + path + "] does not exist");
		if (!path.endsWith("/"))
			this.path = path + "/";
	}

	/**
	 * Loads all questions from the default path.
	 * 
	 * @return array of all the questions (as strings)
	 */
	public abstract List<String> load();

	/**
	 * Loads the specified path.
	 * 
	 * @param path
	 *            location to load from
	 * @return array of all the questions
	 */
	public abstract List<String> loadPath(String path);

	/**
	 * Saves the specified array to the default path (or however this is
	 * implemented).
	 * 
	 * @param arr
	 *            array of strings to save
	 * @return true if saved
	 */
	public abstract boolean save(List<String> arr);
}
