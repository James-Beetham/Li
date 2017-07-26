package version_3;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestIndex {

	private Index index1;
	private Index index;
	private String[] stringArr1;

	@Before
	public void setUp() {
		String[] arr = {"group1", "-group2 3", " -group3", " -group4", "-group5", " -group6", "group7", ""};
		stringArr1 = arr.clone();
		index = new Index(arr);
	}

	@Test
	public void groupSetup() {
		assertEquals("test storing of groups and toString method of index",
				"group1 0\n-group2 3\n -group3 0\n -group4 0\n-group5 0\n -group6 0\ngroup7 0\n", index.toString());
	}

	@Test
	public void groupGetPath() {

	}

	@Test
	public void removeGroup() {
		String[] arr = {"group1", "-group2 3", " -group3", " -group4", "-group5", " -group6", "group7", ""};
		index = new Index(arr);
		Index.GroupIterator gi = index.groupIterator();
		gi.child();
		gi.remove();
		assertEquals("Tests the groupIterator remove method.", "group2 3\n-group3 0\n-group4 0\ngroup5 0\n-group6 0\ngroup7 0",
				index.toString().trim());
		index = new Index(arr);
		gi = index.groupIterator();
		gi.child();
		gi.child();
		gi.remove();
		assertEquals("Tests the groupIterator remove method.", "group1 0\n-group3 0\n-group4 0\n-group5 0\n -group6 0\ngroup7 0",
				index.toString().trim());

	}
}
