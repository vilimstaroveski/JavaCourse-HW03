package hr.fer.zemris.java.tecaj.hw3;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that contains unit tests for {@code ComplexNumber}.
 * 
 * @author Vilim Staroveški
 *
 */
public class ComplexNumberTests {
	
	/**
	 * Checks if the static method {@code fromReal(int a)} returns the valid {@code ComplexNumber}.
	 */
	@Test
	public void testFromReal() {
		
		ComplexNumber z = ComplexNumber.fromReal(5);
		
		assertTrue(Math.abs(5 - z.getReal()) < 0.0000001);
		assertTrue(Math.abs(0 - z.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(0 - z.getAngle()) < 0.0000001);
		assertTrue(Math.abs(5 - z.getMagnitude()) < 0.0000001);
	}
	
	/**
	 * Checks if the static method {@code fromImaginary(int a)} returns the valid {@code ComplexNumber}.
	 */
	@Test
	public void testFromImaginary() {
		
		ComplexNumber z = ComplexNumber.fromImaginary(5);
		
		assertTrue(Math.abs(5 - z.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(0 - z.getReal()) < 0.0000001);
		assertTrue(Math.abs(Math.PI/2 - z.getAngle()) < 0.0000001);
		assertTrue(Math.abs(5 - z.getMagnitude()) < 0.000000001);
	}
	
	/**
	 * Checks if the static method {@code fromMagnitudeAndAngle(double m, double a)} returns the valid {@code ComplexNumber}.
	 */
	@Test
	public void testFromMagnitudeAndAngle() {
		
		ComplexNumber z = ComplexNumber.fromMagnitudeAndAngle(5, Math.PI/4);
		
		double expectedReal = 5*Math.sqrt(2)/2;
		double expectedImag = 5*Math.sqrt(2)/2;
		assertTrue(Math.abs(expectedReal - z.getReal()) < 0.0000001);
		assertTrue(Math.abs(expectedImag - z.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(5 - z.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(Math.PI/4 - z.getAngle()) < 0.0000001);
		
		
		ComplexNumber z2 = ComplexNumber.fromMagnitudeAndAngle(5, 7*Math.PI/4);
		
		double expectedReal2 = 5*Math.sqrt(2)/2;
		double expectedImag2 = -5*Math.sqrt(2)/2;
		assertTrue(Math.abs(expectedReal2 - z2.getReal()) < 0.0000001);
		assertTrue(Math.abs(expectedImag2 - z2.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(5 - z2.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(7*Math.PI/4 - z2.getAngle()) < 0.00000001);
		
		ComplexNumber z3 = ComplexNumber.fromMagnitudeAndAngle(1, 7*Math.PI/6 + 2*Math.PI);
		
		double expectedReal3 = -Math.sqrt(3)/2;
		double expectedImag3 = -0.5;
		assertTrue(Math.abs(expectedReal3 - z3.getReal()) < 0.0000001);
		assertTrue(Math.abs(expectedImag3 - z3.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(1 - z3.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(7*Math.PI/6 - z3.getAngle()) < 0.00000001);
		
		ComplexNumber z4 = ComplexNumber.fromMagnitudeAndAngle(1, 11*Math.PI/6);
		
		double expectedReal4 = Math.sqrt(3)/2;
		double expectedImag4 = -0.5;
		assertTrue(Math.abs(expectedReal4 - z4.getReal()) < 0.0000001);
		assertTrue(Math.abs(expectedImag4 - z4.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(1 - z4.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(11*Math.PI/6 - z4.getAngle()) < 0.00000001);
	}
	
	/**
	 * Checks if the static method {@code parse(String a)} returns the valid {@code ComplexNumber} for different sorts of inputs.
	 */
	@Test
	public void testParse() {
		
		ComplexNumber z1 = ComplexNumber.parse("3-4i");
		
		double expectedMagnitude1 = Math.sqrt(Math.pow(3, 2) + Math.pow(-4, 2));
		double expectedAngle1 = Math.atan2(-4, 3) + 2*Math.PI;
		
		assertTrue(Math.abs(3 - z1.getReal()) < 0.0000001);
		assertTrue(Math.abs(-4 - z1.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude1 - z1.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle1 - z1.getAngle()) < 0.0000001);
		
		ComplexNumber z2 = ComplexNumber.parse(" -2.13 - 5.22i ");
		
		double expectedMagnitude2 = Math.sqrt(Math.pow(-2.13, 2) + Math.pow(-5.22, 2));
		double expectedAngle2 = Math.atan2(-5.22, -2.13) + 2*Math.PI;
		
		assertTrue(Math.abs(-2.13 - z2.getReal()) < 0.0000001);
		assertTrue(Math.abs(-5.22 - z2.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude2 - z2.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle2 - z2.getAngle()) < 0.0000001);
		
		ComplexNumber z3 = ComplexNumber.parse("3i -4");
		
		double expectedMagnitude3 = Math.sqrt(Math.pow(3, 2) + Math.pow(-4, 2));
		double expectedAngle3 = Math.atan2(3, -4);
		
		assertTrue(Math.abs(-4 - z3.getReal()) < 0.0000001);
		assertTrue(Math.abs(3 - z3.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude3 - z3.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle3 - z3.getAngle()) < 0.0000001);
		
		ComplexNumber z4 = ComplexNumber.parse("    3.2i+    0.612345    ");
		
		double expectedMagnitude4 = Math.sqrt(Math.pow(3.2, 2) + Math.pow(0.612345, 2));
		double expectedAngle4 = Math.atan2(3.2, 0.612345);
		
		assertTrue(Math.abs(0.612345 - z4.getReal()) < 0.0000001);
		assertTrue(Math.abs(3.2 - z4.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude4 - z4.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle4 - z4.getAngle()) < 0.0000001);
		
		ComplexNumber z5 = ComplexNumber.parse(" -  i");
		
		double expectedMagnitude5 = Math.sqrt(Math.pow(0, 2) + Math.pow(-1, 2));
		double expectedAngle5 = Math.atan2(-1, 0) + 2*Math.PI;
		
		assertTrue(Math.abs(0 - z5.getReal()) < 0.0000001);
		assertTrue(Math.abs(-1 - z5.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude5 - z5.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle5 - z5.getAngle()) < 0.0000001);
		
		ComplexNumber z6 = ComplexNumber.parse("i  +5");
		
		double expectedMagnitude6 = Math.sqrt(Math.pow(5, 2) + Math.pow(1, 2));
		double expectedAngle6 = Math.atan2(1, 5);
		
		assertTrue(Math.abs(5 - z6.getReal()) < 0.0000001);
		assertTrue(Math.abs(1 - z6.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude6 - z6.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle6 - z6.getAngle()) < 0.0000001);
		
		ComplexNumber z7 = ComplexNumber.parse("-  0.3i  + 5.3");
		
		double expectedMagnitude7 = Math.sqrt(Math.pow(5.3, 2) + Math.pow(-0.3, 2));
		double expectedAngle7 = Math.atan2(-0.3, 5.3)+ 2*Math.PI;
		
		assertTrue(Math.abs(5.3 - z7.getReal()) < 0.0000001);
		assertTrue(Math.abs(-0.3 - z7.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude7 - z7.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle7 - z7.getAngle()) < 0.0000001);
	}
	
	/**
	 * Checks if the method {@code add(ComplexNumber other)} returns the valid {@code ComplexNumber} as a result of adding.
	 */
	@Test
	public void testAdd() {
		
		ComplexNumber z1 = new ComplexNumber(3, 2);
		ComplexNumber z2 = new ComplexNumber(-2, 4);
		ComplexNumber z3 = z1.add(z2);
		
		double expectedMagnitude = Math.sqrt(Math.pow((3-2), 2) + Math.pow((2+4), 2));
		double expectedAngle = Math.atan2((2+4), (3-2));
		
		assertTrue(Math.abs((3-2) - z3.getReal()) < 0.0000001);
		assertTrue(Math.abs((2+4) - z3.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude - z3.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle - z3.getAngle()) < 0.0000001);
	}
	
	/**
	 * Checks if the method {@code sub(ComplexNumber other)} returns the valid {@code ComplexNumber}as a result of subtraction.
	 */
	@Test
	public void testSub() {
		
		ComplexNumber z1 = new ComplexNumber(-4, 5.5);
		ComplexNumber z2 = new ComplexNumber(2, -1.1);
		ComplexNumber z3 = z1.sub(z2);
		
		double expectedMagnitude = Math.sqrt(Math.pow((-4-2), 2) + Math.pow((5.5+1.1), 2));
		double expectedAngle = Math.atan2((5.5+1.1), (-4-2));
		
		assertTrue(Math.abs((-4-2) - z3.getReal()) < 0.0000001);
		assertTrue(Math.abs((5.5+1.1) - z3.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(expectedMagnitude - z3.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(expectedAngle - z3.getAngle()) < 0.0000001);
	}
	
	/**
	 * Checks if the method {@code mul(ComplexNumber other)} returns the valid {@code ComplexNumber} as a result of multiplication.
	 */
	@Test
	public void testMul() {
		
		ComplexNumber z1 = new ComplexNumber(2.3, -1.3);
		ComplexNumber z2 = new ComplexNumber(2.3, -7);
		ComplexNumber z3 = z1.mul(z2);
		
		double expectedMagnitude = z1.getMagnitude() * z2.getMagnitude();
		double expectedAngle = z1.getAngle() + z2.getAngle();
		
		ComplexNumber zExpected = ComplexNumber.fromMagnitudeAndAngle(expectedMagnitude, expectedAngle);
 		
		assertTrue(Math.abs(zExpected.getReal() - z3.getReal()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getImaginary() - z3.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getMagnitude() - z3.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getAngle() - z3.getAngle()) < 0.0000001);
	}
	
	/**
	 * Checks if the method {@code div(ComplexNumber other)} returns the valid {@code ComplexNumber} as a result of dividing.
	 */
	@Test
	public void testDiv() {
		
		ComplexNumber z1 = new ComplexNumber(2.3, -1.3);
		ComplexNumber z2 = new ComplexNumber(2.3, -7);
		ComplexNumber z3 = z1.div(z2);
		
		double expectedMagnitude = z1.getMagnitude() / z2.getMagnitude();
		double expectedAngle = z1.getAngle() - z2.getAngle();
		
		ComplexNumber zExpected = ComplexNumber.fromMagnitudeAndAngle(expectedMagnitude, expectedAngle);
 		
		assertTrue(Math.abs(zExpected.getReal() - z3.getReal()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getImaginary() - z3.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getMagnitude() - z3.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getAngle() - z3.getAngle()) < 0.0000001);
	}
	
	/**
	 * Checks if the method {@code power(int n)} returns the valid {@code ComplexNumber} as a result.
	 */
	@Test
	public void testPower() {
		
		ComplexNumber z1 = new ComplexNumber(2.3, -1.3);
		ComplexNumber z2 = z1.power(2);
		//kvadriranje z1 množenjem sa samim sobom
		ComplexNumber zExpected = z1.mul(z1);
 		
		assertTrue(Math.abs(zExpected.getReal() - z2.getReal()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getImaginary() - z2.getImaginary()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getMagnitude() - z2.getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(zExpected.getAngle() - z2.getAngle()) < 0.0000001);
	}
	
	/**
	 * Checks if the method {@code roots(int n)} returns the valid {@code ComplexNumber} as a result.
	 */
	@Test
	public void testRoot() {
		
		ComplexNumber z1 = new ComplexNumber(-2, 1);
		ComplexNumber[] z2 = z1.root(2);

		ComplexNumber zExpected1 = ComplexNumber.fromMagnitudeAndAngle(Math.sqrt(z1.getMagnitude()), z1.getAngle()/2);
		ComplexNumber zExpected2 = ComplexNumber.fromMagnitudeAndAngle(Math.sqrt(z1.getMagnitude()), (z1.getAngle()+2*Math.PI)/2);
		
		assertTrue(Math.abs(zExpected1.getReal() - z2[0].getReal()) < 0.0000001);
		assertTrue(Math.abs(zExpected1.getImaginary() - z2[0].getImaginary()) < 0.0000001);
		assertTrue(Math.abs(zExpected1.getMagnitude() - z2[0].getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(zExpected1.getAngle() - z2[0].getAngle()) < 0.0000001);
		
		assertTrue(Math.abs(zExpected2.getReal() - z2[1].getReal()) < 0.0000001);
		assertTrue(Math.abs(zExpected2.getImaginary() - z2[1].getImaginary()) < 0.0000001);
		assertTrue(Math.abs(zExpected2.getMagnitude() - z2[1].getMagnitude()) < 0.0000001);
		assertTrue(Math.abs(zExpected2.getAngle() - z2[1].getAngle()) < 0.0000001);
	}
	
}
