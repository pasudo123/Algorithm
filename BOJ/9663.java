import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {

	static InputStreamReader isr;
	static BufferedReader br;
	static OutputStreamWriter osw;
	static BufferedWriter bw;
	static StringTokenizer st;

	static int N = 0;
	static int result = 0;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		osw = new OutputStreamWriter(System.out);
		bw = new BufferedWriter(osw);

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1]; // 0, 1 ~ N;
		process(1, 0);

		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void process(int r, int count) {

		if(count == N){
			result++;
			return;
		}
		
		for (int col = 1; col <= N; col++) {
			boolean found = true;
			
			arr[r] = col;
			
			for (int row = 1; row < r; row++) {

				// 같은 열 || 대각선
				if (arr[row] == arr[r] || Math.abs(arr[row] - arr[r]) == Math.abs(row - r)) {
					found = false;
					break;
				}
			}
			
			if(found)
				process((r+1), (count+1));
			else
				arr[r] = 0;
		}
	}
}