public class LetterBag {

private final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
		+ '\n' + '\r';
	
	private int[] letterFrequencies;
	
	public LetterBag(){
		
		letterFrequencies = new int[alphabet.length()];
		
	}
	
	/***
	 * Adds a letter to the letter bag
	 * @param letter the input letter to add to the bag
	 */
	
	public void add(String letter){
		
		String lower = letter.toLowerCase();
		
		int index = getIndexForLetter(lower);
		
		letterFrequencies[index]++;
		
	}
	
	/***
	 * returns the index of the letter in the alphabet
	 * @param lower the String to get the index of in the alphabet
	 * @return the index of the letter in the alphabet
	 */
	
	private int getIndexForLetter(String lower){
		
		return alphabet.indexOf(lower);
		
	}
	
	/***
	 * returns the total number of words in the letter bag
	 * @return the total number of words
	 */
	
	public int getTotalWords(){
		
		int letterCounter = 0;
		
		for (int i = 0; i < letterFrequencies.length; i++){
			
			letterCounter += letterFrequencies[i];
			
		}
		
		return letterCounter;
		
	}
	
	/***
	 * returns the total number of unique words in the letter bag
	 * @return the total number of unique words
	 */
	
	public int getNumUniqueWords(){
		
		int letterCounter = 0;
		
		for (int i = 0; i < letterFrequencies.length; i++){
			
			if (letterFrequencies[i] > 0) letterCounter++;
			
		}
		
		return letterCounter;
		
	}
	
	/***
	 * returns the total number of occurrences of a letter in the bag
	 * @param letter the letter to find the total number of occurrences
	 * @return the total number of occurrences
	 */
	
	public int getNumOccurrences(String letter){
		
		return letterFrequencies[getIndexForLetter(letter)];
		
	}
	
	/***
	 * returns the most frequent letter in the bag
	 * @return the most frequent letter
	 */
	
	public String getMostFrequent(){
		
		int maxTimes = -1;
		
		String mostFrequentLetter = "a";
		
		for (int i = 0; i < letterFrequencies.length; i++){
			
			if (letterFrequencies[i] > maxTimes){
				
				maxTimes = letterFrequencies[i];
				
				mostFrequentLetter = alphabet.substring(i, i + 1);
				
			}
			
		}
		
		return mostFrequentLetter;
		
	}
	
	/***
	 * returns the top n most frequent letters in the bag
	 * @param num the number of most frequent letters to find
	 * @return the top n most frequent letters
	 */
	
	public String[] getNMostFrequent(int num){
		
		
		String[] arr = new String[num];
		
		int[] copy = makeArrayCopy(letterFrequencies);
		
		for (int i = 0; i < num; i++){
			
			arr[i] = getMostFrequent(copy);
			
		}
		
			
			return arr;
			
		}
	
	/***
	 * returns the most frequent letter in the bag
	 * @param frequencies the frequency array to find the most frequent letter 
	 * @return the most frequent letter
	 */
	
	private String getMostFrequent(int[] frequencies){
		
		int maxTimes = -1;
		
		String mostFrequentLetter = "a";
		
		for (int i = 0; i < frequencies.length; i++){
			
			if (frequencies[i] > maxTimes){
				
				maxTimes = frequencies[i];
				
				mostFrequentLetter = alphabet.substring(i, i + 1);
				
				
			}
			
		}
		
		frequencies[getIndexForLetter(mostFrequentLetter)] = 0;
		
		return mostFrequentLetter;
		
	}
	
	/***
	 * makes a copy of an int array
	 * @param original the int array to make a copy of
	 * @return the copied array
	 */
		
	private int[] makeArrayCopy(int[] original){
		
	int[] copy = new int[original.length];
		
	for (int i = 0; i < original.length; i++) copy[i] = original[i];
	
	return copy;
		
	}
	
}
