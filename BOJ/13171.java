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

		BigInteger A = new BigInteger(br.readLine());
		BigInteger X = new BigInteger(br.readLine());
		BigInteger A_ = null;
		
		ArrayList<BigInteger> expValue = new ArrayList<BigInteger>();
		expValue.add(new BigInteger("0")); // 0 index : 0 삽입
		
		BigInteger num = new BigInteger("1");
		
		BigInteger mod = new BigInteger("1000000007");
		BigInteger bin = new BigInteger("2");
		
		while(true){
			A_ = A.modPow(num, mod);
			expValue.add(A_);
			
			num = num.multiply(bin);
			
			if(num.compareTo(X) == 1)
				break;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(true){
			int value = X.mod(bin).intValue();
			X = X.divide(bin);
			
			sb.append(value);
			
			if(X.compareTo(new BigInteger("0")) == 0)
				break;
		}
		
		BigInteger res = new BigInteger("1");
		
		for(int i = 0; i < sb.length(); i++){
			char c = sb.charAt(i);
			if(c == '1'){
				res = res.multiply(expValue.get(i+1)).mod(mod);
			}
		}
		
		bw.write(res + "\n");
		br.close();
		bw.close();
	}
}