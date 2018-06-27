package models;

import java.util.HashMap;
import java.util.Map;

public class SetMap<T> {
	private Map<T, Integer> map = new HashMap<T, Integer>();

	public void add(T symbol) {
		if (map.containsKey(symbol)) {
			map.put(symbol, map.get(symbol) + 1);

		} else {
			map.put(symbol, 1);
		}
	}

	public Map<T, Integer> getMap() {
		return map;
	}

	public void setMap(Map<T, Integer> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return map.toString();
	}

	public int getCountFor(T wordValue) {
		if (map.containsKey(wordValue)) {
			return map.get(wordValue);
		}
		return 0;
	}

}
