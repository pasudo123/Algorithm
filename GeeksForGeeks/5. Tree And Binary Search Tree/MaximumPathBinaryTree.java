package GeeksforGeeks;

import java.io.*;
// 해당 패키지 내의 클래스 내의 노드 타입을 포함, 임포트
import GeeksforGeeks.MaximumPathTree.Node;

class MaximumPathTree{
	Node root;
	
	class Node{
		int data;
		Node left;
		Node right;
	}
	
	public MaximumPathTree(){
		this.root = null;
	}
	
	public void init(int data){
		this.root = new Node();
		this.root.data = data;
		this.root.left = null;
		this.root.right = null;
	}
	
	public Node nodeInit(Node node, int data){
		node = new Node();
		node.data = data;
		node.left = null;
		node.right = null;
		
		return node;
	}
}

public class MaximumPathBinaryTree {
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	public static void main(String[]args) throws IOException{
		MaximumPathTree tree = new MaximumPathTree();
		
		
		// 전체 트리 초기화
		tree.init(10);
//		System.out.println(tree.root.data);
		tree.root.left = tree.nodeInit(tree.root.left, 2);
		tree.root.right = tree.nodeInit(tree.root.right, 10);
//		System.out.println(tree.root.left.data);
//		System.out.println(tree.root.right.data);
		
		tree.root.left.left = tree.nodeInit(tree.root.left.left, 20);
		tree.root.left.right = tree.nodeInit(tree.root.left.right, 1);
		tree.root.right.right = tree.nodeInit(tree.root.right.right, -25);
		tree.root.right.right.left = tree.nodeInit(tree.root.right.right.left, 3);
		tree.root.right.right.right = tree.nodeInit(tree.root.right.right.right, 4);
		
		// 배열형태로 참조형 레퍼런스를 만들어 힙영역에 저장
		// call by reference 하기 위함
		// LeetCode 참조 : https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39775
		int maxObject[] = new int[1];
		maxObject[0] = Integer.MIN_VALUE;
		
		getMaxPath(tree.root, maxObject);
		bw.write(maxObject[0] + "\n");
		bw.flush();
		bw.close();
	}
	
	static int getMaxPath(Node node, int[]max){
		if(node == null)
			return 0;
		
		/*
		 * leftSum : 왼쪽 가지의 데이터 값 전체
		 * rightSum : 오른쪽 가지의 데이터 값 전체
		 * max[0] : 현재 루트가 최종 루트인지에 대한 여부를 확인하는 값으로, 
		 * 현재 노드의 값과 분기하는 왼쪽 가지의 전체 값 그리고 분기하는 오른쪽 가지의 전체 값을 더해서 갱신
		 * 
		 * return 되는 내용은 현재의 노드 값에서 분기되는 왼쪽 오른쪽 중에 더 큰 값을 취하기 위함.
		 * 
		 * --------------
		 * 계속 보다 보니 나름의 이해를 가지게 된거 같은데, 
		 * leftSum 은 분기하는 왼쪽 가지의 데이터 값 전체를 의미하고,
		 * rightSum 은 분기하는 오른쪽 가지의 데이터 값 전체를 의미하는 것이라고 생각되고
		 * 이후에 max[0] 이건 현재 루트가 루트노드이면 그대로 갱신되서 리턴될것이고 루트노드가 아니면, 
		 * 하위 노드의 부모노드에서 값의 갱신 때문에 존재한다고 생각되네요.
		 * 
		 * 마지막으로 리턴되는 것은 현재 노드 값에서 분기되는 왼쪽과 오른쪽 가지중에서 더 큰 값을 취하기 위함인거 같구여..  
		 * */
		int leftSum = max(getMaxPath(node.left, max), 0);
		int rightSum = max(getMaxPath(node.right, max), 0);
		
		max[0] = max(node.data + leftSum + rightSum, max[0]);
		
		return node.data + max(leftSum, rightSum);
	}
	
	static int max(int a, int b){
		return (a > b)? a : b;
	}
}
