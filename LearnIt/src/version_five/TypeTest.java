package version_five;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class TypeTest {

	@Before
	public void setup() {

	}

	@Test
	public void test1() {
		Type t = new Type("TestQuestion");
		Question test = t.newQuestion("thisShouldBeTheData");
		System.out.println(test.toString());
	}

	public class Test1234 implements Question {
		private String data;
		public Test1234(String s) {
			data = s;
		}
		@Override
		public LinkedList<Stat> getStats() {
			return null;
		}
		@Override
		public String ask(Engine e) {
			return null;
		}
		@Override
		public String[] getKeywords() {
			return null;
		}
		public String toString() {
			return data;
		}
	}

}
