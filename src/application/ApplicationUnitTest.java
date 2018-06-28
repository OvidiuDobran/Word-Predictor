package application;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.Word;

class ApplicationUnitTest {

	@Test
	void test() {
		WordOperationsHandler textOperationsHandler = new WordOperationsHandler();
		List<Word> words = textOperationsHandler.parseTextIntoWords("        I   \n  like \npancakes. How about you?");
		System.out.println(words);
	}

	@Test
	void test2() {
		WordOperationsHandler textOperationsHandler = new WordOperationsHandler();
		IOHandler ioHandler = new IOHandler();
		List<String> textFromFile = null;
		try {
			textFromFile = ioHandler.readFromTextFile("input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(textFromFile);
		for (String s : textFromFile) {
			List<Word> words = textOperationsHandler.parseTextIntoWords(s);
			System.out.println(words + " " + words.size());
		}
	}

}
