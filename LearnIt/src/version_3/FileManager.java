package version_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
	public static boolean loadNotes(String path) {

		return false;
	}

	public static String[] loadFile(String path) {
		ArrayList<String> fileLines = new ArrayList<String>();
		if (!new File("sources").exists())
			new File("sources").mkdir();
		if (!new File("sources/" + path).exists()) {
			new File("sources/" + path).mkdirs();
			saveFile(path, new String[1]);
		}
		Scanner scan;
		try {
			path += ".txt";
			scan = new Scanner(new File("sources/" + path));
			while (scan.hasNextLine()) {
				String next = scan.nextLine();
				if (next.length() != 0)
					fileLines.add(next);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error, could not find file: sources|" + path + "|");
		}
		return fileLines.toArray(new String[fileLines.size()]);
	}

	public static void saveFile(String path, String[] lines) {
		try {
			PrintWriter pw = new PrintWriter(new File("sources/" + path + ".txt"));
			for (String line : lines) {
				pw.println(line);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
