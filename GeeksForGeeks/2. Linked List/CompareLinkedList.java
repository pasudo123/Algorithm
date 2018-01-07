package GeeksforGeeks;

import java.util.*;
import java.io.*;

class CompareNode{
	char c;
	CompareNode nextNode;
	int size = 0;
	
	CompareNode(){}
	
	// 입력
	void insertData(char c){
		if(nextNode == null){
			// 값 삽입 이후, 다음 링크를 만든다.
			this.c = c;	
			nextNode = new CompareNode();
			return;
		}
		
		CompareNode headNode = this;
		CompareNode currentNode = headNode.nextNode;
		
		while(currentNode.nextNode != null)
			currentNode = currentNode.nextNode;
		
		currentNode.c = c;
		currentNode.nextNode = new CompareNode();
		headNode.size++;
	}
	
	// 출력
	void printData(){
		if(nextNode == null){
			System.out.println("Not created LinkedList");
			return;
		}
		
		CompareNode headNode = this;
		CompareNode currentNode = headNode.nextNode;
		
		// 첫 노드 값
		System.out.print(headNode.c + " ㅡㅡ> ");
		while(currentNode.nextNode != null){
			System.out.print(currentNode.c + " ㅡㅡ> ");
			currentNode = currentNode.nextNode;
		}
		System.out.println();
	}
}

public class CompareLinkedList {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int size = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			CompareNode cNode1 = new CompareNode();
			for(int i = 0; i < size; i++)
				cNode1.insertData(st.nextToken().charAt(0));
			
			size = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			CompareNode cNode2 = new CompareNode();
			for(int i = 0; i < size; i++)
				cNode2.insertData(st.nextToken().charAt(0));
			
			bw.write(compareTo(cNode1, cNode2) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// -- PASUDO : Execution Time:0.17
	static int compareTo(CompareNode node1, CompareNode node2){
		CompareNode c1 = node1;
		CompareNode c2 = node2;
		
		// c1 & c2 노드 둘 중의 노드 중에서, 
		// 하나라도 널이면 반복 벗어남
		while((c1 != null && c2 != null)){
			int check = (int)(c1.c - c2.c);
			
			if(check > 0)
				return 1;
			
			if(check < 0)
				return -1;
			
			c1 = c1.nextNode;
			c2 = c2.nextNode;
		}
		
		if(c1 == null && c2 != null)
			return 1;
		if(c2 == null && c1 != null)
			return -1;
		
		return 0;
	}
}
