package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class BOJ11052 {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedReader br = new BufferedReader(isr);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[]args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[]dp = new int[N+1];
		int[]p = new int[N+1];
		long max = Integer.MIN_VALUE;
		Arrays.fill(dp, 0);
		Arrays.fill(p, 0);
		
		for(int i = 0; i < N; i++){
			p[i+1] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] = p[1];
		
		/*
		 * 붕어빵을 묶음으로 팔 때 가장 이익이 많이 남는 최대 값을 확인
		 * p[i]  : 붕어빵 i 묶음 팔 때 가격
		 * dp[i] : 붕어빵 i 묶음으로 팔 때의 최대 값
		 * 
		 * 붕어빵 한 개 : dp[1];
		 * 붕어빵 두 개 : 한 개 팔 떄 이익 최대값 + 한 개 팔 때 이익 값 , 붕어빵 두 묶음 가격
		 * 붕어빵 세 개 : 두 개 팔 때 이익 최대값 + 한 개 팔 때 이익 값 , 붕어빵 세 묶음 가격
		 * ...
		 * */
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				if(i >= j)
					dp[i] = Math.max(dp[i], dp[i-j] + p[j]);
			}
		}
		
		System.out.println(dp[N]);
	}
}
