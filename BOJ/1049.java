import java.util.*;

import javax.management.MXBean;

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

		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[]packPrice = new int[M];
		int[]piecePrice = new int[M];
		
		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			
			int pp1 = Integer.parseInt(st.nextToken());	// 패키지 가격
			int pp2 = Integer.parseInt(st.nextToken());	// 낱개 가격
			
			packPrice[i] = pp1;
			piecePrice[i] = pp2;
		}
		
		Arrays.sort(packPrice);
		Arrays.sort(piecePrice);
		
		int number = (N % 6 != 0)? (N / 6 + 1) : (N / 6);
		
		int packMinPrice = packPrice[0] * number;
		int pieceMinPrice = piecePrice[0] * N;
		int	mixPrice =  packPrice[0] * (N / 6) + piecePrice[0] * (N % 6);
		
		int min = Math.min(packMinPrice, Math.min(pieceMinPrice, mixPrice));
			
		bw.write(min + "\n");
		br.close();
		bw.close();
	}
}