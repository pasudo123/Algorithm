package GeeksforGeeks;

import java.io.*;
import java.util.*;

public class ChineseRemainderTheorem {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int[]num = {3, 4, 5};
		int[]rem = {2, 3, 1};
	
		/*
		 * num 배열의 모든 쌍은 서로소 ( 최대공약수가 1 )
		 * */
		
//		bw.write(nativeProcess(num, rem) + "\n");
		bw.write(chineseRemainder(num, rem) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 순차적인 접근
	static int nativeProcess(int[]num, int[]rem){
		int size = num.length;
		int x = 1;
		int i = 0;
		
		while(true){
			for(i = 0; i < size; i++)
				if(x % num[i] != rem[i])
					break;
			
			if(i == size)
				break;
			
			x++;
		}
		
		return x;
	}
	
	// reference 
	// - https://namu.wiki/w/%EC%A4%91%EA%B5%AD%EC%9D%B8%EC%9D%98%20%EB%82%98%EB%A8%B8%EC%A7%80%20%EC%A0%95%EB%A6%AC
	static int chineseRemainder(int[]num, int[]rem){
		
		int M = 1;
		for(int i = 0; i < num.length; i++)
			M = M * num[i];
		
		int[]pairMultiple = new int[num.length];
		Arrays.fill(pairMultiple, 1);
		for(int i = 0; i < num.length; i++){
			for(int j = 0; j < num.length; j++){
				if(i == j)
					continue;
				
				// 서로소 곱
				pairMultiple[i] *= num[j]; 
			}
		}
		
		int inv[] = new int[num.length];
		int s = 1;
		for(int i = 0; i < num.length; i++){
			while(true){
				// 서로소의 곱과 해당 num[i] 또한 서로소이기 때문에 약수는 최대공약수가 1이다.
				// 합동식 표현에 의해서 이렇게 나온다.
				if((pairMultiple[i]*s) % num[i] == 1)
					break;
				s++;
			}
			
			inv[i] = s;
		}
		
		System.out.println("num : " + Arrays.toString(num));
		System.out.println("rem : " + Arrays.toString(rem));
		System.out.println("M : " + M);
		System.out.println("pairMultiple : " + Arrays.toString(pairMultiple));
		System.out.println("inv : " + Arrays.toString(inv));

		/*
		 * (1) 해당 수를 제외한 서로소의 곱을 구한다.
		 * (2) (1)에서 구한 값과 해당 수를 제외한 값은 서로소이다. 그렇기 때문에 최대공약수는 1을 가진다.
		 * (3) (2)에 해당하는 배수의 값을 inv에 삽입한다.
		 * (4) 연립합동방적식으로 문제를 해결
		 * */
		int x = 0;
		for(int i = 0; i < num.length; i++)
			x += (pairMultiple[i] * rem[i] * inv[i]);
		
		x = x % M;
		
		return x;
	}
}
