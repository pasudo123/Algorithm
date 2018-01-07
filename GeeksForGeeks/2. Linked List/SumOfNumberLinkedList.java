package GeeksforGeeks;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class SumOfNumberLinkedList {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			
			int size1 = Integer.parseInt(st.nextToken());
			int size2 = Integer.parseInt(st.nextToken());
			
			NumberNode node1 = new NumberNode();
			NumberNode node2 = new NumberNode();
			
			st = new StringTokenizer(br.readLine());
			int count1 = st.countTokens();
			node1.size = count1;
			
			for(int i = 0; i < size1; i++)
				node1.insertNode(Integer.parseInt(st.nextToken()));
			
			st = new StringTokenizer(br.readLine());
			int count2 = st.countTokens();
			node2.size = count2;
			
			for(int i = 0; i < size2; i++)
				node2.insertNode(Integer.parseInt(st.nextToken()));
			
			BigInteger toNumber1 = node1.listToNumber();
			BigInteger toNumber2 = node2.listToNumber();
			BigInteger sum = toNumber1.add(toNumber2);
			
			char[]sumCharArr = sum.toString().toCharArray();
			if(count1 < count2 && sumCharArr.length != count2 || count1 > count2 && sumCharArr.length != count1)
				bw.write("0" + " ");
			
			for(char c : sumCharArr)
				bw.write(String.valueOf(c) + ' ');
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class NumberNode{
		int number;
		NumberNode next;
		int size = 0;
		
		NumberNode(){
			this.number = 0;
			this.next = null;
		}
		
		void insertNode(int n){
			if(this.next == null){
				this.number = n;
				this.next = new NumberNode();
				return;
			}
			
			NumberNode head = this;
			NumberNode current = head;
			
			while(current.next != null){
				current = current.next;
			}
			
			current.number = n;
			current.next = new NumberNode();
		}
		
		// -- PASUDO : Execution Time:0.67
		BigInteger listToNumber(){
			NumberNode currentNode = this;
			
			BigInteger result = new BigInteger("0");
			BigInteger ten = new BigInteger("10");
			
			while(currentNode.next != null){
				int number = currentNode.number;
				// 10의 제곱승으로 자릿수 더함.
				// 자릿수에서 해당 수를 곱한 값을 계속 더해감.
				ten = ten.pow(--size);
				result = result.add(ten.multiply(new BigInteger(String.valueOf(number))));
//				System.out.println(result);
				currentNode = currentNode.next;
				ten = new BigInteger("10");
			}
			
			return result;
		}
	}
}
