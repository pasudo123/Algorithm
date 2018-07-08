import java.util.*;
import java.io.*;

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

		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int N = Integer.parseInt(br.readLine());
			int person[] = new int[N+1];
			
			for(int i = 1; i <= N; i++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				person[a] = b;
			}
			
			int count = 1;
			int minB = person[1];
			for(int i = 2; i <= N; i++){
				int b = person[i];
				
				if(minB > b){
					count++;
					minB = b;
				}
			}
			
			bw.write(count + "\n");
		}
		
		br.close();
		bw.close();
	}
}