package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class BubbleSort {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[]args) throws IOException{
		/*
		 * Bubble Sort 는 인접된 요소의 값을 비교하면서 서로 교체하는
		 * 가장 단순한 정렬 알고리즘이다. 
		 * */
		
		int TC = Integer.parseInt(br.readLine());
		for(int t = 0; t < TC; t++){
			int size = Integer.parseInt(br.readLine());
			int[]array = new int[size];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < size; i++){
				array[i] = Integer.parseInt(st.nextToken());
			}
			
//			bubbleSorting(array);
			improveBubbleSorting(array);
			
			for(int i = 0; i < array.length; i++){
				bw.write(array[i] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	// -- PASUDO : Execution Time : 0.2, O(n^2)
	// 버블소트는 기존에 정렬되어있더라도, 계속 인접한 값들을 비교하기 때문에
	// 정렬되어있는 상태의 배열도 불필요한 연산을 작업한다. 비효율적
	// 따라서 flag 변수를 둠으로써, 개선된 버블소트를 가능하게 할 수 있다.
	static void bubbleSorting(int[]array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array.length - 1 - i; j++){
				if(array[j] > array[j + 1]){
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
	
	
	// -- PASUDO(ImproveBubbleSort) : Execution Time : 0.24
	static void improveBubbleSorting(int[]array){
		boolean flag = true;
		
		for(int i = 0; i < array.length; i++){
			flag = true;

			for(int j = 0; j < array.length - 1 - i; j++){
				// 정렬해야 한다.
				if(array[j] > array[j + 1]){
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					flag = false;	// 정렬
				}
			}

			// 정렬이 일어나지 않았다. >> 소팅되어 있는 상태
			if(flag)
				break;
		}
	}
}
