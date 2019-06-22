package hr.fer.zemris.java.tecaj.hw3;

import java.security.InvalidParameterException;

/**
 * This program is support for working with complex numbers. Private arguments for complex number are real part, imaginary
 * part, angle and magnitude.
 * @author Vilim Staroveški
 * 
 */
public class ComplexNumber {
	
	/**
	 * Argument used for storing real part of complex number.
	 */
	private final double real;
	
	/**
	 * Argument used for storing imaginary part of complex number.
	 */
	private final double imaginary;
	
	/**
	 * Argument used for storing angle of complex number. Possible value is from 0 to 2*PI.
	 */
	private final double angle;
	
	/**
	 * Argument used for storing magnitude of complex number. Can only be positive number.
	 */
	private final double magnitude; 
	
	/**
	 * Creates a complex number from given arguments. 
	 * 
	 * @param real - {@code double} value representing real part of complex number.
	 * @param imaginary - {@code double} value representing imaginary part of complex number.
	 */
	public ComplexNumber(double real, double imaginary) {
	
		this.imaginary = imaginary;
		this.real = real;
		
		this.magnitude = Math.sqrt( Math.pow(real, 2) + Math.pow(imaginary, 2) );
		double angle = Math.atan2(imaginary, real);
		if (angle < 0) {
			this.angle = angle + 2*Math.PI;
		}
		else {
			this.angle = angle;
		}
	}
	
	/**
	 * Creates new {@code ComplexNumber} without imaginary part.
	 * 
	 * @param real - {@code double} value representing real part of complex number.
	 * @return new {@code ComplexNumber}
	 */
	public static ComplexNumber fromReal(double real) {
	
		return new ComplexNumber(real, 0);
	
	}
	
	/**
	 * Creates new {@code ComplexNumber} with only imaginary part.
	 * 
	 * @param imaginary - {@code double} value representing imaginary part of complex number.
	 * @return new {@code ComplexNumber}
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
	
		return new ComplexNumber(0, imaginary);

	}
	
	/**
	 * Creates {@code ComplexNumber} from given magnitude and angle. 
	 * 
	 * @param magnitude - {@code double} value representing magnitude of complex number.
	 * @param angle - {@code double} value representing angle of complex number. Value does not have to be from 0 to 2*PI
	 * @return new {@code ComplexNumber}
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		
		double real = Math.sqrt( Math.pow(magnitude, 2) / (1 + Math.pow(Math.tan(angle), 2)) );
		
		if (isInSecondQuadrant(angle) || isInThirdQuadrant(angle)) {
			real *= -1;
		}
		double imaginary = real * Math.tan(angle);
		return new ComplexNumber(real, imaginary);
	}
	
	/**
	 * Checks if the given {@code double} value is from PI to 3*PI/2.
	 * 
	 * @param angle - {@code double} that is checked.
	 * @return true if given {@code double} value is from PI to 3*PI/2. False otherwise.
	 */
	private static boolean isInThirdQuadrant(double angle) {
		
		angle = setUpAngle(angle);
		
		return angle >= Math.PI && angle < (3*Math.PI / 2);
	}
	/**
	 * Method that sets angle in to interval from 0 to 2*PI
	 * @param angle
	 * @return
	 */
	private static double setUpAngle(double angle) {
		
		while (angle > 2*Math.PI) {
			angle -= 2*Math.PI;
		}
		while (angle < 0) {
			angle += 2*Math.PI;
		}
		return angle;
	}

	/**
	 * Checks if the given {@code double} value is from PI/2 to PI.
	 * 
	 * @param angle - {@code double} that is checked.
	 * @return true if given {@code double} value is from PI/2 to PI. False otherwise.
	 */
	private static boolean isInSecondQuadrant(double angle) {
		
		angle = setUpAngle(angle);
		
		return angle > (Math.PI / 2) && angle <= Math.PI;
	}
	
