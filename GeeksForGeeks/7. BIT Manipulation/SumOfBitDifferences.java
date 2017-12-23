package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfBitDifferences {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);

	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);

	public static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int size = Integer.parseInt(br.readLine());
			int array[] = new int[size];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
			bw.write(PASUDO_Solution(array) + "\n");
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	// -- PASUDO : Execution Time:0.09
	public static int PASUDO_Solution(int[]array){
		int mod = (int)Math.pow(10, 9) + 7;
		int diff = 0;
		int res = 0;
		
		// 배열의 모든 값에 대한 쌍을 확인
		// 동일한 값은 어차피 different Bit 가 0개 존재이기 떄문에 패스
		for(int i = 0; i < array.length-1; i++){
			for(int j = i+1; j < array.length; j++){
				int value1 = array[i];
				int value2 = array[j];
				
				// XOR 연산
				// 서로 다른 비트는 1이 나옴
				int XOR_Value = value1^value2;
				
				while(XOR_Value != 0){
					if((XOR_Value & 1) == 1){
						diff++;
					}
					
					// 오르쪽 시프트 연산자.
					// 전체 비트를 오른쪽으로 한칸씩 이동. 점차 줄여나감.
					XOR_Value = XOR_Value >> 1;
				}
				
				res = (res + diff * 2) % mod;
				diff = 0;
			}// for
		}// for
	
		return res;
	}
}
