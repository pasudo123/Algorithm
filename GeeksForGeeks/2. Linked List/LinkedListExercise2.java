package GeeksforGeeks;

import GeeksforGeeks.LinkedList2.Node;

class LinkedList2{
	Node head;
	
	static class Node{
		int data;
		Node next;
		
		public Node(int data){
			this.data = data;
			this.next = null;
		}
	}
	
	// 첫번째 파라미터 : 헤드 노드
	// 두번째 파라미터 : 삭제 대상 노드
	public void deleteNode(Node node, Node n){
		// node == head 라는 의미
		if(node == n){
			if(node.next == null){
				System.out.println("현재 노드는 헤드 노드이며, 하나의 노드밖에 없기 때문에 삭제 불가");
				return;
			}

			// 사실상 헤드 노드를 제거하는 것이 아닌,
			// 헤드 노드가 앞에 있는 노드의 값을 취하고
			// 이후에 헤드 노드의 다음 노드를 삭제하는 순서.
			
			node.data = n.next.data;
			node.next = n.next.next;
			
			/* Copy the data of next node to head */
			/* GeeksforGeeks 에서 정리된 글인데, 위의 방식이 더 이해하기 편한거 같다. */
			// node.data = node.next.data;

			// store address of next node
			// n = node.next;

			// Remove the link of next node
			// node.next = node.next.next;
            
            return;
		}
		
		// 헤드 노드를 이전 노드로 삽입.
		Node prev = node;
		
		// 링크드 리스트 탐색 : 이전 노드의 다음 링크 null 여부 && 이전 노드의 다음 링크가 삭제 노드 일치여부
		while(prev.next.next != null && prev.next != n)
			prev = prev.next;
		
		if(prev.next == null){
			System.out.println("존재하지 않는 노드");
			return;
		}
		
		prev.next = prev.next.next;
		
		return;
	}
		
	// 단방향 연결리스트 출력
	public void printList(Node head){
		Node temp = head;
		
		while(temp != null){
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
	}
}

public class LinkedListExercise2 {
	public static void main(String[]args){
		LinkedList2 list = new LinkedList2();
		list.head = new Node(12);		// Inner class 이기때문에 static 으로 접근.
		list.head.next = new Node(15);
		list.head.next.next = new Node(10);
		list.head.next.next.next = new Node(11);
		list.head.next.next.next.next = new Node(5);
		list.head.next.next.next.next.next = new Node(6);
		list.head.next.next.next.next.next.next = new Node(2);
		list.head.next.next.next.next.next.next.next = new Node(3);

		System.out.print("변경 전 : ");
		list.printList(list.head);
		System.out.println();

		// 10 데이터를 가진 노드 삭제.
		System.out.println("Deleting node : " + list.head.next.next.data);
		list.deleteNode(list.head, list.head.next.next);
		
		// 헤드 노드 삭제
//		list.deleteNode(list.head, list.head);

		System.out.print("변경 후 : ");
		list.printList(list.head);
		System.out.println();
	}
}
