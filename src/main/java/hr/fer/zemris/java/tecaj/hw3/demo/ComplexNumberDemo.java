package hr.fer.zemris.java.tecaj.hw3.demo;

import hr.fer.zemris.java.tecaj.hw3.ComplexNumber;
/**
 * This program is a demo program for representing the work of a {@link ComplexNumber} class.
 * @author Vilim Staroveški
 *
 */
public class ComplexNumberDemo {
	
	/**
	 * Method called when program starts. Does not uses command line arguments.
	 * @param args - ignored.
	 */
	public static void main(String[] args) {
		
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
		ComplexNumber c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57))
		.div(c2).power(3).root(2)[1];
		System.out.println("Angle is: "+c3.getAngle());
		System.out.println("Magnitude is: "+c3.getMagnitude());
		System.out.println("Real part is: "+c3.getReal());
		System.out.println("Imaginary part is: "+c3.getImaginary());
		
		System.out.println("\nString representation is: \n"+c3.toString());
		
	}
	
	

}
