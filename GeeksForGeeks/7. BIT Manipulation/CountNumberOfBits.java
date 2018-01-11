package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class CountNumberOfBits {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
//			bw.write(getFlipCountOfBit(a ,b) + "\n");
			bw.write(optimazationCountOfBit(a, b) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// -- PASUDO : Execution Time:0.08
	static int getFlipCountOfBit(int a, int b){
		StringBuilder sb1 = new StringBuilder("");
		StringBuilder sb2 = new StringBuilder("");
		
		while(a != 0){
			sb1.append(String.valueOf(a%2));
			a = a / 2;
		}
		
		while(b != 0){
			sb2.append(String.valueOf(b%2));
			b = b / 2;
		}
		
		while(sb1.length() > sb2.length())
			sb2.append("0");
		
		while(sb1.length() < sb2.length())
			sb1.append("0");
			
//		System.out.println(sb1.reverse());
//		System.out.println(sb2.reverse());
		
		int res = 0;
		for(int i = 0; i < sb1.length(); i++)
			if(sb1.charAt(i) != sb2.charAt(i))
				res++;
		
		return res;
	}
	
	// -- PASUDO : Execution Time:0.06
	static int optimazationCountOfBit(int a, int b){
		int res = 0;
		
		while(a != 0 && b != 0){
			int b1 = a & 1;
			int b2 = b & 1;
			
			// 다른경우는 뒤집는경우를 의미하기 때문에
			if(b1 != b2){
//				System.out.println(a + ", " + b);
				res++;
			}
			
			a = a >> 1;
			b = b >> 1;
		}
		
		while(a != 0){
//			System.out.println("while(a) : " + a + ", " + b);
//			첫번째 while문을 통해서, 0이 아닌 값에 한해서 논리곱 비트 연산을 실시한다.
//			여기서, 해당 비트의 값이 1인 값은 플립카운트에 속한다. 왜냐? 비교대상이 되는 해당 비트의 위치는 0이기 때문이다.
			if((a & 1) == 1)
				res++;
			
			a >>= 1;
		}
		
		while(b != 0){
//			System.out.println("while(b) : " + a + ", " + b);
			
			if((b & 1) == 1)
				res++;
			
			b >>= 1;
		}
		
		return res;
	}
}
