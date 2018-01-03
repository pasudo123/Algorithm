package GeeksforGeeks;

import java.util.*;
import java.io.*;

class Node{
	int data;
	
	Node(){
		this.data = 0;
	}
}

public class BinaryTree {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException{
		/*
		 * 일반적인 이진트리는 
		 * (1) parentNode.Value >= leftNode.Value
		 * (2) parentNode.value < right.Value
		 * 형태를 가지고 있다.
		 * 
		 * 하지만, 이 문제는 그러한 조건을 주지 않고 오로지 인풋의 데이터에 의존해서 이진트리를 형성하려고 한다.(비정렬)
		 * 따라서 연결리스트로 이진트리를 구성하는 것이 아닌, 배열모습으로 이진트리를 구성하는 것이 더 낫다고 판단된다.
		 * */
		
		int TC = Integer.parseInt(br.readLine());
		
		
		for(int t = 0; t < TC; t++){
			// Root Node 를 제외한 노드의 개수
			int nodeCount = Integer.parseInt(br.readLine());
			
			// 선형적으로 증가하는 노드들도 있을 수도 있기 때문에 nodeCount + 1 을 설정
			// 만약 이진트리가 균형적으로 포화상태로 혹은 완전트리 상태로 맞춰진다면 단순 nodeCount 로 설정해도 아무 이상은 없다.
			int size = (int)Math.pow(2, nodeCount+1);
			Node[]tree = new Node[size];
			for(int i = 0; i < size; i++)
				tree[i] = new Node();
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < nodeCount; i++){
				int parentValue = Integer.parseInt(st.nextToken());
				int childValue = Integer.parseInt(st.nextToken());
				char cont = st.nextToken().toCharArray()[0];
				
				int number = 1;
				
				// O(n) 
				// 해당 노드를 넣기 위해선 배열 내에서 탐색과정을 거쳐야 한다. (null 체크)
				while(number < tree.length){
					// 가장 초기
					if(tree[1].data == 0){
						tree[1].data = parentValue;
						if(cont == 'L')
							number = 1 * 2;
						if(cont == 'R')
							number = 1 * 2 + 1;
						
						tree[number].data = childValue;
						break;
					}
					
					// 탐색 과정
					if(tree[number].data == parentValue){
						if(cont == 'L')
							number = number * 2;
						if(cont == 'R')
							number = number * 2 + 1;
						
						tree[number].data = childValue;
						break;
					}
					
					number++;
				}
			}
			
			// print 문
			System.out.println();
			for(int n = 1; n < size; n++)
				System.out.print(tree[n].data + "  ");
			System.out.println();
			
			// 루트노드부터 탐색
			System.out.println(getMinDepth(tree, 1));
		}
	}
	
	// 이진트리 최소 높이 구하기
	static int getMinDepth(Node[]tree, int index){
		
		if(tree[index].data == 0)
			return 0;
		
		int left = index * 2;
		int right = index * 2 + 1;
		
		if(left < tree.length && right < tree.length){
			if(tree[left].data == 0 && tree[right].data == 0)
				return 1;
			
			if(tree[left].data == 0)
				return getMinDepth(tree, right) + 1;
		
			if(tree[right].data == 0)
				return getMinDepth(tree, left) + 1;
		
		
			return Math.min(getMinDepth(tree, left), getMinDepth(tree, right)) + 1;
		}
		return 1;
	}
}
