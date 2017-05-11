package version_five;

import java.util.Scanner;

public class DefaultEngine implements IEngine {
	private Scanner scan;

	public DefaultEngine() {
		scan = new Scanner(System.in);
	}

	@Override
	public void print(String s) {
		System.out.print(s);
	}

	@Override
	public String get() {
		System.out.println();
		String s = scan.nextLine();
		return s;
	}

	public static void main(String[] args) {
		DefaultEngine e = new DefaultEngine();
		DefaultFileManager fm = new DefaultFileManager();
		CharTree<IQuestion> keywords = new CharTree<IQuestion>();
		fm.load();
	}
}
