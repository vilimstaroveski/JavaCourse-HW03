package hr.fer.zemris.java.tecaj.hw3.demo;

import hr.fer.zemris.java.tecaj.hw3.IntegerSequence;
/**
 * Class used for demonstration of IntegerSequence. 
 * 
 * @author Vilim Staroveški
 *
 */
public class IntegerSequenceDemo {
	
	/**
	 * Method called when program starts. Does not uses command line arguments.
	 * @param args - ignored.
	 */
	public static void main(String[] args) {
		IntegerSequence range = new IntegerSequence(1, 11, 2);
		for(int i : range) {
			for(int j : range) {
				System.out.println("i="+i+", j="+j);
			}
		}
		
	}

}