	/**
	 * Parse the given string into a new {@code ComplexNumber} if the string is parsable. Parsable strings are simillar to
	 * this examples:
	 * "4", "i", "-i", "-2.4-0.2i", "i-2.43", "-i+45", "-i-3.2"
	 * White spaces in strings are not considered.
	 * 
	 * @param s - {@code String} that is tring to parse.
	 * @return new {@code ComplexNumber} if the given {@code String} was parsable.
	 * @throws {@code InvalidParameterException} if the given {@code String} was not parsable.
	 */
	public static ComplexNumber parse(String s) {
		
		String unknownNumber = s.trim();
		
		if (isNotImaginary(unknownNumber)) {
			//moguci oblici: "0.00", "-0.00"
			double real = tryToParse(unknownNumber);
			return new ComplexNumber(real, 0.0);
		}
		else {
			int indexOfImaginaryI = unknownNumber.indexOf("i");
			int numberOfOperators = numberOfOperators(unknownNumber);
			if (numberOfOperators == 0) {
				//moguci oblici: "i", "0.00i"

				if (indexOfImaginaryI == 0) {
					//moguci oblik: "i"
					return new ComplexNumber(0.0, 1.0);
				}
				else {
					//moguci oblik: "0.00i"
					String posibleNumber = unknownNumber.substring(0, indexOfImaginaryI);
					double imaginary = tryToParse(posibleNumber);
					return new ComplexNumber(0.0, imaginary);
				}
			}
			else if(numberOfOperators == 1) {
				//moguci oblici: "0.00 +- 0.00i", "0.00i +- 0.00", "-i", "i +- 0.00", "0.00 +- i"
				
				int indexOfOperator = (unknownNumber.contains("-")) ? unknownNumber.indexOf("-") :  unknownNumber.indexOf("+");
				
				if (indexOfOperator == 0) {
					//moguci oblik: "-i"
					return new ComplexNumber(0.0, -1.0);
				}
				else {
					//moguci oblik:  "0.00 +- 0.00i", "0.00i +- 0.00", "i +- 0.00", "0.00 +- i" 
					
					boolean firstArgIsNegative = false;
					String[] argumentsForComplexNumber = splitArgumentInTwo(unknownNumber, indexOfOperator, firstArgIsNegative);
					
					double real = tryToParse(argumentsForComplexNumber[0]);
					double imaginary = tryToParse(argumentsForComplexNumber[1]);
					
					return new ComplexNumber(real, imaginary);
				}
			}
			else if (numberOfOperators == 2) {
				//moguci oblici: "-0.00 +- 0.00i"  "-0.00i +- 0.00"  "-i +- 0.00"  "-0.00 +- i"
				
				unknownNumber = unknownNumber.substring(1); //izbaci minus ispred prvog broja
				
				int indexOfOperator = (unknownNumber.contains("-")) ? unknownNumber.indexOf("-") :  unknownNumber.indexOf("+");
				
				boolean firstArgIsNegative = true;
				String[] argumentsForComplexNumber = splitArgumentInTwo(unknownNumber, indexOfOperator, firstArgIsNegative);

				double real = tryToParse(argumentsForComplexNumber[0]);
				double imaginary = tryToParse(argumentsForComplexNumber[1]);
				
				return new ComplexNumber(real, imaginary);
			}
		}
		throw new InvalidParameterException("String is unparsable!");
	}
	
