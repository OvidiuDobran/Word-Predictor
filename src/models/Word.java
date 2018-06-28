package models;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Word implements Serializable {
	private String value;
	private SetMap<Word> successors = new SetMap<Word>();
	public static final int NO_OF_SUGGESTIONS = 3;

	public Word(String value) {
		this.value = value;
	}

	public void addSuccessor(Word word) {
		System.err.println(value + ": " + successors);
		successors.add(word);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SetMap<Word> getSuccessors() {
		return successors;
	}

	public void setSuccessors(SetMap<Word> successors) {
		this.successors = successors;
	}

	@Override
	public String toString() {
		return value + "(" + successors.size() + ")";// + successors.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Word) {
			Word word = (Word) obj;
			if (value.equals(word.value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		String valueAux = value.toLowerCase();
		int res = 0;
		for (int i = 0; i < valueAux.length(); i++) {
			res += (37 * Character.getNumericValue(valueAux.charAt(i)));
		}
		return res;
	}

	public int getCountOfSuccessor(String wordValue) {
		return successors.getCountFor(new Word(wordValue));
	}

	public int getCountOfSuccessor(Word wordValue) {
		return successors.getCountFor(wordValue);
	}

	public List<Word> getSuggestions() {
		return successors.getFirstN(NO_OF_SUGGESTIONS);
	}

}
