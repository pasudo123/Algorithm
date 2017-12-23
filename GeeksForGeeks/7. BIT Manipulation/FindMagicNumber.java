package GeeksforGeeks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class FindMagicNumber {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);

	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);

	public static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		for (int i = 0; i < TC; i++) {
			int inputNumber = Integer.parseInt(br.readLine());
//			System.out.println(getMagicNumber(inputNumber));
			System.out.println(OptimazationGetMaginNumber(inputNumber));
		}
	}

	// -- PASUDO : Execution Time:0.06
	public static String getMagicNumber(int paramNumber){
		String bit = new String();
		
		// long 범위 초과 때문에 BigInteger 클래스 이용.
		BigInteger value = new BigInteger("0");
		BigInteger magicNum = new BigInteger("7");
		
		// 파라미터로 넘어오는 값을 비트형태로 변환한다.
		while(paramNumber != 0){
			bit = String.valueOf(paramNumber % 2) + bit;
			paramNumber = paramNumber / 2;
		}
		
		int power = bit.length() - 1;
		for(int index = 0; index < bit.length(); index++){
			if(bit.charAt(index) == '1'){
				value = value.add(magicNum.pow(power));
			}
			power--;
		}
		
		return value.toString();
	}
	
	// -- GFG : : Execution Time:0.07
	public static String OptimazationGetMaginNumber(int paramNumber){
		int power = 0;
		
		BigInteger value = new BigInteger("0");
		BigInteger magicNum = new BigInteger("7");
		
		while (paramNumber != 0) {
			// paramNumber 와 1 사이의 & 연산을 통해 [Last bit] 가 1인지 여부 확인
			if ((paramNumber & 1) == 1) {
				value = value.add(magicNum.pow(power));
			}
			
			// [ Next bit ] 
			paramNumber = paramNumber / 2;
			power++;
		}
		
		return value.toString();
	}
}
