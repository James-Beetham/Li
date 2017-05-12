package version_five;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Base class for a FileManager: stores a path.
 * 
 * @author JamesBeetham
 *
 */
public abstract class AbstractFileManager {
	protected String path;

	public AbstractFileManager() {
		path = "";
	}

	/**
	 * Construct a new FileManager from specified path.
	 * 
	 * @param path
	 *            String specifying the path the FileManager should use to find
	 *            files
	 */
	public AbstractFileManager(String path) {
		if (path == null)
			throw new IllegalArgumentException("Path can not be null.");
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
	 * @throws FileNotFoundException
	 *             if the file is not found
	 */
	public abstract List<String> loadPath(String path) throws FileNotFoundException;

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
