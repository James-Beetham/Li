package version_five;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class TypeTest {

	@Before
	public void setup() {

	}

	@Test
	public void testToString() {
		String msg = "Test toString method.";

		String name = "version_five.TypeTest$Test1234";
		Type t = new Type(name);

		assertEquals(msg, name, t.toString());
	}

	@Test
	public void testNewQuestion() {
		String msg = "Type generates new question.";

		String type_msg = "This should be the string returned and stuff";
		Type t = new Type("TypeTest$Test1234");
		IQuestion q = t.newQuestion(type_msg);

		assertEquals(msg, type_msg, q.toString());
	}

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
	}
}
