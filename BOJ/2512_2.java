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
		
		int total = 0;
		int currTotal = 0;
		int result = 0;
		
		for(int i = 1; i <= N; i++){
			
			total = money[i] * (N - (i - 1));
			
			if(currTotal + total < M)
				currTotal += money[i];
			else{
				int minus = M - currTotal;
				result = minus / (N - (i -  1));
				break;
			}
		}

		bw.write(result + "\n");
		br.close();
		bw.close();
	}
}