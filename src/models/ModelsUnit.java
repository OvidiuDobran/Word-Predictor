package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelsUnit {

	@Test
	void test() {
		Word word=new Word("I");
		word.addSuccessor(new Word("love"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("love"));
		
		System.out.println("successors: "+word.getSuccessors());
	}
	
	@Test
	void test2() {
		Word word1=new Word("abc");
		Word word2=new Word("abc");
		assertTrue(word1.equals(word2));
		System.out.println(word1.equals(word2));
	}
	

}
