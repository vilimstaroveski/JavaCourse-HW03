package hr.fer.zemris.java.tecaj.hw3;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Class that contains tests for testing IntegerSequence.
 * 
 * @author Vilim Staroveški
 *
 */
public class IntegerSequenceTest {

	/**
	 * Tests if constructor creates non null value of IntegerSequence
	 */
	@Test
	public void testIntegerSequence() {
		
		IntegerSequence seq = new IntegerSequence(1, 11, 2);
		assertNotNull(seq);
	}
	
	/**
	 * Tests if iterator has the correct iteration trough IntegerSequence.
	 */
	@Test
	public void testIterator1() {
		
		IntegerSequence seq = new IntegerSequence(1, 11, 2);
		
		int[] array = new int[6];
		int j=0;
		
		for (int i : seq) {
			array[j++] = i;
		}
		
		assertEquals(7, array[3]);
		assertEquals(11, array[5]);
		assertTrue(array.length == 6);
	}
	
	/**
	 * Tests if iterator has the correct iteration trough IntegerSequence.
	 */
	@Test
	public void testIterator2() {
		
		IntegerSequence seq = new IntegerSequence(1, 6, 3);
		int j=0;
		
		int[] array1 = new int[2];
		for (int i : seq) {
			array1[j++] = i;
		}
		
		j = 0;
		
		int[] array2 = new int[2];
		for (int i : seq) {
			array2[j++] = i;
		}
		
		assertEquals(1, array1[0]);
		assertEquals(4, array1[1]);
		
		assertEquals(1, array2[0]);
		assertEquals(4, array2[1]);
		
		int[] expected = {1, 4};
		assertArrayEquals(expected, array1);
		assertArrayEquals(expected, array2);
		
	}
	
	/**
	 * Tests if iterator has the correct iteration trough IntegerSequence.
	 */
	@Test
	public void testIterator3() {
		
		IntegerSequence seq = new IntegerSequence(4, 10, 2);
		
		
		int count = 0;
		
		for (int i : seq) {
			for (int j : seq) {
				for (int k : seq) {
					count++;
				}
			}
		}
		
		assertEquals(4*4*4, count);
	}
}
