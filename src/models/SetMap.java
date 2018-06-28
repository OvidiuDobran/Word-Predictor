package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class SetMap<T> implements Serializable {
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

	public int size() {
		return map.size();
	}

	public List<T> getFirstN(int noOfSuggestions) {
		List<Pair> listAux = new ArrayList<Pair>();
		for (Map.Entry<T, Integer> entry : map.entrySet()) {
			listAux.add(new Pair(entry.getKey(), entry.getValue()));
		}
		Collections.sort(listAux);
		List<T> list = new ArrayList<T>();
		for (Pair p : listAux) {
			list.add(p.key);
		}
		if (noOfSuggestions > list.size()) {
			return list;
		}
		return list.subList(0, noOfSuggestions);
	}

	class Pair implements Comparable<Pair> {
		T key;
		int value;

		public Pair(T key2, Integer value2) {
			key = key2;
			value = value2;
		}

		@Override
		public int compareTo(SetMap<T>.Pair o) {
			if (this.value == o.value) {
				return 0;
			}
			return this.value > o.value ? -1 : 1;
		}
	}

}
