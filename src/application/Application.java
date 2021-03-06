package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Word;
import ui.UIHandler;

public class Application {

	private UIHandler uiHandler;
	private IOHandler ioHandler;
	private WordOperationsHandler wordOperationsHandler;
	private SerializationHandler serializationHandler;
	private List<Word> words;

	public Application() {
		uiHandler = UIHandler.getInstance(this);
		ioHandler = new IOHandler();
		wordOperationsHandler = new WordOperationsHandler();
		serializationHandler = new SerializationHandler();
		setWords(serializationHandler.deserializeObjects());
	}

	public static void main(String[] args) {
		Application application = new Application();
		application.run();
	}

	private void run() {
		uiHandler.run();
		// TODO some method that saves the data
		serializationHandler.serializeObjects(words);
		System.err.println(words.get(1).getSuggestions());
	}

	public void processTextFromKeyoboard(String text) {

		String[] textOnLines = text.split("\\r?\\n");
		List<String> lines = new ArrayList<String>(Arrays.asList(textOnLines));
		while (lines.contains("")) {
			lines.remove("");
		}
		processWordsFromStrings(lines);
	}

	public void processTextFromFile(String path) throws IOException {
		List<String> lines = ioHandler.readFromTextFile(path);
		System.out.println(lines);
		processWordsFromStrings(lines);

	}

	private void processWordsFromStrings(List<String> wordsAsString) {
		for (String line : wordsAsString) {
			List<Word> newWords = wordOperationsHandler.parseTextIntoWords(line);
			addNewWords(newWords);

			for (int i = 0; i < newWords.size() - 1; i++) {
				newWords.get(i).addSuccessor(newWords.get(i + 1));
			}
		}
		System.out.println(words);
	}

	private void addNewWords(List<Word> newWords) {
		// for (int i = 0; i < newWords.size(); i++) {
		// if (!words.contains(newWords.get(i))) {
		// words.add(newWords.get(i));
		// } else {
		// newWords.set(i, words.get(words.indexOf(newWords.get(i))));
		// }
		// }
		for (Word newWord : newWords) {
			if (!words.contains(newWord)) {
				words.add(newWord);
			} else {
				int indexInOldWords = words.indexOf(newWord);
				int indexInNewWords = newWords.indexOf(newWord);
				newWords.set(indexInNewWords, words.get(indexInOldWords));
			}
		}
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public List<String> getWordStartingWith(String currentWord) {
		List<String> suggestions = new ArrayList<String>();
		int counter = 0;
		if (!"".equals(currentWord) && !" ".equals(currentWord)) {
			for (Word word : words) {
				if (word.getValue().startsWith(currentWord)) {
					suggestions.add(word.getValue());
					counter++;
					if (counter >= 3) {
						break;
					}
				}
			}
		}
		return suggestions;
	}

	public List<Word> getNextWordsOf(String lastWord) {
		Word word = new Word(lastWord);
		if (words.contains(word)) {
			int index = words.indexOf(word);
			word = words.get(index);
			return word.getSuggestions();
		}
		return new ArrayList<Word>();
	}

}
