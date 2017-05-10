package version_one;

/**
 * Runs the program, controls what happens when, and manages user inputs (not
 * raw input however).
 * 
 * @author JamesBeetham
 */
public class Engine {
	private FileManager fm;

	public Engine(String path) {
		fm = new FileManager(path);
	}

	/**
	 * Starts running the program.
	 * 
	 * @return true if the program has started
	 */
	public boolean start() {
		// TODO
		return false;
	}

	/**
	 * Startup code for the entire program.
	 * 
	 * @param args
	 *            first index is the path files are stored
	 */
	public static void main(String[] args) {
		System.out.println("Running");
		Engine e = new Engine(args.length > 0 ? args[0] : "");
	}
}
