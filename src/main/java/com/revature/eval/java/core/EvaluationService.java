package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j = 0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		char[] acronym = new char[phrase.length()];

///		Using regex to split at non-alphabetical characters
		String[] split = phrase.split("\\P{Alpha}+");
		for (int i = 0; i < split.length; i++) {
			acronym[i] = split[i].charAt(0);
		}

//		convert the array that holds the acronym to a string, trim the ending whitespace,
//		and make it uppercase since acronyms are uppercase.
		return new String(acronym).trim().toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
//			If sideOne is equal to sideTwo and sideOne is equal to sideThree, then all
//			three sides must have the same value. The && will make sure true is returned if
//			both sides are true. Else, false will be returned
			return ((this.sideOne == this.sideTwo) && (this.sideOne == this.sideThree));
		}

		public boolean isIsosceles() {
//			Similar to above, but with | will return true if at least one statement is true,
//			since we want at least two sides to be the same (but all three can be too).
//			Using | instead of || so both sides get evaluated.
			return ((this.sideOne == this.sideTwo) | (this.sideOne == this.sideThree));

		}

		public boolean isScalene() {
//			Since this is the opposite of an equilateral, we'll check for the opposite of what we
//			did in isEquilateral();
			return (!(this.sideOne == this.sideTwo) && !(this.sideOne == this.sideThree));
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {

		int score = 0;

		Map<Character, Integer> letterValues = new HashMap<>();
		letterValues.put('A', 1);
		letterValues.put('B', 3);
		letterValues.put('C', 3);
		letterValues.put('D', 2);
		letterValues.put('E', 1);
		letterValues.put('F', 4);
		letterValues.put('G', 2);
		letterValues.put('H', 4);
		letterValues.put('I', 1);
		letterValues.put('J', 8);
		letterValues.put('K', 5);
		letterValues.put('L', 1);
		letterValues.put('M', 3);
		letterValues.put('N', 1);
		letterValues.put('O', 1);
		letterValues.put('P', 3);
		letterValues.put('Q', 10);
		letterValues.put('R', 1);
		letterValues.put('S', 1);
		letterValues.put('T', 1);
		letterValues.put('U', 1);
		letterValues.put('V', 4);
		letterValues.put('W', 4);
		letterValues.put('X', 8);
		letterValues.put('Y', 4);
		letterValues.put('Z', 10);

//		Turn the string into a character array
		char[] characters = string.toUpperCase().toCharArray();

//		Get the value of each letter and add its value to the score counter.
		for (int i = 0; i < characters.length; i++) {
			score += letterValues.get(characters[i]);
		}

		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {

//		regex will match non-numeric characters and replace with empty string.
//		Should leave us with only numbers.
		string = string.replaceAll("[^0-9]", "");

		if (string.charAt(0) == '1') {
			string = string.substring(1);
		}

		if (string.length() > 11 || string.length() < 9) {
			throw new IllegalArgumentException("Invalid number length");
		}

		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {

		// Using regex to split at non-alphabetical characters
		String[] split = string.split("\\P{Alpha}+");

		Map<String, Integer> counter = new HashMap<>();

//		if the key (word) already exists in the map, we'll get the number of occurrences and add +1.
//		If it's not in the map, we'll add it and set it to 1 (first occurrence)
		for (int i = 0; i < split.length; i++) {
			if (counter.get(split[i]) != null) {
				counter.put(split[i], counter.get(split[i]) + 1);
			} else {
				counter.put(split[i], 1);
			}
		}

		return counter;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {

			int lowerBound = 0;
			int higherBound = sortedList.size() - 1;
			int middle = sortedList.size() / 2;
			int foundT = -1;

			while (lowerBound <= higherBound) {

//				If it's in between the bounds, result (foundT) to the current index and break out of the loop
				if (sortedList.get(middle).equals(t)) {
					foundT = middle;
					break;
				} else if (sortedList.get(middle).compareTo(t) < 0) {
//					If the number we are searching for is smaller than our index, set the higher bound to one less than middle
//					so we search the lower half of the array and find the new midpoint

					lowerBound = ++middle;
					middle = (lowerBound + higherBound) / 2;
				} else {
//					If the number was larger, set the lower bound to one greater than the middle and find new midpoint

					higherBound = --middle;
					middle = (higherBound + lowerBound) / 2;
				}
			}

			return foundT;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {

		String[] split = string.split("\\s+");

		List<String> newPhrase = new ArrayList<>();

		for (int i = 0; i < split.length; i++) {

			for (int j = 0; j < split[i].length(); j++) {
				if (split[i].charAt(j) == 'q' && split[i].charAt(j + 1) == 'u') {
					String temp1 = split[i].substring(0, j + 2);
					String temp2 = split[i].substring(j + 2);
					newPhrase.add(temp2 + temp1 + "ay" + " ");
					break;
				}
				if (split[i].charAt(j) == 'a' || split[i].charAt(j) == 'e' || split[i].charAt(j) == 'i'
						|| split[i].charAt(j) == 'o' || split[i].charAt(j) == 'u') {
					String temp1 = split[i].substring(0, j);
					String temp2 = split[i].substring(j);
					newPhrase.add(temp2 + temp1 + "ay" + " ");
					break;
				}
			}

		}

		if (newPhrase.isEmpty()) {
			newPhrase.add(split[0] + "ay");
		}

		StringBuilder pigLatin = new StringBuilder();
		for (String s : newPhrase) {
			pigLatin.append(s);
		}
		return pigLatin.toString().trim();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {

//		My hack-y solution to splitting an int. Convert to string so it can be split,
//		turn the string into a char array
//		turn the char array into an int array
		String inputAsString = Integer.toString(input);
		char[] numAsCharArr = new char[inputAsString.length()];
		int[] numArr = new int[numAsCharArr.length];
		int calculated = 0;

		for (int i = 0; i < inputAsString.length(); i++) {
			numAsCharArr[i] = inputAsString.charAt(i);
			numArr[i] = Integer.parseInt(String.valueOf(numAsCharArr[i]));

//			We can update each element in the array to be raised to the length of the int arr
//			(raise each digit to the number of digits of the input)
//			Then we can add each to an accumulator which will compare the input to our calculated value
			numArr[i] = (int) Math.pow((double) numArr[i], (double) numArr.length);
			calculated += numArr[i];
		}

		return calculated == input;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {

		List<Long> primeFactors = new ArrayList<Long>();

		for (int i = 2; i <= l; i++) {
			while (l % i == 0) {
				primeFactors.add((long) i);
				l = l / i;
			}
		}
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {

			final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
			StringBuilder encodedString = new StringBuilder();

			for (int i = 0; i < string.length(); i++) {

				if (String.valueOf(string.charAt(i)).matches("[A-Z]")) {

//					Formula for Ceaser cipher is encoded = (text + key) mod 26.
//					This is for encoding an uppercase character					
					int encode = (key + ALPHABET_UPPER.indexOf(string.charAt(i))) % 26;
					char encodedChar = ALPHABET_UPPER.charAt(encode);
					encodedString.append(encodedChar);

				} else if (String.valueOf(string.charAt(i)).matches("[a-z]")) {

//					encoding a lowercase character					
					int encode = (key + ALPHABET_LOWER.indexOf(string.charAt(i))) % 26;
					char encodedChar = ALPHABET_LOWER.charAt(encode);
					encodedString.append(encodedChar);

				} else {
//					If we get something that isn't a char, just add it straight to the new string we're building
					encodedString.append(string.charAt(i));
				}

			}

			return encodedString.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {

		if (i < 1) {
			throw new IllegalArgumentException("Please enter a number larger than 0");
		}

		int nthPrime = 0;
		int currentNum = 0;
		boolean isNotPrime = false;
		int counter = 0;
		while (counter < i + 1) {
//			Our number to test
			currentNum++;

//			for loop will test if our current number is divisible by 2 to our current number
			for (int j = 2; j < currentNum; j++) {
//				if our number was divided, set the flag so our current number isn't set to be the current nth prime
//				also break out of the loop as there is no point in continuing to test the current number
				if (currentNum % j == 0) {
					isNotPrime = true;
					break;
				}

			}

//			if the number is prime, set it to be the current nth prime
			if (isNotPrime == false) {
				nthPrime = currentNum;
				counter++;
			}

//			reset our flag for the next round
			isNotPrime = false;
		}

		return nthPrime;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {

			Map<Character, Character> rotatedValues = new HashMap<>();
			rotatedValues.put('A', 'Z');
			rotatedValues.put('B', 'Y');
			rotatedValues.put('C', 'X');
			rotatedValues.put('D', 'W');
			rotatedValues.put('E', 'V');
			rotatedValues.put('F', 'U');
			rotatedValues.put('G', 'T');
			rotatedValues.put('H', 'S');
			rotatedValues.put('I', 'R');
			rotatedValues.put('J', 'Q');
			rotatedValues.put('K', 'P');
			rotatedValues.put('L', 'O');
			rotatedValues.put('M', 'N');
			rotatedValues.put('N', 'M');
			rotatedValues.put('O', 'L');
			rotatedValues.put('P', 'K');
			rotatedValues.put('Q', 'J');
			rotatedValues.put('R', 'I');
			rotatedValues.put('S', 'H');
			rotatedValues.put('T', 'G');
			rotatedValues.put('U', 'F');
			rotatedValues.put('V', 'E');
			rotatedValues.put('W', 'D');
			rotatedValues.put('X', 'C');
			rotatedValues.put('Y', 'B');
			rotatedValues.put('Z', 'A');
			rotatedValues.put('0', 'A');
			rotatedValues.put('1', '1');
			rotatedValues.put('2', '2');
			rotatedValues.put('3', '3');
			rotatedValues.put('4', '4');
			rotatedValues.put('5', '5');
			rotatedValues.put('6', '6');
			rotatedValues.put('7', '7');
			rotatedValues.put('8', '8');
			rotatedValues.put('9', '9');

//			Turn the string into a character array
			string = string.replaceAll("[^a-zA-Z0-9]", "");
			char[] characters = string.toUpperCase().toCharArray();

			char[] encoded = new char[characters.length];

//			Get what each letter is mapped to and add its new value to the array
			for (int i = 0; i < characters.length; i++) {

				encoded[i] = rotatedValues.get(characters[i]);
			}

			StringBuilder encodedString = new StringBuilder();
			encodedString.append(encoded);

			String encodedStringNoSpaces = encodedString.toString().toLowerCase();

			return encodedStringNoSpaces.toString().toLowerCase().replaceAll("(.{5})(?!$)", "$1\s");
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {

			Map<Character, Character> rotatedValues = new HashMap<>();
			rotatedValues.put('A', 'Z');
			rotatedValues.put('B', 'Y');
			rotatedValues.put('C', 'X');
			rotatedValues.put('D', 'W');
			rotatedValues.put('E', 'V');
			rotatedValues.put('F', 'U');
			rotatedValues.put('G', 'T');
			rotatedValues.put('H', 'S');
			rotatedValues.put('I', 'R');
			rotatedValues.put('J', 'Q');
			rotatedValues.put('K', 'P');
			rotatedValues.put('L', 'O');
			rotatedValues.put('M', 'N');
			rotatedValues.put('N', 'M');
			rotatedValues.put('O', 'L');
			rotatedValues.put('P', 'K');
			rotatedValues.put('Q', 'J');
			rotatedValues.put('R', 'I');
			rotatedValues.put('S', 'H');
			rotatedValues.put('T', 'G');
			rotatedValues.put('U', 'F');
			rotatedValues.put('V', 'E');
			rotatedValues.put('W', 'D');
			rotatedValues.put('X', 'C');
			rotatedValues.put('Y', 'B');
			rotatedValues.put('Z', 'A');
			rotatedValues.put('0', 'A');
			rotatedValues.put('1', '1');
			rotatedValues.put('2', '2');
			rotatedValues.put('3', '3');
			rotatedValues.put('4', '4');
			rotatedValues.put('5', '5');
			rotatedValues.put('6', '6');
			rotatedValues.put('7', '7');
			rotatedValues.put('8', '8');
			rotatedValues.put('9', '9');

//			Turn the string into a character array
			string = string.replaceAll("[^a-zA-Z0-9]", "");
			char[] characters = string.toUpperCase().toCharArray();

			char[] decoded = new char[characters.length];

//			Get what each letter is mapped to and add its new value to the array
			for (int i = 0; i < characters.length; i++) {

				decoded[i] = rotatedValues.get(characters[i]);
			}

			StringBuilder decodedString = new StringBuilder();
			decodedString.append(decoded);
			return decodedString.toString().toLowerCase();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {

//		We only want 0-9 and X
		string = reverse(string.replaceAll("[^0-9Xx]", ""));
		int isbnMultAccumulator = 0;

//		If the ISBN isn't 10 digits, it's not valid
		if (string.length() != 10) {
			return false;
		}

//		starting the loop at 1 for the multiplication, but need to accommodate that in the for loop's
//		exit condition and charAt
		for (int i = 1; i < string.length() + 1; i++) {
			if (string.charAt(i - 1) == 'x' || string.charAt(i - 1) == 'X') {
				isbnMultAccumulator += 10 * i;
			} else {
				int isbnDigit = Integer.parseInt(String.valueOf(string.charAt(i - 1)));
				isbnMultAccumulator += isbnDigit * i;
			}

		}

		return (isbnMultAccumulator % 11 == 0 ? true : false);

	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {

		string = string.replaceAll("\\s+", "");

		Map<Character, Integer> counter = new HashMap<>();

//		if the key (char) already exists in the map, we'll get the number of occurrences and add +1.
//		If it's not in the map, we'll add it and set it to 1 (first occurrence)

		for (char c : string.toCharArray()) {
			if (counter.get(c) != null) {
				counter.put(c, counter.get(c) + 1);
			} else {
				counter.put(c, 1);
			}
		}

//		if size is 26, we got all the letters at least once.
		if (counter.size() == 26) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {

		if (given.isSupported(ChronoUnit.SECONDS)) {
//			if the temporal already supports seconds, add a gigasecond and return
			return given.plus(1000000000L, ChronoUnit.SECONDS);
		} else {
//			if it doesn't support seconds, set it LocalDate so we can set it to localDateTime to give it seconds and add a gigsecond
			LocalDate dateNoTime = LocalDate.from(given);
			return LocalDateTime.from(dateNoTime.atStartOfDay()).plus(1000000000L, ChronoUnit.SECONDS);
		}
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {

		int sumOfMults = 0;

		for (int j = 0; j < i; j++) {

			boolean isMultiple = false;
			for (int k = 0; k < set.length; k++) {
				if (j % set[k] == 0) {
					isMultiple = true;
				}
			}

			if (isMultiple) {
				sumOfMults += j;
			}

		}
		return sumOfMults;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
//		remove spaces
		string = string.replaceAll("\\s+", "");

		char[] numAsCharArr = new char[string.length()];
		int[] numArr = new int[numAsCharArr.length];
		int calculated = 0;

//		convert string to int array. If we fail to parse and int, then there is an invalid
//		character in the string and we should return false (invalid Luhn number)
		for (int i = 0; i < string.length(); i++) {
			try {
				numAsCharArr[i] = string.charAt(i);
				numArr[i] = Integer.parseInt(String.valueOf(numAsCharArr[i]));
			} catch (NumberFormatException e) {
				return false;
			}

		}

//		Start from the second to last element and work backwards, skipping every other element
//		so that we double every other element. If the element becomes greater than 9, subtract 9
		for (int i = numArr.length - 2; i >= 0; i -= 2) {
			numArr[i] *= 2;
			if (numArr[i] > 9) {
				numArr[i] -= 9;
			}
		}

//		Add all the elements, check if divisible by 10 (mod will return 0 if it is) and return our
//		final result
		for (int i = 0; i < numArr.length; i++) {
			calculated += numArr[i];
		}

		if (calculated % 10 == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {

		String[] split = string.replaceAll("[^a-zA-Z0-9\\-\\s+]", "").split("\\s+");

		String operation = split[3];
		int result = 0;
		int num1 = Integer.parseInt(split[2]);

		switch (operation) {
		case "plus":
			result = num1 + Integer.parseInt(split[4]);
			break;
		case "minus":
			result = num1 - Integer.parseInt(split[4]);
			break;
		case "multiplied":
			result = num1 * Integer.parseInt(split[5]);
			break;
		case "divided":
			result = num1 / Integer.parseInt(split[5]);

		}

		return result;
	}

}
