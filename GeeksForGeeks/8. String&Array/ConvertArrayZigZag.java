package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class ConvertArrayZigZag {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[]args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int size = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int[]array = new int[size];
			
			for(int i = 0; i < array.length; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
//			bw.write(PASUDO_Solution(array) + "\n");
			bw.write(OptimazationSolution(array) + "\n");
			bw.flush();
		}
	}
	
	// -- PASUDO : Wrong Answer 
	public static String PASUDO_Solution(int[]array){
		String answer = new String();
		
		// O(nlogn)
		Arrays.sort(array);	
		int start = 0;				// index 로 접근
		int end = array.length-1;	// ""
		boolean flag = true;
		
		if(array.length % 2 == 0)	// 짝수
			flag = true;
		else{						// 홀수
			start = 1;
			flag = false;
		}
		
		// O(n) 
		while(start < end){
			if(flag){
				flag = false;
			}
			else{
				int temp = array[start];
				array[start] = array[end];
				array[end] = temp;
				
				flag = true;
			}
			
			start++;
			end--;
		}
		
		for(int element : array)
			answer += element + " ";
		
		return answer;
	}
	
	// -- GFG Code Optimazation : Execution Time:0.1
	public static String OptimazationSolution(int[]array){
		boolean flag = true;
		String res = "";
		
		// O(n)
		// 초기 flag 변수는 true 이다.
		// 한번의 반복으로 인해서, 버블소트 형태로 하나씩 지그재그 형태의 값을 구현한다.
		for(int index = 0; index < array.length - 1; index++){
			if(flag){
				if(array[index] > array[index+1]){
					int temp = array[index];
					array[index] = array[index+1];
					array[index+1] = temp;
				}
			}
			else{
				if(array[index] < array[index+1]){
					int temp = array[index];
					array[index] = array[index+1];
					array[index+1] = temp;
				}
			}
			
			// flag 변수를 뒤집는다.
			flag = !flag;
		}
		
		for(int element : array)
			res += element + " ";
		
//		System.out.println(Arrays.toString(array));
		return res;
	}
}
