package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class PythagoreanTripletArray {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);

	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);

	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int size = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[]array = new int[size];
			
			for(int i = 0; i < size; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
//			if(PASUDO_Solution(array))
//				bw.write("Yes" + "\n");
//			else
//				bw.write("No" + "\n");
			
			if(GFG_Solution(array))
				bw.write("Yes" + "\n");
			else
				bw.write("No" + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// -- PASUDO : Execution Time:0.08
	public static boolean PASUDO_Solution(int array[]){
		Arrays.sort(array);
		
		for(int i = 0; i < array.length; i++)
			array[i] = (int)Math.pow(array[i], 2); // 제곱
		
		// Native : O(n^3) 
		for(int i = 0; i < array.length - 2; i++){
			for(int j = i + 1; j < array.length - 1; j++){
				int pythaValue = array[i] + array[j];
				
				for(int k = j + 1; k < array.length; k++){
					if(pythaValue > array[k])
						continue;
					else if(pythaValue == array[k])
						return true;
					else //(pythaValue < array[k])
						break;
				}
			}
		}
		
		return false;
	}
	
	// -- GFG : Execution Time:0.09
	public static boolean GFG_Solution(int array[]){
		Arrays.sort(array);
		
		for(int i = 0; i < array.length; i++)
			array[i] = (int)Math.pow(array[i], 2); // 제곱
		
		// 수들은 정렬되어있으며 제곱인 상태.
		// ex) 1 4 9 16 25 36 49 ...
		// Use Sorting & Square : O(n^2)
		for(int i = array.length-1; i >= 2; i--){
			int left = 0;
			int right = i - 1;
			
			while(left < right){
				if(array[left] + array[right] == array[i])
					return true;
				else if(array[left] + array[right] < array[i]){
					left++;	// 인덱스를 늘림으로써, 합한 값을 증가시킨다.
				}
				else{	// array[left] + array[right] > array[i];
					right--;// 인덱스를 줄임으로써, 합한 값을 감소시킨다.
				}
			}
		}
		
		return false;
	}
}