	/**
	 * Checks if the given {@code String} is a possible complex number.
	 * @param unknownNumber - {@code String} for what the check is performed.
	 * @return true if given {@code String} contains letter 'i' and therefore could be a string representation of
	 * a complex number. False otherwise.
	 */
	private static boolean isNotImaginary(String unknownNumber) {
		
		if (unknownNumber.contains("i")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Counts the number of operators in given {@code String}. Operators that are considered are: '+' and '-'.
	 * 
	 * @param unknownNubmer - {@code String} value in which counting is performed. 
	 * @return {@code int} value representing the number of operators in given {@code String}.
	 */
	private static int numberOfOperators(String unknownNubmer) {
		int count = 0;
		char c = 0;
		int length = unknownNubmer.length();
		for (int i = 0; i < length; i++) {
			c = unknownNubmer.charAt(i);
			if (c == '+' || c == '-') count++;
		}
		return count;
	}
	
	/**
	 * Method tries to parse given {@code String} in to a {@code double} value. If fails, prints error messege
	 * to standard output.
	 * 
	 * @param posibleNumber - {@code String} which the method try parsing.
	 * @return {@code double} value of the parsed {@code String}.
	 */
	private static double tryToParse(String posibleNumber) {
		double number = 0;
		try {
			number = Double.parseDouble(posibleNumber);
		} catch (NumberFormatException e) {
			System.err.println("Number is not parsable!");
		}
		return number;
	}
	
	/**
	 * Method splits given {@code String} in two parts divided by operator symbol. Operator symbol can be: '+' or '-'.
	 * 
	 * @param unknownNumber - {@code String} value that will be divided.
	 * @param indexOfOperator - {@code int} value of operator position in given {@code String}.
	 * @param firstArgIsNegative - {@code boolean} value which tells method to ignore first operator in {@code String}.
	 * @return {@code String[]} containing two parts of the original {@code String}.
	 */
	private static String[] splitArgumentInTwo(String unknownNumber, int indexOfOperator, boolean firstArgIsNegative) {
		
		String[] arguments = new String[2];
		char operator = unknownNumber.charAt(indexOfOperator);
		String firstPart = unknownNumber.substring(0, indexOfOperator).trim();
		String secondPart = unknownNumber.substring(indexOfOperator+1).trim();
		
		if (firstPart.indexOf("i") == 0) {
			//moguci oblik: "i +- 0.00"
			
			firstPart = "1" + firstPart;
			
		} 
		else if (secondPart.indexOf("i") == 0) {
			//moguci oblici: "0.00 +- i" 
			secondPart = "1" + secondPart;
		}
		
		//moguci oblici: "0.00 +- 0.00i", "0.00i +- 0.00",
		
		secondPart = operator + secondPart;
		
		if (firstArgIsNegative){
			firstPart = "-" + firstPart;
		}
		
		String imaginaryString = (firstPart.contains("i")) ? firstPart : secondPart;
		String realString = (!firstPart.contains("i")) ? firstPart : secondPart;
		
		int indexOfImaginaryI = imaginaryString.indexOf("i");
		
		imaginaryString = imaginaryString.substring(0, indexOfImaginaryI);
		
		arguments[0] = realString;
		arguments[1] = imaginaryString;
		
		return arguments;

	}
	
	/**
	 * Simple getter method.
	 * 
	 * @return real part of the {@code ComplexNumber}.
	 */
	public double getReal() {
		
		return real;
	}
	
	/**
	 * Simple getter method.
	 * 
	 * @return imaginary part of the {@code ComplexNumber}.
	 */
	public double getImaginary() {
		
		return imaginary;
	}
	
	/**
	 * Simple getter method.
	 * 
	 * @return magnitude of the {@code ComplexNumber}.
	 */
	public double getMagnitude() {

		return magnitude;
	}
	
	/**
	 * Simple getter method.
	 * 
	 * @return angle of the {@code ComplexNumber}.
	 */
	public double getAngle() {

		return angle;
	}
	
	/**
	 * Adds this {@code ComplexNumber} with another {@code ComplexNumber} given as a parameter.
	 * 
	 * @param - {@code ComplexNumber} second factor for sum.
	 * @return - {@code ComplexNumber} value representing the result.
	 */
	public ComplexNumber add(ComplexNumber c) {
		
		return new ComplexNumber(this.getReal() + c.getReal(), this.getImaginary() + c.getImaginary());
	}
	
	/**
	 * Subtracts given {@code ComplexNumber} from this {@code ComplexNumber}.
	 * 
	 * @param - {@code ComplexNumber} subtractor.
	 * @return - {@code ComplexNumber} value representing the subtraction.
	 */
	public ComplexNumber sub(ComplexNumber c) {
		
		return new ComplexNumber(this.getReal() - c.getReal(), this.getImaginary() - c.getImaginary());
	}
	
	/**
	 * Multiplies this {@code ComplexNumber} with another {@code ComplexNumber} given as a parameter.
	 * 
	 * @param - {@code ComplexNumber} second factor for multiplication.
	 * @return - {@code ComplexNumber} value representing the result.
	 */
	public ComplexNumber mul(ComplexNumber c) {
		
		double magnitude = this.getMagnitude() * c.getMagnitude();
		double angle = this.getAngle() + c.getAngle();
		return ComplexNumber.fromMagnitudeAndAngle(magnitude, angle);
	}
	
	/**
	 * Divides this {@code ComplexNumber} with another {@code ComplexNumber} given as a parameter.
	 * 
	 * @param - {@code ComplexNumber} dividor.
	 * @return - {@code ComplexNumber} value representing the result.
	 */
	public ComplexNumber div(ComplexNumber c) {
		
		double magnitude = this.getMagnitude() / c.getMagnitude();
		double angle = this.getAngle() - c.getAngle();
		return ComplexNumber.fromMagnitudeAndAngle(magnitude, angle);
	}
	
	/**
	 * Raises this {@code ComplexNumber} to the power given as argument.
	 * 
	 * @param n - the exponent.
	 * @return new {@code ComplexNumber} representing the result of raising to the exponent.
	 */
	public ComplexNumber power(int n) {
		
		if (n < 0) {
			throw new InvalidParameterException();
		}
		double magnitude = Math.pow(this.magnitude, n);
		double angle = n*this.angle;
		return ComplexNumber.fromMagnitudeAndAngle(magnitude, angle);
	}
	
	/**
	 * Calculates n-th roots of a {@code ComplexNumber} and returns them in a {@code ComplexNumber[]}.
	 * 
	 * @param n - number of root.
	 * @return {@code ComplexNumber[]} with n-th roots of a complex number.
	 */
	public ComplexNumber[] root(int n) {
		
		if (n <= 0) {
			throw new InvalidParameterException();
		}
		ComplexNumber[] roots = new ComplexNumber[n];
		
		double magnitude = Math.pow(this.magnitude, (1./n));
		double angle = 0;
		for (int k = 0; k < n; k++) {
			angle = this.angle + Math.PI*k*2;
			angle /= n;
			roots[k] =  ComplexNumber.fromMagnitudeAndAngle(magnitude, angle);
		}
		return roots;
	}
	
	@Override
	public String toString() {
		String stringRepresentation = (this.imaginary < 0) ? "("+this.real+" - "+this.imaginary*(-1)+"i)" : "("+this.real+" + "+ this.imaginary+"i)";
		return stringRepresentation;
	}
}
