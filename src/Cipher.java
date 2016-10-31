import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;

	private static Dictionary dictionary = Dictionary
			.buildDictionary("/Users/nehakonakalla/Documents/workspace/CipherBlankTemplate/words.txt");

	/***
	 * Returns the password string needed to crack the cipher
	 * 
	 * @param cipher
	 *            the cipher String to find the password
	 * @param the
	 *            alphabet used to crack the cipher
	 * @return the password string
	 */

	public static String vigenereCipherCrack(String cipher, String alphabet) {

		String password = "";

		String[] groups = new String[] {};

		for (int passwordLength = 1; passwordLength < 100; passwordLength++) {

			String[] tempGroups = splitIntoGroups(cipher, passwordLength);

			if (getSimilarityToEnglishScore(getNewBag(rotationCipherDecrypt(tempGroups[0],
					alphabet.indexOf(crackOneGroup(tempGroups[0], alphabet))), alphabet), alphabet) < 15) {

				groups = tempGroups;

				break;

			}

		}

		for (String group : groups) {

			password += crackOneGroup(group, alphabet);

		}

		return password;

	}

	/***
	 * Returns the password string needed to crack the cipher
	 * 
	 * @param cipher
	 *            the cipher String to find the password
	 * @param passwordLength
	 *            the length of the password to find
	 * @param alphabet
	 *            the alphabet used to crack the cipher
	 * @return the password string
	 */

	public static String vigenereCipherCrack(String cipher, int passwordLength, String alphabet) {

		String password = "";

		String[] groups = splitIntoGroups(cipher, passwordLength);

		for (String group : groups) {

			password += crackOneGroup(group, alphabet);

		}

		return password;

	}

	/***
	 * Returns the password string needed to crack the cipher
	 * 
	 * @param cipher
	 *            the cipher String to find the password
	 * @param alphabet
	 *            the alphabet used to crack the cipher
	 * @return the password string
	 */

	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {

		String password = "";

		String[] groups = splitIntoGroups(cipher, 3);

		for (String group : groups) {

			password += crackOneGroup(group, alphabet);

		}

		return password;

	}

	/***
	 * returns a string that whose index in the alphabet is the shift amount for
	 * the group
	 * 
	 * @param group
	 *            the group to find the shift amount
	 * @param alphabet
	 *            the alphabet used to crack the cipher
	 * @return the letter whose index in the alphabet is the group's shift
	 *         amount
	 */

	public static String crackOneGroup(String group, String alphabet) {

		double minScore = Double.MAX_VALUE;

		int bestShiftValue = 0;

		for (int shiftAmount = 0; shiftAmount < alphabet.length(); shiftAmount++) {

			String decoded = rotationCipherDecrypt(group, shiftAmount);

			LetterBag bag = getNewBag(decoded, alphabet);

			double score = getSimilarityToEnglishScore(bag, alphabet);

			if (score < minScore) {

				minScore = score;

				bestShiftValue = shiftAmount;

			}

		}

		return alphabet.substring(bestShiftValue, bestShiftValue + 1);

	}

	public static double getSimilarityToEnglishScore(LetterBag bag, String alphabet) {

		double frequencyDist = 0;

		double[] englishFrequency = { 8.17, 1.49, 2.78, 4.25, 12.7, 2.23, 2.01, 6.09, 6.97, .15, .77, 4.03, 2.4, 6.75,
				7.5, 1.93, .09, 5.99, 6.33, 9.1, 2.8, .98, 2.36, .15, 1.98, .07 };

		for (int i = 0; i < 26; i++) {

			double numTimes = bag.getNumOccurrences(alphabet.substring(i, i + 1));

			double myFrequency = (numTimes / (double) bag.getTotalWords()) * 100;

			frequencyDist += Math.abs(englishFrequency[i] - myFrequency);

		}

		return frequencyDist;

	}

	public static LetterBag getNewBag(String group, String alphabet) {

		LetterBag bag = new LetterBag();

		for (int i = 0; i < group.length(); i++) {

			if (alphabet.indexOf(group.substring(i, i + 1)) < 52)
				bag.add(group.substring(i, i + 1));

		}

		return bag;

	}

	/***
	 * returns an array of the cipher string split into three groups
	 * 
	 * @param cipher
	 *            the cipher string to split
	 * @return the array of the three groups
	 */

	public static String[] splitIntoGroups(String cipher, int length) {

		String[] groups = new String[length];

		int positionState = 0;

		for (int i = 0; i < cipher.length(); i++) {

			groups[positionState] += cipher.substring(i, i + 1);

			positionState++;

			if (positionState >= length)
				positionState = 0;

		}

		return groups;

	}

	/***
	 * returns the decoded text by frequency cracking
	 * 
	 * @param cipher
	 *            the cipher text to decode
	 * @param alphabet
	 *            the alphabet to use in cracking
	 * @return the decoded text
	 */

	public static String rotationCipherCrack(String cipher, String alphabet) {

		return rotationCipherDecrypt(cipher, alphabet.indexOf(crackOneGroup(cipher, alphabet)));

	}

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {

		String tempText = "";

		for (int i = 0; i < plain.length(); i++) {

			int index = alphabet.indexOf(plain.substring(i, i + 1)) + shift;

			index = makeValidShift(index, alphabet);

			tempText += alphabet.substring(index, index + 1);

		}

		return (plain = tempText);

	}

	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param shiftAmount
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {

		String tempText = "";

		for (int i = 0; i < cipher.length(); i++) {

			int index = alphabet.indexOf(cipher.substring(i, i + 1)) - shift;

			index = makeValidShift(index, alphabet);

			tempText += alphabet.substring(index, index + 1);

		}

		return (cipher = tempText);

	}

	public static String rotationCipherDecrypt(String cipherText, int shiftAmount) {
		return rotationCipherDecrypt(cipherText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plain, String password, String alphabet) {

		String tempText = "";

		while (password.length() < plain.length()) {

			password += password;

		}

		for (int i = 0; i < plain.length(); i++) {

			int codeIndex = alphabet.indexOf(password.substring(i, i + 1));

			int index = alphabet.indexOf(plain.substring(i, i + 1)) + codeIndex;

			index = makeValidShift(index, alphabet);

			tempText += alphabet.substring(index, index + 1);

		}

		return (plain = tempText);

	}

	public static String vigenereCipherEncrypt(String plainText, String code) {
		return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipher, String password, String alphabet) {

		String tempText = "";

		while (password.length() < cipher.length()) {

			password += password;

		}

		for (int i = 0; i < cipher.length(); i++) {

			int codeIndex = alphabet.indexOf(password.substring(i, i + 1));

			int index = alphabet.indexOf(cipher.substring(i, i + 1)) - codeIndex;

			index = makeValidShift(index, alphabet);

			tempText += alphabet.substring(index, index + 1);

		}

		return (cipher = tempText);

	}

	public static String vigenereCipherDecrypt(String cipherText, String code) {
		return vigenereCipherDecrypt(cipherText, code, DEFAULT_ALPHABET);
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else
				// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																				// a
																				// message
						+ "\"");
		}
		return b.toString();
	}

	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	public static boolean isEnglish(String plainText) {

		int englishWordCounter = 0;

		ArrayList<String> arr = getWords(plainText);

		for (int i = 0; i < arr.size(); i++) {

			if (dictionary.isWord(arr.get(i)))
				englishWordCounter++;

		}

		if (englishWordCounter / (double) arr.size() >= .7)
			return true;

		return false;

	}

	/***
	 * Returns an ArrayList of words without spaces
	 * 
	 * @param plainText
	 *            the string to remove the spaces from
	 * @return the ArrayList of words without spaces
	 */

	public static ArrayList<String> getWords(String plainText) {

		boolean isLetter = false;

		String word = "";

		ArrayList<String> words = new ArrayList<>();

		for (int i = 0; i < plainText.length(); i++) {

			if (!plainText.substring(i, i + 1).equals(" "))
				isLetter = true;

			else
				isLetter = false;

			if (isLetter)
				word += plainText.substring(i, i + 1);

			if (!isLetter || i == plainText.length() - 1) {

				if (!word.equals(""))
					words.add(word);

				word = "";

			}

		}

		return words;

	}

	/***
	 * Returns a substituted String by modifying a plainText String based on a
	 * permutation array
	 * 
	 * @param plainText
	 *            the String which will be modifyed based on a permutation array
	 * @param permutation
	 *            an array that gives indices of new letters for each sequential
	 *            letter in the alphabet.
	 * @param alphabet
	 *            the alphabet to be used for the substitution
	 * @return returns the substituted String based on the input plainText
	 *         String
	 */

	public static String substitutionCipher(String plainText, int[] permutation, String alphabet) {

		String output = "";

		for (int i = 0; i < plainText.length(); i++) {

			String letter = plainText.substring(i, i + 1);

			int index = alphabet.indexOf(letter);

			int newIndex = permutation[index];

			output += alphabet.substring(newIndex, newIndex + 1);

		}

		return output;

	}

	/***
	 * Returns whether or not an array is a valid permutation
	 * 
	 * @param permutation
	 *            the input array to check whether it's a valid permutation
	 * @return returns a boolean (whether the array is a valid permutation)
	 */

	public static boolean isValidPermutation(int[] permutation) {

		for (int i = 0; i < permutation.length - 1; i++) {

			for (int j = i + 1; j < permutation.length; j++) {

				if (permutation[i] == permutation[j])
					return false;

			}

		}

		return true;

	}

	/***
	 * Returns a random permutation of a certain length
	 * 
	 * @param length
	 *            the input length to check whether the permutation is that long
	 * @return an array that is a random permutation of the input length
	 */

	public static int[] randomPermutation(int length) {

		int[] positions = new int[length];

		for (int i = 0; i < length; i++) {

			positions[i] = i;

		}

		shuffleArray(positions);

		return positions;

	}

	/***
	 * Shuffles an array (no return value)
	 * 
	 * @param ar
	 *            the array to shuffle
	 */

	public static void shuffleArray(int[] ar) {
		Random rnd = new Random();

		for (int i = ar.length - 1; i > 0; i--) {

			int index = rnd.nextInt(i + 1);

			// Simple swap

			int a = ar[index];

			ar[index] = ar[i];

			ar[i] = a;

		}
	}

	/***
	 * Returns a valid shift number
	 * 
	 * @param index
	 *            the index to check if it's valid and modify when appropriate
	 * @param alphabet
	 *            the alphabet to be used in modifying index to be valid
	 * @return the valid shift number
	 */

	public static int makeValidShift(int index, String alphabet) {

		while (index < 0)
			index += alphabet.length();

		if (index > alphabet.length() - 1)
			index = index % alphabet.length();

		return index;

	}

}
