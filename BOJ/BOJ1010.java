package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1010 {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedReader br = new BufferedReader(isr);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[]args) throws IOException{
		int[][]dp = new int[30][30];
		
		for(int n = 1; n <= 29; n++)
			dp[1][n] = n;
		
		for(int n = 2; n <= 29; n++){
			for(int m = 2; m <= 29; m++){
				for(int n2 = (m-1); n2 >= 1; n2--){
					dp[n][m] += dp[n-1][n2];
				}
			}
		}
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < tc; t++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[N][M]);
		}
	}
}
