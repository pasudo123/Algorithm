import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		BufferedWriter bw = new BufferedWriter(osw);

		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int map[][] = new int[M + 1][N + 1];

		for (int row = 1; row <= M; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int dp[][] = new int[M + 1][N + 1];
		for (int i = 0; i <= M; i++)
			Arrays.fill(dp[i], -1);

		process(dp, map, 1, 1, M, N);

		System.out.println(dp[1][1]);

		bw.flush();
		bw.close();
		br.close();
	}

	public static int process(int[][] dp, int[][] map, int row, int col, int M, int N) {

		int move1[] = { 1, -1, 0, 0 };
		int move2[] = { 0, 0, 1, -1 };

		if (row == M && col == N)
			return 1;
		
		if(dp[row][col] != -1)
			return dp[row][col];
		
		int temp = 0;
		
		if (dp[row][col] == -1) {
			for (int m = 0; m < 4; m++) {
				int mr = row + move1[m];
				int mc = col + move2[m];

				int height = map[row][col];

				if (mr <= 0 || mr > M)
					continue;
				if (mc <= 0 || mc > N)
					continue;
				if (height <= map[mr][mc])
					continue;

				temp += process(dp, map, mr, mc, M, N);
			}
		}

		return dp[row][col] = temp;
	}
}