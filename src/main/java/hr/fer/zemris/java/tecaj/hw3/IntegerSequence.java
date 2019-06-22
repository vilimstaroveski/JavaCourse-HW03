package hr.fer.zemris.java.tecaj.hw3;

import java.security.InvalidParameterException;
import java.util.Iterator;

/**
 * Class that represents a sequence of int values. Private arguments are start,
 * end and step of the sequence.
 * 
 * @author Vilim Staroveški
 *
 */
public class IntegerSequence implements Iterable<Integer> {

	/**
	 * First int value.
	 */
	private int start;

	/**
	 * Last int value.
	 */
	private int end;

	/**
	 * Step of the sequence.
	 */
	private int step;

	/**
	 * Creates a sequence from parameters.
	 * 
	 * @param start
	 *            - first int value.
	 * @param end
	 *            - last int value.
	 * @param step
	 *            - step of the sequence.
	 */
	public IntegerSequence(int start, int end, int step) {

		if (!validParameters(start, end, step)) {
			throw new InvalidParameterException();
		}

		this.start = start;
		this.end = end;
		this.step = step;
	}

	/**
	 * Method that checks are the parameters valid and compatible for sequence.
	 * 
	 * @param start
	 *            - first int value.
	 * @param end
	 *            - last int value.
	 * @param step
	 *            - step of the sequence.
	 * @return true if arguments are valid. False otherwise.
	 */
	private boolean validParameters(int start, int end, int step) {

		if (step > 0) {

			return end > start;
		}

		else if (step < 0) {

			return end < start;
		}

		return false;
	}

	/**
	 * Factory method that returns new iterator for IntegerSequence
	 */
	@Override
	public Iterator<Integer> iterator() {

		return new IteratorForIntegerSequence();
	}

	/**
	 * Nested class used for iterating trough IntegerSequence.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	private class IteratorForIntegerSequence implements Iterator<Integer> {

		/**
		 * Int value of current integer in sequence.
		 */
		private int currentInteger;

		/**
		 * Int value of how many int values are left to iterate trough.
		 */
		private int howManyLeft;

		/**
		 * Creates new iterator that can iterate trough this IntegerSequence
		 */
		public IteratorForIntegerSequence() {

			this.currentInteger = start;
			this.howManyLeft = (end - start) / step + 1;
		}

		/**
		 * Method that checks if there are more int values left.
		 * 
		 * @return true if there are, false otherwise.
		 */
		@Override
		public boolean hasNext() {

			return howManyLeft > 0;
		}

		/**
		 * Returns next int value in the sequence.
		 * 
		 * @return next int value in the sequence if available.
		 */
		@Override
		public Integer next() {

			if (howManyLeft <= 0) {
				throw new RuntimeException("There are no more elements!");
			}

			int value = currentInteger;
			currentInteger += step;
			howManyLeft--;
			return value;
		}

	}

}
