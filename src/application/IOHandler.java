package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IOHandler {
	public List<String> readFromTextFile(String path) throws IOException {
		List<String> result = new ArrayList<String>();
		BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String line = "";
			while ((line = bf.readLine()) != null) {
				if (line.length() != 0) {
					result.add(line);
				}
			}
			bf.close();
		
		return result;
	}
}
