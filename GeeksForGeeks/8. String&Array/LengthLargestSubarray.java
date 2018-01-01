package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class LengthLargestSubarray {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException {
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			int size = st.countTokens();
			
			int[]array = new int[size];
			
			for(int i = 0; i < size; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
//			bw.write(PASUDO_Solution(array) + "\n");
			bw.write(GFG_AnotherSolution(array) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	// -- PASUDO : O(n^2)
	public static int PASUDO_Solution(int[]array){
		
		int minValue = 0;
		int maxValue = 0;
		int subSize = 0;
		
		for(int i = 0; i < array.length - 1; i++){
			
			minValue = array[i];
			maxValue = array[i];
			
			for(int j = i + 1; j < array.length; j++){
				minValue = Math.min(minValue, array[j]);
				maxValue = Math.max(maxValue, array[j]);
				
				// 인덱스가 0부터 시작하기 때문에
				// j-1+1 이 아닌, j-i 로 접근. 
				// 개수는 j-i+1로 삽입.
				if((maxValue - minValue) == (j - i)){
					if(subSize < j - i + 1)
						subSize = j - i + 1;
				}
			}
		}
		
		return subSize;
	}
	
	
	// -- GFG Another Solution : Copy & Sorted & HashMap 
	// Java 의 Arrays.sort() 알고리즘은 기본적으로 머지 소트를 따른다. : O(nlogn)
	public static int GFG_AnotherSolution(int[]array){
		
		// (1) Copy & Sorted : O(n) + O(nlogn)
		int[]copyArray = Arrays.copyOf(array, array.length);
		Arrays.sort(copyArray);	
		
		// (2) Grouping
		int groupNumber = 1;
		int value = copyArray[0];
		Map<Integer, Integer>map = new HashMap<Integer, Integer>();
		map.put(value, groupNumber);
		
		for(int i = 1; i < copyArray.length; i++){
			if(copyArray[i] - 1 == value){
				value = copyArray[i];
				map.put(value, groupNumber);
				
				continue;
			}
			
			// 다른 경우
			value = copyArray[i];
			map.put(value, ++groupNumber);
		}
		
//		System.out.println(Arrays.toString(copyArray));
//		System.out.println(map.toString());
		
		// (3) Marking : O(n)
		int key = map.get(array[0]);
		int count = 1;
		int maxCount = 0;
		
		for(int i = 1; i < array.length; i++){
			if(map.get(array[i]) == key){
				count++;
				continue;
			}
			
			// 그룹핑이 다른 경우
			if(count > maxCount)
				maxCount = count;
			
			key = map.get(array[i]);
			count = 1;
		}
		
		return maxCount;
	}
}
