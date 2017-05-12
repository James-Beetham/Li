package version_five;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
			if (t.equals(new Type(parts[0])))
				return t.newQuestion(parts[1]);
		// TODO what when no questions are claimed
		System.out.println(s);
		return null;
	}

	public static void main(String[] args) {
		DefaultEngine e = new DefaultEngine();
		DefaultFileManager fm = new DefaultFileManager();
		CharTree<IQuestion> keywords = new CharTree<IQuestion>();
		List<String> strQuestions = fm.load();

		LinkedList<IQuestion> questions = new LinkedList<IQuestion>();
		for (String s : strQuestions) {
			questions.add(e.getQuestion(s));
		}
		for (IQuestion q : questions)
			for (String s : q.getKeywords())
				keywords.add(s, q);

		DefaultAnalyzer a = new DefaultAnalyzer(.9, questions);

		String input = "";
		HashSet<String> selectedKeywords = new HashSet<String>();
		while (!input.equals("exit")) {
			e.print(":");
			input = e.get();
			if (input.startsWith("ask")) {
				try {
					double percent = Double.parseDouble(input.substring(3).trim());
					HashSet<IQuestion> selectedQuestions = new HashSet<IQuestion>();
					for (String s : selectedKeywords)
						for (IQuestion q : keywords.get(s))
							selectedQuestions.add(q);

					LinkedList<IQuestion> selectedQuestionsLL = new LinkedList<IQuestion>();
					for (IQuestion q : selectedQuestions) {
						q.setNextTime(a.nextTime(q));
						selectedQuestionsLL.add(q);
					}
					a = new DefaultAnalyzer(percent, selectedQuestionsLL);
					ask(a, e, selectedQuestionsLL);
				} catch (Exception ex) {
					e.print("ask needs to be in the format of \"ask .91\" (number needs to be between 0 and 1: is a percent\n");
				}
			} else if (input.startsWith("import")) {

			} else if (input.startsWith("select")) {
				try {
					input = input.substring(6).trim();
					HashSet<String> toAdd = new HashSet<String>();
					for (String s : input.split(" "))
						if (s.length() > 0) {
							toAdd.add(s.trim());
						}
					selectedKeywords.addAll(toAdd);

					int n = 0;
					for (String s : toAdd) {
						n += keywords.get(s).size();
					}
					e.print("Selected (" + n + ")\n");
				} catch (Exception ex) {
					e.print("select needs to be in the format of \"select [keyword]\".\n");
				}
			} else {
				// TODO show help / list of commands
			}
		}

		strQuestions = new ArrayList<String>();
		for (IQuestion q : questions)
			strQuestions.add(q.toString());
		e.print((fm.save(strQuestions) ? "Saved" : "Save Failed") + "\n");
	}

	private static void ask(IAnalyzer a, IEngine e, List<IQuestion> questions) {
		PriorityQueue<IQuestion> list = new PriorityQueue<IQuestion>(questions);
		IQuestion q;
		while ((q = list.poll()) != null)
			while (q.getNextTime() - System.currentTimeMillis() <= 0) {
				q.ask(e);
				q.setNextTime(a.nextTime(q));
				break;
			}

	}

}
