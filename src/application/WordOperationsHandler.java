package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Word;

public class WordOperationsHandler {
	public List<Word> parseTextIntoWords(String text) {

		Pattern p = Pattern.compile("(\\s+|[,.!?:])");
		Matcher m = p.matcher(text);
		text = m.replaceAll(" ");

		p = Pattern.compile("(\\s+)");
		m = p.matcher(text);
		text = m.replaceAll(" ");

		String[] textSplitted = text.split(" ");
		List<String> listOfWordsAsStrings = new ArrayList<String>(Arrays.asList(textSplitted));
		while (listOfWordsAsStrings.contains("")) {
			listOfWordsAsStrings.remove(listOfWordsAsStrings.indexOf(""));
		}

		List<Word> words = new ArrayList<Word>();
		for (String wordAsString : listOfWordsAsStrings) {
			words.add(new Word(wordAsString));
		}

		return words;
	}
}
