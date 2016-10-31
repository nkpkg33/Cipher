import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CipherTest {
	
	@Test
	public void getWordsTest1(){
		
		String plainText = "the quick brown fox jumped over the lazy dogs";
		
		String correctResultText = "thequickbrownfoxjumpedoverthelazydogs";
		
		ArrayList<String> str = Cipher.getWords(plainText);
		
		String getWordsResult = "";
		
		for (int i = 0; i < str.size(); i++) getWordsResult += str.get(i);
		
		System.out.println(getWordsResult);
		
		assertEquals(getWordsResult, correctResultText);
		
	}
	
	@Test
	public void getWordsTest2(){
		
		String plainText = " the quick brown fox ";
		
		String correctResultText = "thequickbrownfox";
		
		ArrayList<String> str = Cipher.getWords(plainText);
		
		String getWordsResult = "";
		
		for (int i = 0; i < str.size(); i++) getWordsResult += str.get(i);
		
		System.out.println(getWordsResult);
		
		assertEquals(getWordsResult, correctResultText);
		
	}
	
	@Test
	public void getWordsTest3(){
		
		String plainText = "the     quick ";
		
		String correctResultText = "thequick";
		
		ArrayList<String> str = Cipher.getWords(plainText);
		
		String getWordsResult = "";
		
		for (int i = 0; i < str.size(); i++) getWordsResult += str.get(i);
		
		System.out.println(getWordsResult);
		
		assertEquals(getWordsResult, correctResultText);
		
	}
	
	@Test
	public void isEnglishTest1(){
		
		String plainText = "slkdfjl lsrjiflsjf  sljfldjlsfj";
		
		assertEquals(Cipher.isEnglish(plainText), false);
		
	}
	
	@Test
	public void isEnglishTest2(){
		
		String plainText = "thhhhee quick brown fox";
		
		assertEquals(Cipher.isEnglish(plainText), true);
		
	}
	
	@Test
	public void isEnglishTest3(){
		
		String plainText = "yyys today is school iljiljoijis eifjidsjf sjfidjfjls";
		
		assertEquals(Cipher.isEnglish(plainText), false);
		
	}

	@Test
	public void rotationCipherEncryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherEncryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100);
		assertEquals(testCipherText, correctCipherText);
	}
}
