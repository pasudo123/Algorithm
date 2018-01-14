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

		int leftSum = max(getMaxPath(node.left, max), 0);
		int rightSum = max(getMaxPath(node.right, max), 0);
		
		max[0] = max(node.data + leftSum + rightSum, max[0]);
		
		return node.data + max(leftSum, rightSum);
	}
	
	static int max(int a, int b){
		return (a > b)? a : b;
	}
}
