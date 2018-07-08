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

		int N = Integer.parseInt(br.readLine());
		char[][]arr = new char[N+1][N+1];
		
		for(int row = 1; row <= N; row++){
			
			char[]charArr = br.readLine().toCharArray();
			
			for(int col = 1; col <= N; col++){
				arr[row][col] = charArr[col-1];
			}
		}
		
		bw.write(process(arr, 1, 1, N) + "\n");
		
		br.close();
		bw.close();
	}
	
	public static String process(char[][]arr, int row, int col, int size){
		
		if(size == 1){
			return (arr[row][col] == '0')? "0" : "1";
		}
		
		String leftUp = process(arr, row, col, size / 2);
		String rightUp = process(arr, row, (col + size / 2), size / 2);
		String leftDown = process(arr, (row + size / 2), col, size / 2);
		String rightDown = process(arr, (row + size / 2), (col + size / 2), size / 2);
		
		if(leftUp == rightUp && rightUp == leftDown & leftDown == rightDown){
			return leftUp;
		}
		else{
			return "(" + (leftUp + rightUp + leftDown + rightDown) + ")";
		}
	}
	
}