import static org.junit.Assert.*;

import org.junit.Test;


public class MyTreeSetTest {

	@Test
	public void testSize() {
		MyTreeSet<Integer> Treez = new MyTreeSet<Integer>();
		int y=Treez.size();
		assertTrue("Is this equal", y==0);
	}

	@Test
	public void testClear() {
		MyTreeSet<Integer> Treez = new MyTreeSet<Integer>();
		Treez.add(5);
		Treez.clear();
		int y=Treez.size();
		assertTrue("Is this equal", y==0);
	}

	@Test
	public void testMyTreeSet() {
		MyTreeSet<Integer> Treez = new MyTreeSet<Integer>();
	}

	@Test
	public void testAddT() {
		MyTreeSet<Integer> Treez = new MyTreeSet<Integer>();
		Treez.add(5);
		int y=Treez.size();
		assertTrue("Is this equal", y==1);
	}

	@Test
	public void testIterator() {
		MyTreeSet<Integer> Treez = new MyTreeSet<Integer>();
	}

}
