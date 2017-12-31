package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class CoverTheDistance {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException{
		/*
		 * -- Step 1, Step 2, Step 3 --
		 * dp[n] : n 값을 만드는 방법의 수
		 * dp[1] : 1 을 만드는 방법은 1
		 * dp[2] : 2 를 만드는 방법은 dp[1]에 1을 더하고, 따로 2가 존재한다.
		 * dp[3] : 3 을 만드는 방법은 dp[1]에 2를 더하고, dp[2]에 1을 더하며, 따로 3이 존재한다.
		 * dp[4] : dp[3] 에 1 을 더하고, dp[2] 에 2 를 더하고, dp[3] 에 1 을 더하는 방법이 존재.
		 * 
		 * 따라서 위의 형태로 점화식 도출 및 계산
		 * */
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int N = Integer.parseInt(br.readLine());
			bw.write(PASUDO_Solution(N) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// -- PASUDO : Execution Time:0.07
	public static int PASUDO_Solution(int n){
		int[]dp = new int[n + 4];
		Arrays.fill(dp, 0);
		
		dp[1] = 1;
		dp[2] = dp[1] + 1;
		dp[3] = dp[1] + dp[2] + 1;
		
		for(int i = 4; i <= n; i++)
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		
		return dp[n];
	}
}
