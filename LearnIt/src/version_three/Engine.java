package version_three;

import java.util.Arrays;
import java.util.Scanner;

public class Engine {
	public static void main(String[] args) {
		Engine e = new Engine();
	}

	private IUserInterface ui;
	private Index index;
	private FileManager fm;
	private Command[] commands;
	private String currentPath;

	public Engine() {
		ui = new defaultUI();
		setup();
		inputCommands();
	}

	public Engine(IUserInterface input) {
		this.ui = input;
		setup();
		inputCommands();
	}

	private void setup() {
		ui.print("-Setting up... ");
		fm = new FileManager();
		String[] temp = fm.loadFile(".LIIndex");
		// System.out.print(Arrays.toString(temp));
		index = new Index(temp);
		currentPath = "";

		commands = new Command[9];
		commands[0] = new Command("cd");
		commands[1] = new Command("ls");
		commands[2] = new Command("settings");
		commands[3] = new Command("import");
		commands[4] = new Command("export");
		commands[5] = new Command("stats");
		commands[6] = new Command("help");
		commands[7] = new Command("search");
		commands[8] = new Command("quit,exit,stop");

		ui.println("Done!");
	}

	private void inputCommands() {
		String input;
		while (true) {
			ui.print("LearnIt:" + currentPath + ">");
			input = ui.getInput();
			if (commands[0].match(input))
				cd(commands[0].subString(input));
			else if (commands[1].match(input))
				ls(commands[1].subString(input));
			else if (commands[2].match(input))
				settings();
			else if (commands[3].match(input))
				importPath(commands[3].subString(input));
			else if (commands[4].match(input))
				exportPath(commands[4].subString(input));
			else if (commands[5].match(input))
				getStats();
			else if (commands[6].match(input))
				help();
			else if (commands[7].match(input))
				search(commands[7].subString(input));
			else if (commands[8].match(input))
				break;
			else
				ui.print("Command not found. Type \"help\" for a list of commands.\n");
		}
		// TODO save
	}

	private void cd(String path) {
		String newPath;
		if (path.startsWith("/"))
			newPath = path.substring(1);
		else
			newPath = currentPath + (currentPath.length() == 0 ? "" : "/") + path;
		Index.Group g = index.getGroup(newPath);
		if (g != null)
			currentPath = g.getPath().replace("/root", "");
		else
			ui.print(newPath + " does not exist.\n");
		currentPath = currentPath.startsWith("/") ? currentPath.substring(1) : currentPath;
	}

	private void ls(String args) {
		if (args.trim().length() == 0) {
			String[] files = index.getGroups(currentPath);
			for (String file : files)
				ui.print(file + "\n");
		} else if (args.trim().indexOf("-a") == 0) {
			ui.print(index.toString(currentPath));
		}
	}

	private void asker() {
		// TODO
	}

	private void notes() {
		// TODO
	}

	private void settings() {
		// TODO
	}

	private void importPath(String path) {
		// TODO
	}

	private void exportPath(String path) {
		// TODO
	}

	private void getStats() {
		// TODO
	}

	private void help() {
		// TODO
	}

	private void search(String s) {
		// TODO
	}

	private class Command {
		private String[] commands;
		public Command(String commands) {
			this.commands = commands.split(",");
		}
		public Command(String[] commands) {
			this.commands = commands;
		}
		public boolean match(String s) {
			boolean output = false;
			for (String command : commands) {
				if (s.indexOf(command) == 0)
					return true;
			}
			return false;
		}
		public String subString(String s) {
			for (String command : commands)
				if (s.indexOf(command) == 0)
					return s.substring(command.length()).trim();
			return null;
		}
	}

	private class defaultUI implements IUserInterface {

		public void defaultUI() {

		}

		@Override
		public void print(String s) {
			System.out.print(s);
		}

		@Override
		public void println(String s) {
			System.out.println(s);
		}

		@Override
		public String getInput() {
			Scanner scan = new Scanner(System.in);
			return scan.nextLine();
		}
	}

}
