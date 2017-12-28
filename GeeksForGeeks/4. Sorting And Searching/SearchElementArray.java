package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SearchElementArray {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);

	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);

	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			
			int size = Integer.parseInt(st.nextToken());
			int findNumber = Integer.parseInt(st.nextToken());
			int[]array = new int[size];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
			int res = 0;
			
			
			// 일반적인 풀이 (문제 없음) : Linear 하게 찾는 방식이다.
//			if((res = PASUDO_Solution(array, findNumber)) == -1){
//				System.out.println("OOPS! NOT FOUND");
//			}
//			else{
//				System.out.println(res);
//			}
			// *************************************************************
			
			
			// 전혀 다른 풀이 방식이다.
			// GeeksforGeeks 풀이 : Execution Time:0.1
//			if((res = SearchElementArrayBinarySearch.searchBitonic(array, findNumber)) == -1){
//				System.out.println("OOPS! NOT FOUND");
//			}
//			else{
//				System.out.println(res);
//			}
			// *************************************************************
			
			
			// GeeksforGeeks 풀이 : 
//			if((res = GFG_ImproveSolution(array, 0, array.length-1, findNumber)) == -1){
//				System.out.println("OOPS! NOT FOUND");
//			}
//			else{
//				System.out.println(res);
//			}
			// *************************************************************
		}
	}
	
	// -- PASUDO : Execution Time:0.12
	public static int PASUDO_Solution(int[]array, int findNumber){
		int answer = -1;
		
		// O(n) : Execution Time:0.1
		for(int i = 0; i < array.length; i++){
			if(array[i] == findNumber)
				return i;
		}
		
		return answer;
	}
		
	
	// Bitonic 시퀀스에는 맞지 않다. 
	// 선형적으로 증가하는 배열에 대해서 rotate 가 일어난 경우만 해당되는 메소드
	public static int GFG_pivotedBinarySearch(int[]array, int findNumber){
		// 피봇을 찾는다.
		int pivot = GFG_findPivot(array, 0, array.length-1);
		
		System.out.println("pivot : " + pivot);
		System.out.println("value about pivot : " + array[pivot]);
		
		// 피봇을 찾지 못했다는 의미
		// 배열은 올바르게 정렬되어있음을 말함.
		if(pivot == -1){
			return Arrays.binarySearch(array, findNumber);
		}
		
		if(array[pivot] == findNumber)
			return pivot;
		if(array[0] <= findNumber)
			return SearchElementArrayBinarySearch.PASUDO_AscIterativeBinarySearch(array, 0, pivot-1, findNumber);
		
		return SearchElementArrayBinarySearch.PASUDO_DesIterativeBinarySearch(array, pivot+1, array.length-1, findNumber);
	}
	
	/*
	 * 피벗을 찾고 두 개의 하위 배열로 배열을 나눈 다음 바이너리 서치를 하는 것이다.
	 * O(logn)	
	 */
	public static int GFG_findPivot(int[]array, int left, int right){
		if(right < left)
			return -1;
		if (left == right)
			return left;
		
		int middle = (left + right) / 2;
		
		// 중간 인덱스는 오른쪽 인데스보다 작다. 
		// 중간 값은 중간 위치 오른 쪽 한칸 보다 크다.
		// --> Rotate 상태 여부 파악
		if(middle < right && array[middle] > array[middle + 1])
			return middle;
		
		// 중간 인덱스는 왼쪽 인덱스보다 크다.
		// 중간 값은 중간 위치 왼 쪽 한칸 보다 작다.
		// --> Rotate 상태 여부 파악
		if(middle > left && array[middle] < array[middle - 1])
			return (middle - 1);
		
		// 또는 왼쪽 값이 중간 값보다 크거나 같다.
		// --> Rotate 상태 여부 파악
		if(array[left] >= array[middle])
			return GFG_findPivot(array, left, middle - 1);
		
		// 위 3개의 if문을 다 통과한다는 의미는
		// 해당 middle 이 속한 위치는 오름차순(정상적인) 배열 
		return GFG_findPivot(array, middle + 1, right);
	}
	
	public static int GFG_ImproveSolution(int[]array, int left, int right, int findNumber){
		if(right < left)
			return -1;
		
		int middle = (left + right) / 2;
		
		if(array[middle] == findNumber)
			return middle;
		
		// Sorted : array[left ... middle] 정렬되어 있는 상태
		if(array[left] <= array[middle]){
			
			// 찾고자 하는 숫자가, 해당 left ~ middle 사이에 존재하는 경우
			if(findNumber >= array[left] && findNumber <= array[middle])
				return GFG_ImproveSolution(array, left, middle - 1, findNumber);
			
			// 찾고자 하는 숫자가, 해당 (middle + 1) ~ right 사이에 존재하는 경우
			return GFG_ImproveSolution(array, middle + 1, right, findNumber);
		}
		
		// Not Sorted : array[ left ... middle ] 정렬되어 있지 않은 상태
		// 그러면, array[ (middle + 1) ... right ] 는 졍렬되어 있어야 한다.
		// 
		// WHY? 
		// 
		// 정렬되어 있는 배열이지만 Rotate 되었기 때문이다.
		if(findNumber >= array[middle] && findNumber <= array[right])
			return GFG_ImproveSolution(array, middle + 1, right, findNumber);
		
		return GFG_ImproveSolution(array, left, middle - 1, findNumber);
	}
}
