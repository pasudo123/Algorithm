package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class CountTriplets {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[]args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < TC; i++){
			st = new StringTokenizer(br.readLine());
			
			int size = Integer.parseInt(st.nextToken());
			int standardSum = Integer.parseInt(st.nextToken());
			
			int [] array = new int[size];
			
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < size; j++)
				array[j] = Integer.parseInt(st.nextToken());
			
//			bw.write(PASUDO_Solution(array, standardSum) + "\n");
			bw.write(OptimazationSolution(array, standardSum) + "\n");
		}
		
		bw.flush();
	}
	
	// -- PASUDO : Expected Time Limit < 1.4sec 
	public static int PASUDO_Solution(int[]array, int standardSum){
		int result = 0;
		
//		for(int i = 0; i < array.length; i++){
//			for(int j = i + 1; j < array.length; j++){
//				for(int k = j + 1; k < array.length; k++){
//					if(array[i] + array[j] + array[k] < standardSum){
////						System.out.println(i + ", " + j + ", " + k);
////						System.out.println(array[i] + ", " + array[j] + ", " + array[k]);
////						System.out.println();
//						result++;
//					}// if
//				}// for
//			}// for
//		}// for
		
		return result;
	}
	
	// GFG Code Optimazation : Execution Time:0.22
	public static int OptimazationSolution(int[]array, int standardSum){
		int result = 0;
		
		// ** 오름차순 정렬 (매우 중요)
		Arrays.sort(array);

		// 세개의 항을 sum 해야하기 때문에, length - 2 까지 반복문 돌린다.
		for(int start = 0; start < array.length - 2; start++){
			
			int left = start + 1;	// left 항 
			int right = array.length - 1;	// right 항 
			
			while(left < right){
				// 세개의 항이 모두 더해진경우 기준 sum 보다 크기 여부 확인
				if(array[start] + array[left] + array[right] >= standardSum)
					right--;
				// 작으면 해당 인덱스를 빼주어 하나씩 계산하지 한번에 갯수를 파악한다.
				// 단순 ++ 증가 연산자로 하나씩 추가시키지 않는다.
				// 이후에 left 항을 하나 추가한다.
				else{
					result += right - left;
					left++;
				}
			}
		}
		
		return result;
	}
}
