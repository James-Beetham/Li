package version_five;

import java.lang.reflect.Constructor;

/**
 * Stores the class name of a class that implements Question. Type must be added
 * to type list in the Engine in order to load. Questions are constructed in the
 * FileManager and the question types are the first word on every line of the
 * file being loaded.
 * 
 * @author JamesBeetham
 *
 */
public class Type {
	private String type;
	public Type(String questionClassName) {
		this.type = questionClassName;
	}
	/**
	 * Returns a new instance of the class specified by the string this Type was
	 * constructed with.
	 * 
	 * @param info
	 *            String of text to construct the question with.
	 * @return new instance of the question specified by this type.
	 */
	public Question newQuestion(String info) {
		try {
			Question z = new TestQuestion("test123");
			System.out.println(type);
			Class<?> cl = Class.forName("TestQuestion");
			System.out.println("check 1");
			Constructor con = cl.getConstructor(info.getClass());
			return (Question) con.newInstance(info);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Class [" + type + "] does not exist");
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException("method does not exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String toString() {
		return type;
	}
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Type t = (Type) obj;
		return t.toString().equals(toString());
	}
}
