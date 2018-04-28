
class Node {
	int data;
	Node leftNode ;
	Node rightNode;
	
	public Node(int data){
		this.data = data;
	}
	
	// 삽입
	public void insert(int value){
		if(value <= data){
			if(leftNode == null)
				leftNode = new Node(value);
			else
				leftNode.insert(value);
		}
		else{
			if(rightNode == null)
				rightNode = new Node(value);
			else
				rightNode.insert(value);
		}
	}
	
	// 포함 여부
	public boolean contains(int value){
		if(value == data)
			return true;
		else if(value < data){
			if(leftNode == null)
				return false;
			else
				return leftNode.contains(value);
		}
		else{// (value > data)
			if(rightNode == null)
				return false;
			else
				return rightNode.contains(value);
		}
	}
	
	// 중위순회
	public void printInOrder(){
		if(leftNode != null)
			leftNode.printInOrder();
		
		System.out.println(data);
		
		if(rightNode != null)
			rightNode.printInOrder();
	}
}
