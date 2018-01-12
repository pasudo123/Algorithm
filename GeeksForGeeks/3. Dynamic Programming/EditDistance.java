package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class EditDistance {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			
			int len1 = Integer.parseInt(st.nextToken());
			int len2 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			char[]charLine1 = st.nextToken().toCharArray();
			char[]charLine2 = st.nextToken().toCharArray();
			
			bw.write(recurSiveSolution(charLine1, charLine2, len1, len2) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int threeValueMin(int x, int y, int z){
		if(x <= y && x <= z)
			return x;
		if(y <= z && y <= x)
			return y;
		//if(z <= x && z <= y)
		return z;
	}
	
	// -- GFG : Time Limited, O(3^m)
	public static int recurSiveSolution(char[]line1, char[]line2, int len1, int len2){
		
		if(len1 == 0)
			return len2;
		
		if(len2 == 0)
			return len1;
		
		if(line1[len1-1] == line2[len2-1])
			return recurSiveSolution(line1, line2, len1-1, len2-1);
		
		// line1[len1-1] != line2[len2-1];
		else{
			// Three Operation, 
			// Insert, Remove, Replace
			return (1 + threeValueMin(recurSiveSolution(line1, line2, len1, len2-1), 
					                  recurSiveSolution(line1, line2, len1-1, len2),
					                  recurSiveSolution(line1, line2, len1-1, len2-1)));
		}
	}
	
	// -- Optimazation : Execution Time:0.1
	public static int dynamicProgrammingSolution(char[]line1, char[]line2, int m, int n){
		int[][]dp = new int[m+1][n+1];
		
		// 두번째 문자열이 비어있는 경우 첫번째 문자열의 개수 그대로 삽입
		for(int i = 0; i <= m; i++)
			dp[i][0] = i;
		
		// 반대의 경우
		for(int i = 0; i <= n; i++)
			dp[0][i] = i;
		
		// - dp[i][j]
		// i = 1, j = 1 부터 루프를 시작해서 메모이제이션을 하기 위함이다.
		// 필요한 문자의 개수, m과 n의 문자열에서 m의 i인덱스에 n의 j인덱스 까지의 최소 비용 반환
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
			
				if(line1[i-1] == line2[j-1])
					dp[i][j] = dp[i-1][j-1];
				
				else{
					dp[i][j] = threeValueMin(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
				}
			}
		}
		
		return dp[m][n];
	}
}