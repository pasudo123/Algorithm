package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LongestCommonSubsequence {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);

	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);

	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			int firSize = Integer.parseInt(st.nextToken());
			int secSize = Integer.parseInt(st.nextToken());
			
			char[]line1 = new char[firSize];
			char[]line2 = new char[secSize];
			
			line1 = new String(br.readLine()).toCharArray();
			line2 = new String(br.readLine()).toCharArray();
			
			bw.write(PAUSOD_Solution(line1, line2) + "\n");
//			bw.write(LCS(line1, line2, line1.length, line2.length) + "\n");
		}
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	// -- PASUDO : Execution Time:0.08
	// Memoization 형태로 푸는 것이 가장 이상적
	public static int PAUSOD_Solution(char[]line1, char[]line2){
		int[][]dp = new int[101][101];
		StringBuilder resBuilder = new StringBuilder();
		
		for(int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], 0);
		
//		System.out.println(Arrays.toString(line1));
//		System.out.println(Arrays.toString(line2));
		
		for(int i = 1; i <= line2.length; i++){
			for(int j = 1; j <= line1.length; j++){
				if(line2[i-1] == line1[j-1]){
					dp[i][j] = dp[i-1][j-1] + 1;
//					resBuilder.append(line1[j-1]);
				}
				else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		
//		arrayPrint(dp, line2.length, line1.length);
//		System.out.println(resBuilder);
		return dp[line2.length][line1.length];
	}
	
	// -- GFG : Time Limit O(2^n) 
	// Recursive
	public static int LCS(char[]X, char[]Y, int m, int n){
		if(m == 0 || n == 0)
			return 0;
		if(X[m-1] == Y[n-1])
			return 1 + LCS(X, Y, m-1, n-1);
		else
			return Math.max(LCS(X, Y, m, n-1), LCS(X, Y, m-1, n));
	}
	
	public static void arrayPrint(int[][]array, int row, int col){
		for(int i = 1; i <= row; i++){
			for(int j = 1; j <= col; j++){
				System.out.printf("%-3d", array[i][j]);
			}
			System.out.println();
		}
	}
}
