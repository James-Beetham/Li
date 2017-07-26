package version_5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Default engine that uses command line to get commands.
 * 
 * @author JamesBeetham
 *
 */
public class DefaultEngine implements IEngine {
	private Scanner scan;

	/**
	 * Construct the default engine that uses command line to display and input
	 * data.
	 */
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
		// Add more types here TODO

		for (Type t : types)
			if (t.equals(new Type(parts[0])))
				return t.newQuestion(parts[1]);
		// TODO what happens when no questions are claimed
		return null;
	}

	/**
	 * Entry point to program.
	 * 
	 * @param args
	 *            arguments for this
	 */
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
					for (String s : selectedKeywords) {
						if (s.equals("/all"))
							selectedQuestions.addAll(questions);
						for (IQuestion q : keywords.get(s))
							selectedQuestions.add(q);
					}

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
				try {
					List<String> qs = fm.loadPath(input.substring(6, input.length()).trim());
					for (String s : qs)
						questions.add(e.getQuestion(s));
					e.print("Imported");
				} catch (FileNotFoundException fnfe) {
					e.print("That path does not seem to exist.");
				} catch (Exception ex) {
					e.print("import needs to be in the format of \"import school/classes/english/review.txt\". Type \"help\" for more info.");
				}
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
					int askable = 0;
					for (String s : toAdd) {
						for (IQuestion q : keywords.get(s)) {
							n++;
							q.setNextTime(a.nextTime(q));
							if (q.getNextTime() <= System.currentTimeMillis())
								askable++;
						}
					}
					if (Arrays.asList(input.split(" ")).contains("/all")) {
						askable = 0;
						for (IQuestion q : questions) {
							q.setNextTime(a.nextTime(q));
							if (q.getNextTime() <= System.currentTimeMillis())
								askable++;
						}
						n = questions.size();

					}
					e.print("Selected (" + askable + "/" + n + ")\n");
					// TODO better way of checking for commands.
					// TODO better information for how many questions actually
					// need to be asked.
				} catch (Exception ex) {
					e.print("select needs to be in the format of \"select [keyword]\".\n");
				}
			} else if (input.startsWith("clear")) {
				e.print("Cleared (" + selectedKeywords.size() + ")\n");
				selectedKeywords = new HashSet<String>();
			} else if (input.startsWith("help")) {
				// TODO instructions for how this works
			} else if (!input.equals("exit")) {
				e.print("Invalid command. List of commands:\n\tselect (term1) [term2] [term3]...\n\tclear\n\task (percentCorrect)\n\timport (path)\n\thelp\n");
			}
		}

		strQuestions = new ArrayList<String>();
		for (IQuestion q : questions)
			strQuestions.add(q.toString());
		e.print((fm.save(strQuestions) ? "Saved" : "Save Failed") + "\n");
	}

	/**
	 * Ask the list of questions using engine e and analyzer a in order until
	 * the predicted time for remembering of all the questions is in the future.
	 * 
	 * @param a
	 *            analyzer to use to update the positions of the questions
	 * @param e
	 *            engine to use to display and input questions
	 * @param questions
	 *            list of questions to ask
	 */
	private static void ask(IAnalyzer a, IEngine e, List<IQuestion> questions) {
		PriorityQueue<IQuestion> list = new PriorityQueue<IQuestion>(questions);
		IQuestion q;
		while ((q = list.poll()) != null)
			while (q.getNextTime() - System.currentTimeMillis() <= 0) {
				q.ask(e);
				long nextTime;
				q.setNextTime(nextTime = a.nextTime(q));
				e.print("[Next time for previous question: " + ((nextTime - System.currentTimeMillis()) / 1000)
						+ "]\n");
				// TODO make that printout more attractive.
				break;
			}

	}

}
