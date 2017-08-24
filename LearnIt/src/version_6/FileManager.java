package version_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controls all the interaction between information stored by the OS and
 * information within the program. Can read, write, and delete files.
 * 
 * @author James-Beetham
 *
 */
public class FileManager {

	public FileManager() {

	}

	/**
	 * Finds the file specified and gets the text from it.
	 * 
	 * @param path
	 *            String of where the file is
	 * @return either the text from the File or null if not found.
	 */
	public ArrayList<String> getFile(String path) {
		if (path == null)
			return null;
		File f = new File(path);
		if (!f.exists())
			return null;

		ArrayList<String> arr = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(f);
			String s;
			while ((s = scan.nextLine()) != null)
				arr.add(s);
			scan.close();
		} catch (FileNotFoundException e) {
			// This is checked for a the beginning of getFile().
		}
		return arr;
	}

	/**
	 * Stores the content to the path. A new line will be added after every element
	 * in content.
	 * 
	 * @param path
	 *            location to be stored at
	 * @param content
	 *            content to be stored
	 */
	public void store(String path, ArrayList<String> content) {
		if (path == null || content == null)
			return;
		File f = new File(path);
		File dir = f.getParentFile();
		if (!dir.exists())
			dir.mkdirs();
		try {
			PrintWriter pw = new PrintWriter(f);
			for (String s : content)
				pw.println(s);
			pw.close();
		} catch (FileNotFoundException e) {
			// This is checked and directories needed are generated at the beginning of
			// store().
		}
	}

	/**
	 * Deletes whatever is specified at path. Also removes empty parent files.
	 * 
	 * @param path
	 *            location to be deleted
	 * @return true if successfully deleted
	 */
	public boolean delete(String path) {
		if (path == null)
			return false;
		File f = new File(path);
		if (!f.exists())
			return false;
		delete(f);
		return true;
	}

	/**
	 * Recursive helper class for delete(String path)
	 * 
	 * @param f
	 *            file to be deleted
	 */
	private void delete(File f) {
		// If the file is a directory, remove everything in it
		if (f.isDirectory()) {
			for (File file : f.listFiles())
				delete(file);
		}

		// remove all empty parent files
		File parent = f.getParentFile();
		f.delete();
		if (parent.listFiles().length == 0)
			delete(parent);
	}

}
