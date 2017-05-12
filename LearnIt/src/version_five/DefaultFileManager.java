package version_five;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The default file manager that uses the specified path and saves the
 * information to "learnItSaved.txt"
 * 
 * @author JamesBeetham
 *
 */
public class DefaultFileManager extends IFileManager {
	public DefaultFileManager() {
		super();
	}

	public DefaultFileManager(String path) {
		super(path);
	}

	@Override
	public List<String> load() {
		String fPath = path + "learnItSaved.txt";
		File f = new File(fPath);
		if (!f.exists())
			save(new ArrayList<String>());
		try {
			return loadPath(fPath);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("The File was created so how was this thrown...");
		}
	}

	@Override
	public boolean save(List<String> arr) {
		if (arr == null)
			throw new IllegalArgumentException("List to save can not be null.");
		try {
			PrintWriter pw = new PrintWriter(new File(path + "learnItSaved.txt"));
			pw.print(""); // Ensure file is created.
			for (String s : arr)
				pw.println(s);
			pw.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	@Override
	public List<String> loadPath(String path) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(path));
		ArrayList<String> arr = new ArrayList<String>();
		while (scan.hasNextLine())
			arr.add(scan.nextLine());
		scan.close();
		return arr;
	}
}
