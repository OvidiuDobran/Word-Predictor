package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Word;

class ModelsUnit {

	@Test
	void test() {

		Word word = new Word("I");
		word.addSuccessor(new Word("love"));
		word.addSuccessor(new Word("hate"));
		word.addSuccessor(new Word("love"));

		assertEquals(word.getCountOfSuccessor("love"), 2);
		assertEquals(word.getCountOfSuccessor("hate"), 1);

	}

}
