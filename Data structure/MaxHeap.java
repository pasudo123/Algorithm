class MaxIntHeap {
	public int capacity = 10;
	public int size = 0;
	public int[] items = new int[capacity * 2];

	// -- get Index Method()
	public int getLeftChildIndex(int i) {
		return (i * 2);
	}

	public int getRightChildIndex(int i) {
		return (i * 2) + 1;
	}

	public int getParentIndex(int i) {
		return (i / 2);
	}

	// -- has Method()
	public boolean hasLeftChild(int i) {
		return getLeftChildIndex(i) <= size;
	}

	public boolean hasRightChild(int i) {
		return getRightChildIndex(i) <= size;
	}

	public boolean hasParent(int i) {
		return getParentIndex(i) >= 1;
	}

	// -- get Item Method()
	public int getLeftChild(int i) {
		if (hasLeftChild(i))
			return items[getLeftChildIndex(i)];

		return 0;
	}

	public int getRightChild(int i) {
		if (hasRightChild(i))
			return items[getRightChildIndex(i)];

		return 0;
	}

	public int getParent(int i) {
		if (hasParent(i))
			return items[getParentIndex(i)];

		return 0;
	}

	// -- swap
	public void swap(int i, int j) {
		int temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}

	public void ensureExtraCapacity() {
		if (size == capacity) {
			items = Arrays.copyOf(items, capacity * 2);
			capacity *= 2;
		}
	}
	
	public void add(int item){
		ensureExtraCapacity();
		items[++size] = item;
		heapifyUp();
	}
	
	public int poll(){
		if (size == 0)
			throw new IllegalStateException();
		
		int item = items[1];
		items[1] = items[size--];
		heapifyDown();
		return item;
	}
	
	public void heapifyUp(){
		int index = size;
		
		while(hasParent(index) && getParent(index) < items[index]){
			swap(index, getParentIndex(index));
			index = getParentIndex(index);
		}
	}
	
	public void heapifyDown(){
		int index = 1;
		
		// 왼쪽 진입
		while(hasLeftChild(index)){
			int biggerIndex = getLeftChildIndex(index);
			
			if(hasRightChild(index) && getLeftChild(index) < getRightChild(index))
				biggerIndex = getRightChildIndex(index);
			
			if(items[index] > items[biggerIndex])
				break;
			else
				swap(index, biggerIndex);
			
			index = biggerIndex;
		}
	}
}