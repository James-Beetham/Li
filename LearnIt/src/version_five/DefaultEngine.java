package version_five;

import java.util.Scanner;

public class DefaultEngine implements IEngine {

	public DefaultEngine() {

	}

	@Override
	public void print(String s) {
		System.out.print(s);
	}

	@Override
	public String get() {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		scan.close();
		return s;
	}

	public static void main(String[] args) {
		DefaultEngine e = new DefaultEngine();
		FileManager fm = new FileManager();
		
	}
}
