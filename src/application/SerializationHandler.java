package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import models.Word;

public class SerializationHandler {

	public List<Word> deserializeObjects() {
		List<Word> words = null;
		try {
			FileInputStream fileIn = new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			words = (List<Word>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			// i.printStackTrace();
			words = new ArrayList<Word>();
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
		}

		System.out.println("DESERIALIZTION "+words);
		return words;
	}

	public void serializeObjects(List<Word> words) {

		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(words);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
