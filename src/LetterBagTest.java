import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class LetterBagTest {

	@Test
	public void totalWordsTest1() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("n");
		
		assertEquals(bag.getTotalWords(), 4);
		
	}
	
	@Test
	public void totalWordsTest2() {
		
		LetterBag bag = new LetterBag();
		
		assertEquals(bag.getTotalWords(), 0);
		
	}
	
	@Test
	public void uniqueWordsTest1() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("n");
		
		assertEquals(bag.getNumUniqueWords(), 2);
		
	}
	
	@Test
	public void uniqueWordsTest2() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("i");
		
		bag.add("i");
		
		bag.add("i");
		
		System.out.println(bag.getNumUniqueWords());
		
		assertEquals(bag.getNumUniqueWords(), 1);
		
	}
	
	@Test
	public void occurrencesTest1() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("n");
		
		assertEquals(bag.getNumOccurrences("a"), 3);
		
	}
	
	@Test
	public void occurrencesTest2() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("o");
		
		bag.add("O");
		
		assertEquals(bag.getNumOccurrences("o"), 2);
		
	}
	
	@Test
	public void mostFrequentTest1() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("n");
		
		bag.add("n");
		
		bag.add("y");
		
		assertEquals(bag.getMostFrequent(), "a");
		
	}
	
	@Test
	public void mostFrequentTest2() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("o");
		
		assertEquals(bag.getMostFrequent(), "o");
		
	}

	@Test
	public void NMostFrequentTest1() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("a");
		
		bag.add("n");
		
		bag.add("n");
		
		bag.add("y");
		
		assertEquals(Arrays.toString(bag.getNMostFrequent(3)), Arrays.toString(new String[] {"a", "n", "y"}));
		
	}
	
	@Test
	public void NMostFrequentTest2() {
		
		LetterBag bag = new LetterBag();
		
		bag.add("a");
		
		bag.add("y");
		
		assertEquals(Arrays.toString(bag.getNMostFrequent(2)), Arrays.toString(new String[] {"a", "y"}));
		
	}

}

