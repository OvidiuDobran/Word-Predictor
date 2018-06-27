package models;

public class Word {
	private String value;
	private SetMap<Word> successors = new SetMap<Word>();

	public Word(String value) {
		this.value = value;
	}

	public void addSuccessor(Word word) {
		successors.add(word);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SetMap getSuccessors() {
		return successors;
	}

	public void setSuccessors(SetMap successors) {
		this.successors = successors;
	}

	@Override
	public String toString() {
		return value + successors.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Word) {
			Word word = (Word) obj;
			if (value.equals(word.value)) {
				System.err.println(value + "==" + word.value);
				return true;
			}
		}
		return false;
	}
}
