public class Company {

	private static class MyLinkedList<T> {

		// helper inner class that holds a data and next node.
		static class MyNode<T> {
			MyNode<T> next;
			T data;

			MyNode(T dataValue) {
				next = null;
				data = dataValue;
			}

			@SuppressWarnings("unused")
			MyNode(T dataValue, MyNode<T> nextValue) {
				next = nextValue;
				data = dataValue;
			}

		}

		private int size;
		private MyNode<T> head = null;

		// add a new item to the linked list.
		void add(T data) {
			// if data is first value then create the head.
			if (head == null) {
				head = new MyNode<T>(data);
			}
			// create the new node to be inserted.
			MyNode<T> temp = new MyNode<T>(data);
			MyNode<T> current = head;
			if (current != null) {
				// search for the tail of the list.
				while (current.next != null) {
					current = current.next;
				}
				current.next = temp;
			}
			size++;
		}

		// get the value at the specified index, if index is less than 0 or
		// greater than the size of the list, null is returned.
		T get(int index) {
			if (index < 0)
				return null;
			MyNode<T> current = null;
			if (head != null) {
				current = head.next;
				for (int i = 0; i < index; i++) {
					if (current.next == null)
						return null;

					current = current.next;
				}
				return current.data;
			}
			return null;
		}

		int size() {
			return size;
		}
	}

	// name of the company.
	private String name;
	// linked list to hold complaint id's
	private MyLinkedList<String> ids;

	public Company(String name, String complaintId) {
		this.name = name;
		this.ids = new MyLinkedList<>();
		addId(complaintId);
	}

	// add a complaint id
	public void addId(String id) {
		ids.add(id);
	}

	// get the name of the company.
	public String getName() {
		return name;
	}

	// return an array representing the complaint id's
	public String[] getIds() {
		String[] idsarray = new String[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			idsarray[i] = ids.get(i);
		}
		return idsarray;
	}

	// equals method based on name&type.
	public boolean equals(Object otherObject) {
		if (otherObject == this)
			return true;
		if (otherObject instanceof Company) {
			return name.equals(((Company) otherObject).name);
		}
		return false;
	}

	// hash code based on the company's name.
	public int hashCode() {
		return name.hashCode();
	}
}