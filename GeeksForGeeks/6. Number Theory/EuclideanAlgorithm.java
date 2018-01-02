package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class EuclideanAlgorithm {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int size = Integer.parseInt(br.readLine());
			int[]array = new int[size];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i++){
				array[i] = Integer.parseInt(st.nextToken());
			}
			
			int res = 0;
			Arrays.sort(array);
			res = array[0];
			
//			for(int i = 1; i < size; i++){
//				res = gcd(res, array[i]);
//			}
			
			for(int i = 1; i < size; i++){
				res = extendedGCD(res, array[i]);
			}
			
			bw.write(res + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();	
	}
	
	
	// -- GCD ( Greatest Common Divisor )
	// -- PASUDO : O(Log GCD(a, b)) : Execution Time:0.07
	static int gcd(int a, int b){
		if(a == 0)
			return b;
		
		return gcd(b%a, a);
	}
	
	
	/*
	 * x, y 에 대한 부정방정식 ax + by = c 는 c 의 값이 gcd(a, b) 의 배수일 때만 정수해를 갖는다.
	 * 즉, ax + by = c 가 정수 해를 갖는  c 의 최소값이 gcd(a, b) 가 되는 것이다.
	 * 따라서 확장 유클리드 알고리즘은 말 그대로 유클리드 알고리즘을 확장하여, 
	 * a, b 에 대한 최대공약수 뿐만 아니라 ax + by = gcd(a, b) 를 만족하는 정수해 x, y 도 구하는 알고리즘이다.
	 * 
	 * reference : http://codepractice.tistory.com/79
	 * */
	// -- Extended GCD : Execution Time:0.05
	static int extendedGCD(int a, int b){
		
		if(a < b){
			int temp = a;
			a = b;
			b = temp;
		}
		
		int q = 0;		// 몫
		int r0 = 0;		// a 와 b 적용 값.
		int r1 = a;		// 초기 a 값
		int r2 = b;		// 초기 b 값
		int t0 = 1;
		
		while(t0 != 0){
			q = r1 / r2;	// a > b
			r0 = r1 - r2 * q;
			
			if(r0 == 0)
				return r2;
			
			q = r2 / r0;
			t0 = r2 - r0 * q;
			
			r1 = r0;
			r2 = t0;
		}
		
		return r1;
	}
}
