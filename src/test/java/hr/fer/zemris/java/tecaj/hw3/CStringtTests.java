package hr.fer.zemris.java.tecaj.hw3;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Class that contains tests for CString.
 * @author Vilim Staroveški
 *
 */
public class CStringtTests {

	/**
	 * Test that tests if constructor {@code CString(char[] data, int offset, int length)} returns valid CString
	 */
	@Test
	public void testConstructor1() {
		
		char[] data = {'V', 'i', 'l', 'i'};
		CString instance = new CString(data, 1, 2);
		
		assertTrue(instance.getData().length == 2);
		assertTrue(instance.getOffset() == 0);
		
		assertTrue(instance.toString().equals("il"));
	}
	
	/**
	 * Test that tests if constructor {@code CString(char[] data)} returns valid CString
	 */
	@Test
	public void testConstructor2() {
		
		char[] data = {'V', 'i', 'l', 'i'};
		CString instance = new CString(data);
		
		assertTrue(instance.getData().length == 4);
		assertTrue(instance.getOffset() == 0);
		
		assertTrue(instance.toString().equals("Vili"));
	}
	
	/**
	 * Test that tests if constructor {@code CString(String s)} returns valid CString
	 */
	@Test
	public void testConstructor3() {
		
		String test = new String("Vili");
		CString instance = new CString(test);
		
		assertTrue(instance.getData().length == 4);
		assertTrue(instance.getOffset() == 0);
		
		assertTrue(instance.toString().equals(test));
	}
	
	/**
	 * Test that tests if constructor {@code CString(CString original)} returns valid CString
	 */
	@Test
	public void testConstructor4() {
		
		char[] data = {'V', 'i', 'l', 'i'};
		CString test = new CString(data);
		
		CString instance = new CString(test);
		
		assertTrue(instance.getData().length == 4);
		assertTrue(instance.getOffset() == 0);
		
		assertTrue(instance.toString().equals(test.toString()));
	}
	
	/**
	 * Test that tests if the method length() returns correct number as a representation of CString length
 	 */
	@Test
	public void testLength() {
		
		char[] data = {'V', 'i', 'l', 'i'};
		CString test = new CString(data);
		
		assertTrue(test.length() == 4);
	}
	
	/**
	 * Test that tests if the method charAt() returns correct char.
	 */
	@Test
	public void testCharAt() {
		
		char[] data = {'V', 'i', 'l', 'i'};
		CString test = new CString(data);
		
		assertTrue(test.charAt(3) == 'i');
	}
	
	/**
	 * Test that tests if the method toString() returns correct String.
	 */
	@Test
	public void testToString() {
		
		char[] data = {'V', 'i', 'l', 'i'};
		CString test = new CString(data, 1, 3);
		
		assertTrue(test.toString().equals("ili"));
	}
	
	/**
	 * Test that tests if the method indexOf() returns correct index.
	 */
	@Test
	public void testIndexOf() {
		
		char[] data = {'V', 'i', 'l', 'i'};
		CString test = new CString(data, 0, 3);
		
		assertTrue(test.indexOf('V') == 0);
		assertTrue(test.indexOf('t') == -1);
	}
	
	/**
	 * Test that tests if the method startsWith() returns correct boolean.
	 */
	@Test
	public void testStartsWith() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data, 1, 5);
		
		CString test2 = new CString(data, 1, 3);
		
		assertTrue(test.startsWith(test2));
		
		CString test3 = new CString(data, 2, 2);
		
		assertFalse(test.startsWith(test3));
	}
	
	/**
	 * Test that tests if the method endsWith() returns correct boolean.
	 */
	@Test
	public void testEndsWith() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data, 1, 5);
		
		CString test2 = new CString(data, 4, 2);
		
		assertTrue(test.endsWith(test2));
		
		CString test3 = new CString(data, 5, 2);
		
		assertFalse(test.endsWith(test3));
	}
	
	/**
	 * Test that tests if the method contains() returns correct boolean.
	 */
	@Test
	public void testContains() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data, 0, 9);
		CString test2 = new CString(data, 1, 5);
		CString test3 = new CString(data, 5, 5);
		
		assertTrue(test.contains(test2));
		assertFalse(test.contains(test3));
	}
	
	/**
	 * Test that tests if the method subString() returns correct CString.
	 */
	@Test
	public void testSubString() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data);
		CString test2 = test.subString(2, 5);
		
		assertTrue(test2.toString().equals("lim"));
	}

	/**
	 * Test that tests if the method left() returns correct CString.
	 */
	@Test
	public void testLeft() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data);
		
		assertTrue(test.left(4).toString().equals("Vili"));
	}
	
	/**
	 * Test that tests if the method right() returns correct CString.
	 */
	@Test
	public void testRight() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data);
		
		assertTrue(test.right(4).toString().equals("test"));
	}
	
	/**
	 * Test that tests if the constructor returns correct CString.
	 */
	@Test
	public void testCString() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data);
		
		char[] data2 = {' ', 't', 'e', 's', 't'};
		CString test2 = new CString(data2);
		
		test = test.add(test2);
		
		assertTrue(test.toString().equals("Vilim test test"));
		
		test = test.add(test);
		
		assertTrue(test.toString().equals("Vilim test testVilim test test"));
		
	}
	
	/**
	 * Test that tests if the method add() returns expected CString.
 	 */
	@Test
	public void testAddC() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data);
		
		char[] data2 = {};
		CString test2 = new CString(data2);
		
		assertTrue(test.add('!').toString().equals("Vilim test!"));
		
		assertTrue(test2.add('c').toString().equals("c"));
	}
	
	/**
	 * Test that tests if the method replaceAll() returns expected CString. For characters as parameters.
 	 */
	@Test
	public void testReplaceAllC() {
		
		char[] data = {'V', 'i', 'l', 'i', 'm', ' ', 't', 'e', 's', 't'};
		CString test = new CString(data);
		
		assertTrue(test.replaceAll('i', 'a').toString().equals("Valam test"));
		
		assertTrue(test.replaceAll('a', 'r').toString().equals("Vilim test"));
	}
	
	/**
	 * Test that tests if the method replaceAll() returns expected CString. For CStrings as parameters.
 	 */
	@Test
	public void testReplaceAllS() {
		
		char[] data = {'A', 'b', 'A', 'a', 'B', 'a', 'B', 'a', 'b', 'b'};
		CString test = new CString(data);
		char[] data2 = {'a'};
		CString oldStr = new CString(data2);
		char[] data3 = {'A', 'b', 'A'};
		CString newStr = new CString(data3);
		assertTrue(test.replaceAll(oldStr, newStr).toString().equals("AbAAbABAbABAbAbb"));
		
	}
}
