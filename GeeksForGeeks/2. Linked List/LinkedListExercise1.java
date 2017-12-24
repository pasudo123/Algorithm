package GeeksforGeeks;

import GeeksforGeeks.LinkedList.Node;

class LinkedList{
	Node head;
	
	class Node{
		int data;
		Node next;
		
		public Node(int data){
			this.data = data;
			this.next = null;
		}
	}
	
	// 정렬된 상태의 삽입
	public void sortedInsert(Node newNode){
		Node currentNode;
		
		// 해드노드 확인.
		if(head == null || head.data >= newNode.data){
			newNode.next = head;
			head = newNode;
		}
		
		// 헤드노드 존재한다면, 
		else{
			currentNode = head;
			
			// Sorting 상태로 확인 : O(n)
			// 새로운 노드의 데이터가 최근 노드 다음의 데이터보다 크다면, 
			// 최근 노드에 
			while(currentNode.next != null && currentNode.next.data < newNode.data)
				currentNode = currentNode.next;
			
			// O(1)
			newNode.next = currentNode.next;
			currentNode.next = newNode;
		}
	}
	
	public Node createNewNode(int data){
		Node x = new Node(data);
		return x;
	}
	
	// 단방향 연결리스트 출력
	public void printList(){
		Node temp = head;
		
		while(temp != null){
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
	}
}

public class LinkedListExercise1 {
	public static void main(String[]args){
		LinkedList llist = new LinkedList();
		Node newNode;
		newNode = llist.createNewNode(10);
		llist.sortedInsert(newNode);
		
		newNode = llist.createNewNode(5);
		llist.sortedInsert(newNode);
		
		
		llist.printList();
	}
}
