
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
		
		BigInteger N = new BigInteger(br.readLine());
		int mod =  1000000;
		int period = getPeriod(mod);
		
		long[]fibo = new long[period + 1];
		fibo[1] = 0L;
		fibo[2] = 1L;
		
		for(int i = 3; i <= period; i++)
			fibo[i] = (fibo[i-1] + fibo[i-2]) % mod;
		
		int i = N.mod(new BigInteger(period + "")).intValue() + 1;
		
		bw.write(fibo[i] + "\n");
		br.close();
		bw.close();
	}
	
	public static int getPeriod(int mod){
		int number1 = 0;
		int number2 = 1;
		int period = 0;		// number1 & number2 의 주기
		
		while(true){
			int temp = number1;
			number1 = number2;
			number2 = (temp + number2) % mod;
			
			period++;
			
			if(number1 == 0 && number2 == 1)
				break;
		}
		
		return period;
	}
}