package version_five;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * Basic tests for the Type class, could be expanded on.
 * 
 * @author JamesBeetham
 *
 */
public class TestType {

	@Before
	public void setup() {

	}

	@Test
	public void testToString() {
		String msg = "Test toString method.";

		String name = "version_five.TestType$Test1234";
		Type t = new Type(name);

		assertEquals(msg, name, t.toString());
	}

	@Test
	public void testNewQuestion() {
		String msg = "Type generates new question.";

		String expected = "version_five.TestType$Test1234";
		Type t = new Type("TestType$Test1234");
		IQuestion q = t.newQuestion(expected);
		String actual = q.toString();

		assertEquals(msg, expected, actual.substring(0, actual.indexOf("@")));
	}

	/**
	 * Test class for a basic question.
	 * 
	 * @author JamesBeetham
	 *
	 */
	public static class Test1234 implements IQuestion {
		private String data;

		public Test1234(String s) {
			data = s;
		}

		@Override
		public LinkedList<Stat> getStats() {
			return null;
		}

		@Override
		public void ask(IEngine e) {
		}

		@Override
		public String[] getKeywords() {
			return null;
		}

		public String getString() {
			return data;
		}

		@Override
		public int compareTo(IQuestion o) {
			return 0;
		}

		@Override
		public long getNextTime() {
			return 0;
		}

		@Override
		public void setNextTime(long time) {

		}
	}
}
