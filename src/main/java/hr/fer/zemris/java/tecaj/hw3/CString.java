package hr.fer.zemris.java.tecaj.hw3;


import java.security.InvalidParameterException;

/**
 * Class that represents support for working with {@code String}s. It represents unmodifiable strings which
 * shares the character arrays.
 * 
 * @author Vilim Staroveški
 *
 */
public class CString {
	
	/**
	 * Private argument for storing {@code char} values.
	 */
	private char[] data;
	
	/**
	 * Private argument that represents offset from begin of own {@code char[]}.
	 */
	private int offset;
	
	/**
	 * Private argument that represents how many {@code char} is included from private {@code char[]}.
	 */
	private int length;
	
	/**
	 * Simple getter method. 
	 * @return private {@code char[]} value.
	 */
	public char[] getData() {
		return data;
	}
	
	/**
	 * Simple getter method. 
	 * @return private {@code int} value as offset.
	 */
	public int getOffset() {
		return offset;
	}
	
	/**
	 * Simple getter method. 
	 * @return private {@code int} value as length.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Creates new {@code CString} from given arguments. If arguments are not valid, throws exception.
	 * 
	 * @param data - {@code char[]} representing the array of char values for this CString.
	 * @param offset - offset from beginig of parameter array. Must be >= 0.
	 * @param length - {@code int} value that tells how many characters is taken in consideration. Must be >= 0.
	 * @throws InvalidParameterException if length or offset are lesser then 0; or their sum bigger then length of {@code char[]}. 
	 */
	public CString(char[] data, int offset, int length) {
		
		
		if (offset < 0 || length < 0) {
			throw new InvalidParameterException();
		}
		
		if (data.length < offset+length) {
			throw new IndexOutOfBoundsException();
		}		
		
		char[] newData = data;
		
		if (offset > 0) {
		
			newData = new char[length];
			System.arraycopy(data, offset, newData, 0, length);
		}
		
		this.data = newData;
		this.offset = 0;
		this.length = length;
	}
	
	/**
	 * Creates new {@code CString} from given argument {@code char[]}.
	 * 
	 * @param data {@code char[]} that is going to be a array of characters for new {@code CString}.
	 */
	public CString(char[] data) {
		
		this(data, 0, data.length);
	}
	
	/**
	 * Creates new {@code CString} as a copy of given {@code CString}.
	 * 
	 * @param original - {@code CString} value from the copy is made of.
	 */
	public CString(CString original) {
		
		char[] newData = original.getData();
		int length = original.getLength();
		
		if (original.offset > 0) {
			
			newData = new char[length];
			System.arraycopy(original.getData(), original.getOffset() , newData, 0, length); 
		}
		
		this.data = newData;
		this.length = length;
		this.offset = 0;
	}
	
	/**
	 * Creates new {@code CString} from given {@code String}
	 * @param s - given {@code String}
	 */
	public CString(String s) {
		
		this(s.toCharArray());
	}
	
	/**
	 * Returns the length of this {@code CString}.
	 * @return
	 */
	public int length() {
		
		return length;
	}
	
	/**
	 * Returns {@code char} at index given as parameter.
	 * 
	 * @param index - {@code int} value representing index in this {@code CString}
	 * @return {@code char} at index
	 */
	public char charAt(int index) {
		
		if (index < 0 || index >= this.length) {
			throw new IndexOutOfBoundsException();
		}
		return data[this.offset + index];
	}
	
	/**
	 * Returns the {@code String} representation of {@code CString}.
	 * 
	 */
	@Override
	public String toString() {
		
		char[] newData = new char[length];
		
		for (int i = 0; i < length; i++) {
			newData[i] = this.data[i+offset];
		}
		
		return new String(newData);
	}
	
