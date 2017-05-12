package version_five;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DefaultFileManager extends IFileManager {
	public DefaultFileManager() {
		super();
	}

	public DefaultFileManager(String path) {
		super(path);
	}

	public List<String> load() {
		return loadPath(path + "learnItSaved.txt");
	}

	public boolean save(List<String> arr) {
		try {
			PrintWriter pw = new PrintWriter(new File(path + "learnItSaved.txt"));
			for (String s : arr)
				pw.println(s);
			pw.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public List<String> loadPath(String path) {
		try {
			Scanner scan = new Scanner(new File(path));
			ArrayList<String> arr = new ArrayList<String>();
			while (scan.hasNextLine())
				arr.add(scan.nextLine());
			scan.close();
			return arr;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(
					"File does not exist: [" + path + "] searching in : " + new File("").getAbsolutePath());
		}
	}
}
