
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
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int[][]A = new int[N+1][N+1];
		int[][]result = new int[N+1][N+1];
		
		for(int row = 1; row <= N; row++){
			st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= N; col++){
				A[row][col] = Integer.parseInt(st.nextToken()); 
				result[row][col] = A[row][col];
			}
		}
		
		ArrayList<Boolean> evenList = new ArrayList<Boolean>();
		ArrayList<Long> longList = new ArrayList<Long>();
		
		while(B > 1){
			if(B % 2 == 0)
				evenList.add(true);
			else
				evenList.add(false);
			longList.add(B);
			
			B = B / 2;
		}
		
		int lastIndex = longList.size()-1;
		for(int i = lastIndex; i >= 0; i--){

			long exp = longList.get(i);
			boolean flag = evenList.get(i);
			
			if(flag){
				// result * result 
				result = multiply(result, result);
			}
			else{
				// result * result * A
				result = multiply(result, result);
				result = multiply(result, A);
			}
		}
		
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= N; j++){
				bw.write((result[i][j] % 1000) + " ");
			}
			bw.write("\n");
		}
		
		br.close();
		bw.close();
	}
	
	public static int[][] multiply(int[][]arr1, int[][]arr2){
		int mod = 1000;
		int rows = arr1.length;
		int cols = arr1[0].length;
		
		int[][]result = new int[rows][cols];
		
		for(int row = 1; row < rows; row++){
			for(int col = 1; col < cols; col++){
				int sum = 0;
				
				for(int m = 1; m < rows; m++){
					sum += (arr1[row][m] * arr2[m][col]) % mod;
				}
				
				result[row][col] = sum;
			}
		}
		
		return result;
	}
}