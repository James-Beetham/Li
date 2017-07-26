package version_5;

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

	/**
	 * Constructs a new type with parameter class name. The class name locator
	 * is in the format: package.class[$nestedClass1][$nestedClass2]... If no
	 * package is put, will assume to be in this package (version_five).
	 * 
	 * @param questionClassName
	 *            name of the question class, note that the format will be the
	 *            same as that used in Class.forName(String s).
	 */
	public Type(String questionClassName) {
		if (questionClassName == null)
			throw new IllegalArgumentException("String questionClassName can not be null.");
		if (!questionClassName.contains("."))
			questionClassName = "version_five." + questionClassName;
		try {
			Class.forName(questionClassName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Class specified does not exists: " + questionClassName);
		}
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
	public IQuestion newQuestion(String info) {
		if (info == null)
			throw new IllegalArgumentException("String info can not be null.");
		try {
			Class<?> cl = Class.forName(type);
			Constructor<?> con = cl.getConstructor(info.getClass());
			return (IQuestion) con.newInstance(info);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Class [" + type + "] does not exist");
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException("method does not exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Type t = (Type) obj;
		return t.toString().equals(toString());
	}
}
