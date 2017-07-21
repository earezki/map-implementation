public class MapSeparateChaining<K, V> {
	// helper inner class that holds a key, a value and a next entry.
	private static class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	// the max size that the map can hold.
	private int capacity;
	private Entry<K, V>[] map;
	// current size of the map.
	private int size;

	@SuppressWarnings("unchecked")
	public MapSeparateChaining(int capacity) {
		this.capacity = capacity;
		map = new Entry[capacity];
	}

	// add a new value to the map otherwise replace the old one if they have the
	// same keys.
	public void put(K key, V value) {
		// resize the map if it passes a threshold.
		if (size >= capacity / 3) {
			resize(capacity * 3);
		}

		// resize the map if it passes a threshold.
		int hash = (key.hashCode() & 0x7fffffff) % capacity;
		if (map[hash] == null)
			map[hash] = new Entry<K, V>(key, value);
		else {
			Entry<K, V> head = map[hash];
			// search for next free slot.
			while (head != null) {
				if (head.key.equals(key)) {
					head.value = value;
					break;
				}

				if (head.next == null) {
					head.next = new Entry<K, V>(key, value);
					break;
				}

				head = head.next;
			}
		}
		size++;
	}

	@SuppressWarnings("unchecked")
	private void resize(int i) {
		size = 0;
		capacity = capacity * 3;
		Entry<K, V>[] oldMap = map;
		map = new Entry[capacity];
		// re-insert the old map values into the new one to calculate their new
		// hashes based on the new capacity value.
		for (Entry<K, V> entry : oldMap) {
			if (entry != null) {
				put(entry.key, entry.value);
			}
		}
	}

	// return the value associated with that key otherwise a null value.
	public V get(K key) {
		// resize the map if it passes a threshold.
		int hash = (key.hashCode() & 0x7fffffff) % capacity;
		if (map[hash] == null)
			return null;

		Entry<K, V> head = map[hash];
		while (head != null) {
			if (head.key.equals(key))
				return head.value;
			head = head.next;
		}

		return null;
	}

	public boolean containsKey(K key) {
		return get(key) != null;
	}
}