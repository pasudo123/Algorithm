package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class BinaryRepresentation {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException {
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int N = Integer.parseInt(br.readLine());
			
//			bw.write(PASUDO_Solution1(N) + "\n");
//			bw.write(PASUDO_Solution2(N) + "\n");
			AnotherSolution(N);
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	// -- USE JavaAPI : Execution Time:0.04
	static String PASUDO_Solution1(int n){
		String binaryResult = Integer.toBinaryString(n);
		
		int addingSize = 14 - binaryResult.length();
		
		for(int i = 0; i < addingSize; i++)
			binaryResult = 0 + binaryResult;
		
		return binaryResult;
	}
	
	
	// -- USE Modular : Execution Time:0.06
	static String PASUDO_Solution2(int n){
		String binaryResult = new String("");
		
		while(n != 0){
			int modValue = n % 2;
			n = n / 2;
			
			binaryResult = modValue + binaryResult;
		}
		
		int addingSize = 14 - binaryResult.length();
		
		for(int i = 0; i < addingSize; i++)
			binaryResult = 0 + binaryResult;
		
		return binaryResult;
	}
	
	
	// -- GFG Another Solution : Execution Time:0.07
	static void AnotherSolution(int n) throws IOException{
		// Because, Maximum Bit is 14 
		// 13 비트 부터 시작하는 것은, 처음 자리 비트부터 차근차근 출력하기 위함.
		for(int i = 13; i >= 0; i--){
			int bit = (n >> i);	// 오른쪽 시프트 연산자 (지정된 위치의 수만큼 오른쪽으로 이동)
			bit = bit & 1;		// 우측부터 차례대로 출력하기 위함이다.
			bw.write(String.valueOf(bit));
		}
		bw.write("\n");
	}
}