	/**
	 * Returns the index within this string of the first occurrence of the specified character.
	 * 
	 * @param c - a character 
	 * @return the index of the first occurrence of the character in the character sequence represented by this 
	 *  object, or -1 if the character does not occur.
	 */
	public int indexOf(char c) {
		
		int endIndex = offset + length;
		for (int i = 0; i < endIndex; i++) {
			if (data[i] == c) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Tests if this string starts with the specified prefix.
	 * 
	 * @param s - the prefix.
	 * @return true if the character sequence represented by the argument is a prefix of
	 *  the character sequence represented by this string; false otherwise.
	 */
	public boolean startsWith(CString s) {
		
		int endIndex = s.getLength();
		if (this.length < s.getLength()) {
			return false;
		}
		for (int i = 0; i < endIndex; i++) {
			if (this.charAt(i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Tests if this string ends with the specified suffix.
	 * 
	 * @param s - a suffix
	 * @return true if the character sequence represented by the argument is 
	 *  a suffix of the character sequence represented by this object; false otherwise.
	 */
	public boolean endsWith(CString s) {
		
		int endIndex = s.getLength();
		int startPositionInOriginalCString = this.length - s.getLength();
		if (startPositionInOriginalCString < 0) {
			return false;
		}
		for (int i = 0; i < endIndex; i++) {
			
			if (this.charAt(i + startPositionInOriginalCString) != s.charAt(i)) {
				
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if and only if this string contains the specified sequence of char values.
	 * 
	 * @param s - the sequence to search for
	 * @return true if this string contains s, false otherwise
	 */
	public boolean contains(CString s) {
		
		char beginingChar = s.charAt(0);
		int endIndex = this.length - s.getLength();
		for (int i = 0; i <= endIndex; i++) {
			
			if (this.charAt(i) == beginingChar) {
				
				CString subString = this.subString(i, i + s.length);
				if (subString.startsWith(s)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 *  Returns a string that is a substring of this string.
	 *  The substring begins at the specified beginIndex and extends to the character at index endIndex - 1.
	 * @param startIndex - the beginning index, inclusive
	 * @param endIndex - the ending index, exclusive
	 * @return the specified substring.
	 */
	public CString subString(int startIndex, int endIndex) {
		
		if (startIndex < 0 || endIndex < startIndex) {
			throw new IndexOutOfBoundsException();
		}
		int length = endIndex - startIndex;
		CString subString = new CString(this.data, startIndex, length);
		return subString;
	}
	
	/**
	 * Returns substring of this CString from 0 to n-th - 1 character.
	 * 
	 * @param n - end index
	 * @return wanted substring in this CString
	 */
	public CString left(int n) {
		
		if (n > this.length || n < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		return this.subString(0, n);
	}
	
	/**
	 * Returns substring of this CString from n-th to last character.
	 * 
	 * @param n - start index
	 * @return wanted substring in this CString
	 */
	public CString right(int n) {
		
		if (n > this.length || n < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		return this.subString(length - n, length);
	}
	
	/**
	 * Returns CString concatenated with given CString as parameter.
	 * 
	 * @param s - CString we want to concatenate
	 * @return new CString as joined this and given CString
	 */
	public CString add(CString s) {
		
		int length = this.length + s.getLength();
		char[] data = new char[length];
		int position = 0; 
		
		for (int i = 0; i < this.length; position++, i++) {
			data[position] = this.charAt(i);
		}
		
		for (int i = 0; i < s.getLength(); position++, i++) {
			data[position] = s.charAt(i);
		}
		
		return new CString(data);
	}
	
	/**
	 * Returns CString concatenated with given char as parameter.
	 * 
	 * @param c - char we want to concatenate
	 * @return new CString as joined this and given char
	 */
	public CString add(char c) {
		
		int length = this.length + 1;
		char[] data = new char[length];
		int position = 0; 
		
		for (int i = 0; i < this.length; position++, i++) {
			data[position] = this.charAt(i);
		}
		
		data[position] = c;
		
		return new CString(data);
	}
	
	/**
	 * Replaces each substring of this string that matches the given char as parameter, with
	 * a new char given as second parameter.
	 * 
	 * @param oldChar - the old character. 
	 * @param newChar - the new character.
	 * @return new CString with replaced characters.
	 */
	public CString replaceAll(char oldChar, char newChar) {
		
		char[] newData = new char[this.length];
		for (int i = 0; i < this.length; i++) {
			
			if (this.charAt(i) == oldChar) {
				newData[i] = newChar;
			}
			else {
				newData[i] = this.charAt(i);
			}
		}
		return new CString(newData);
	}
	
	/**
	 * Replaces each substring of this CString that matches the given CString as parameter, with
	 * a new CString given as second parameter.
	 * 
	 * @param oldStr - the old CString. 
	 * @param newStr - the new CString.
	 * @return new CString with replaced substrings.
	 */
	public CString replaceAll(CString oldStr, CString newStr) {
		
		if (!this.contains(oldStr)) {
			System.err.println("CString \""+this.toString()+"\" does not contains \""+oldStr+"\"");
			return null;
		}
		
		char beginingChar = oldStr.charAt(0);
		char[] newData = {};
		CString newCS = new CString(newData);
		
		for (int i = 0; i < this.length; i++) {
			
			if (this.charAt(i) == beginingChar) {
				
				CString subString = this.subString(i, i + oldStr.length);
				if (subString.startsWith(oldStr)) {
					
					newCS = newCS.add(newStr);
					i += oldStr.length-1;
					continue;
				}
				else {
					newCS = newCS.add(this.charAt(i));
				}
			}
			else {
				newCS = newCS.add(this.charAt(i));
			}
		}
		return newCS;
	}
}
