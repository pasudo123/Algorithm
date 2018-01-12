package GeeksforGeeks;

import java.util.*;
import java.io.*;
	
public class SmallestSubarray {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			
			int size = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int[]array = new int[size];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
			bw.write(optimazationGetMinimumSubarray(array, x) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// -- PASUDO : Execution Time:0.08, O(n^2)
	static int getMinimumSubarray(int[]array, int x){
		int min = Integer.MAX_VALUE;
		int arraySum = 0;
		int minimumLength = 0;
		
		for(int i = 0; i < array.length; i++){
			
			minimumLength = 0; 	// 배열길이
			arraySum = 0;		// 배열합계
			
			for(int j = i; j < array.length; j++){
				arraySum += array[j];
				minimumLength += 1;
				
				// 배열의 합계 > x && 최소길이 > 배열길이
				if(arraySum > x && min > minimumLength){
					min = minimumLength;
					break;
				}
				
				// 최소길이 < 배열길이 
				if(minimumLength > min)
					break;
			}
		}
		
		return min;
	}
	
	
	// -- Optimazation : Execution Time:0.08 O(n)
	// 캐터필러 알고리즘이라고 한다.
	static int optimazationGetMinimumSubarray(int[]array, int x){
		int currentSum = 0;
		int minLength = Integer.MAX_VALUE;
		int start = 0, end = 0;
		// left, right
		
		while(end < array.length){
			
			// currentSum이 주어진 x 보다 작거나 같은 경우 && end가 인덱스 내 포함 (while)
			while(currentSum <= x && end < array.length){
				currentSum += array[end];
				end += 1;
				
				// currentSum 이 0보다 작거나 같은 경우, 음수를 만난경우
				// 출발점은 갱신되고 다시 while 을 시작한다.
				if(currentSum <= 0){
					start = end;
					currentSum = 0;
				}// if
			}// while
			
			// 첫번째 while문을 벗어나고 이후에 x 보다 큰 경우 계속 돌아간다.
			// end는 현재 위치가 아닌 그 이후의 위치를 가리킨다. 후위 연산자 때문에
			while(currentSum > x && start < array.length){
				if(end - start < minLength)
					minLength = end-start;
				
				currentSum -= array[start++];
			}// while
		}
		
		return minLength;
	}
}
