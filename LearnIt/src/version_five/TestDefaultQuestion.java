package version_five;

import org.junit.Before;
import org.junit.Test;

public class TestDefaultQuestion {
	private DefaultQuestion dq;

	@Before
	public void setup() {
		dq = new DefaultQuestion("question:answer:(100,3.14,200,0.0");
	}

	@Test
	public void test1() {

	}
}
