package application;

import java.io.IOException;
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

	public void processTextFromFile(String path) throws IOException {
		List<String> wordsAsString = ioHandler.readFromTextFile(path);
		System.out.println(wordsAsString);
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

	public void processTextFromKeyoboard(String text) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(text);
		System.out.println("--------------------------------------------------------------------------");

	}
}
