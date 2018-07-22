class MinIntHeap {
	public int capacity = 10;
	public int size = 0;
	public int items[] = new int[capacity + 1];

	// get Index Method()
	public int getLeftChildIndex(int i) {
		return (2 * i);
	}

	public int getRightChildIndex(int i) {
		return (2 * i) + 1;
	}

	public int getParentIndex(int i) {
		return i / 2;
	}

	// has Method()
	public boolean hasLeftChild(int i) {
		return getLeftChildIndex(i) <= size;
	}

	public boolean hasRightChild(int i) {
		return getRightChildIndex(i) <= size;
	}

	public boolean hasParent(int i) {
		return getParentIndex(i) >= 1;
	}

	// get Method()
	public int getLeftChild(int i) {
		return items[getLeftChildIndex(i)];
	}

	public int getRightChild(int i) {
		return items[getRightChildIndex(i)];
	}

	public int getParent(int i) {
		return items[getParentIndex(i)];
	}

	public void swap(int a, int b) {
		int temp = items[a];
		items[a] = items[b];
		items[b] = temp;
	}

	public void ensureExtraCapacity() {
		if (size == capacity) {
			items = Arrays.copyOf(items, (capacity * 2) + 1);
			capacity = (capacity * 2) + 1;
		}
	}

	public int peek() {
		if (size == 0)
			throw new IllegalStateException();

		return items[1];
	}

	public int poll() {
		if (size == 0)
			throw new IllegalStateException();

		int item = items[1];
		items[1] = items[size--];
		heapifyDown();
		return item;
	}

	public void add(int item) {
		ensureExtraCapacity();
		items[++size] = item;
		heapifyUp();
	}

	public void heapifyUp() {
		int index = size;
		while (hasParent(index) && getParent(index) > items[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}

	public void heapifyDown() {
		int index = 1;

		while (hasLeftChild(index)) {
			int smallerChildIndex = getLeftChildIndex(index);

			if (hasRightChild(index) && getRightChild(index) < getLeftChild(index))
				smallerChildIndex = getRightChildIndex(index);

			if (items[index] < items[smallerChildIndex]) 
				break;
			else 
				swap(index, smallerChildIndex);
			
			index = smallerChildIndex;
		}
	}
}