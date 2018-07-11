import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Main {

	static InputStreamReader isr;
	static BufferedReader br;
	static OutputStreamWriter osw;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		osw = new OutputStreamWriter(System.out);
		bw = new BufferedWriter(osw);
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int	money[] = new int[N+1];
		int sum = 0;
		
		for(int i = 1; i <= N; i++){
			money[i] = Integer.parseInt(st.nextToken());
			sum += money[i];
		}
		
		Arrays.sort(money);
		
		if(sum <= M){
			bw.write(money[N] + "\n");
			br.close();
			bw.close();
			return;
		}
		
		int left = 0;
		int right = money[N];
		int mid = 0;
		
		while(left <= right){
			mid = (left + right) / 2;
			sum = 0;

			for(int i = 1; i <= N; i++){
				if(money[i] > mid)
					sum += mid;
				else
					sum += money[i];
			}
			
			if(sum > M)
				right = mid - 1;
			else
				left = mid + 1;
		}

		bw.write(right + "\n");
		br.close();
		bw.close();
	}
}