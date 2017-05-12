package version_five;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

/**
 * Basic test for the DefaultAnalyzer, could be expanded upon.
 * 
 * @author JamesBeetham
 *
 */
public class TestDefaultAnalyzer {

	@Test
	public void test() {
		long startTime = System.currentTimeMillis();

		String begin = "question:answer:";
		DefaultQuestion q0 = new DefaultQuestion(begin + "100,0");
		DefaultQuestion q1 = new DefaultQuestion(begin + "100,1");

		LinkedList<IQuestion> qs = new LinkedList<IQuestion>();
		for (int i = 0; i < 10; i++)
			qs.add(q0);
		DefaultAnalyzer da1 = new DefaultAnalyzer(.9, qs);
		assertEquals("Test when all previous are failed questions.", 100, da1.nextTime(q1));

		for (int i = 0; i < 10; i++) {
			qs.add(q0);
			qs.add(q1);
		}
		da1 = new DefaultAnalyzer(.1, qs);
		assertEquals("Test when first was 50/50", 102, da1.nextTime(q1));

		assertEquals("Test when question has not yet been asked (exposed)", 0,
				da1.nextTime(new DefaultQuestion("question:answer")));

		System.out.println(System.currentTimeMillis() - startTime);
	}

}
