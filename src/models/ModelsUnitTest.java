package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Word;

class ModelsUnitTest {

	@Test
	void test() {

		Word word = new Word("I");
		word.addSuccessor(new Word("am"));
		word.addSuccessor(new Word("am"));
		word.addSuccessor(new Word("am"));

		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("love"));
		word.addSuccessor(new Word("love"));

		assertEquals(word.getCountOfSuccessor("love"), 2);
		assertEquals(word.getCountOfSuccessor("hate"), 4);
		assertEquals(word.getCountOfSuccessor("like"), 0);

	}

	@Test
	void test2() {
		Word word = new Word("I");
		word.addSuccessor(new Word("am"));
		word.addSuccessor(new Word("am"));
		word.addSuccessor(new Word("am"));

		word.addSuccessor(new Word("like"));
		word.addSuccessor(new Word("like"));
		word.addSuccessor(new Word("like"));
		word.addSuccessor(new Word("like"));

		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("love"));
		word.addSuccessor(new Word("love"));

		word.addSuccessor(new Word("go"));

		System.out.println(word.getSuggestions());
	}

}
