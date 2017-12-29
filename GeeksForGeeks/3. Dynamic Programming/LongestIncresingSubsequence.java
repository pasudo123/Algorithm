package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LongestIncresingSubsequence {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[]args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int size = Integer.parseInt(br.readLine());
			int[]array = new int[size];
			st = new StringTokenizer(br.readLine());
//			int size = 9;
//			int[]array = {10, 22, 9, 33, 21, 50, 41, 60, 80};
			
			for(int i = 0; i < size; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
//			bw.write(PASUDO_Solution(array) + "\n");
			bw.write(OptimazationSolutioin(array) + "\n");
		}
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	
	// Basic Solution, O(n^2), Memoization : Execution Time:0.09
	// 굉장히 비효율적 방식.
	public static int PASUDO_Solution(int[]array){
		int[]dp = new int[array.length];
		int max = Integer.MIN_VALUE;
		
		/*
		 * (1) array 배열에서 하나의 값 선택 dp[i]
		 * (2) dp[i] : i 인덱스에서 가질 수 있는 최장 증가 수열 (LIS)
		 * (3) i 인덱스를 기준으로 이전 인덱스 0 ~ (i-1) 인덱스를 검사
		 * */
		for(int i = 0; i < array.length; i++){
			dp[i] = 1;
			
			for(int j = 0; j < i; j++){
				if(array[i] > array[j] && dp[i] < dp[j] + 1){
					dp[i] = dp[j] + 1;
				}
			}
			
			if(dp[i] > max)
				max = dp[i];
		}
		
		if(max == Integer.MIN_VALUE)
			max = 0;
		
		return max;
	}

	// GFG Solution, O(nLogn) : Execution Time:0.1
	// end element of smaller list is smaller than end elements of larger lists
	// reference : https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	public static int OptimazationSolutioin(int[]array){
		ArrayList<Integer>list = new ArrayList<Integer>();
		
		// O(n)
		for(int i = 0; i < array.length; i++){
			// 가장 초기에 값을 삽입한다.
			if(i == 0)
				list.add(array[i]);
			
			// 값이 더 크면 해당 리스트에 값을 삽입.
			if(list.get(list.size() - 1) < array[i])
				list.add(array[i]);
			// 값이 더 작으면 discard 및 이전 크기에서 extend 한다.
			else
				list.set(getBetweenIndexUseBinarySearch(list, -1, list.size()-1, array[i]), array[i]);
		}
		
		System.out.println(list);
		return list.size();
	}
	
	// O(nLogn)
	public static int getBetweenIndexUseBinarySearch(ArrayList<Integer>list, int left, int right, int value){
		while(right - left > 1){
			int middle = (left + right) / 2;
			
			if(list.get(middle) >= value)
				right = middle;
			else	// list.get(middle) < value;
				left = middle;
		}
		
		return right;
	}
}
