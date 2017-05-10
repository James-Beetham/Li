package version_one;

import java.io.File;

/**
 * Manages the files for the program
 * 
 * @author JamesBeetham
 */
public class FileManager {
	private String path;

	/**
	 * For development purposes only.
	 * 
	 * @param path
	 *            path to store information.
	 */
	public FileManager(String path) {
		this.path = path.charAt(path.length() - 1) == '/'? path : path + "/";
	}

	/**
	 * Handles individual files for FileManager.
	 * 
	 * @author JamesBeetham
	 */
	private class LiFile {
		public File[] files;

		/**
		 * Searches for specified file by name. Will check other variations of
		 * the name. Eg: input "hello.tx" will find "hello.txt", "hello.mp4",
		 * "hello".
		 * 
		 * @param fileName
		 *            name of the file
		 * @return true if file was loaded
		 */
		public LiFile(String fileName) {
			// TODO
		}

		/**
		 * Converts string to a standard name: all lowercase and no extension or
		 * periods.
		 * 
		 * @param name
		 *            name of the file
		 * @return converted string
		 */
		private String getFileName(String name) {
			// TODO
			return null;
		}
	}
}
