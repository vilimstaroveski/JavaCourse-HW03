package hr.fer.zemris.java.tecaj.hw3.demo;

import hr.fer.zemris.java.tecaj.hw3.CString;
/**
 * Class used as demonstration of CString.
 * 
 * @author Vilim Staroveški
 *
 */
public class CStringDemo {

	/**
	 * Method called when program starts. Does not uses command line arguments.
	 * @param args - ignored.
	 */
	public static void main(String[] args) {
		
		CString oldStr = new CString("ab");
		CString newStr = new CString("abab");
		CString original = new CString("ababab");
		CString test = original.replaceAll(oldStr, newStr);
		
		System.out.println("In string \""+original.toString()+"\" replacing substring \""+oldStr.toString()+"\""
				+ " with new substring \""+newStr.toString()+"\". Result is: \""+test.toString()+"\".");
	}

}
