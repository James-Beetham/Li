package version_five;

import java.util.ArrayList;
import java.util.LinkedList;
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

	@Override
	public IQuestion getQuestion(String s) {
		String[] parts = s.split("\t");

		ArrayList<Type> types = new ArrayList<Type>();
		// Instead of "DefaultQuestion", could also be
		// "version_five.DefaultQuestion"
		types.add(new Type("DefaultQuestion"));
		// Add more types here

		for (Type t : types)
			if (t.equals(parts[0]))
				return t.newQuestion(parts[1]);
		// TODO what when no questions are claimed
		return null;
	}

	public static void main(String[] args) {
		DefaultEngine e = new DefaultEngine();
		DefaultFileManager fm = new DefaultFileManager();
		CharTree<IQuestion> keywords = new CharTree<IQuestion>();
		String[] strQuestions = fm.load();

		LinkedList<IQuestion> questions = new LinkedList<IQuestion>();
		for (String s : strQuestions)
			questions.add(e.getQuestion(s));
		for (IQuestion q : questions)
			for (String s : q.getKeywords())
				keywords.add(s, q);

		DefaultAnalyzer a = new DefaultAnalyzer(.9, questions);

		String input = "";
		LinkedList<IQuestion> selectedQuestions = new LinkedList<IQuestion>();
		while (!input.equals("exit")) {
			input = e.get();
			if (input.startsWith("ask")) {
				double percent;
				try {
					percent = Double.parseDouble(input.substring(3).trim());
					a = new DefaultAnalyzer(percent, selectedQuestions);
					ask(a, selectedQuestions);
				} catch (Exception ex) {
					e.print("ask needs to be in the format of \"ask .91\" (number needs to be between 0 and 1: is a percent");
				}
			} else if (input.startsWith("import")) {

			} else if (input.startsWith("select")) {
			} else {
				// TODO show help / list of commands
			}
		}
	}

	private static void ask(IAnalyzer a, LinkedList<IQuestion> questions) {
		// TODO: sort questions list, input questions, "/" is for a command
	}

}
