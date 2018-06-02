import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * reference : http://mathbang.net/101
     * 원의 반지름과 중심거리간의 연관성
	 * */
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		BufferedWriter bw = new BufferedWriter(osw);

		int T = Integer.parseInt(br.readLine());
		int x1, y1, r1, x2, y2, r2;
			
		for(int i = 0; i < T; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());
			
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());
			
			bw.write(process(x1, y1, r1, x2, y2, r2) + "\n");
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static int process(int x1, int y1, int r1, int x2, int y2, int r2){
		
		int maxX = Math.max(x1, x2);
		int minX = Math.min(x1, x2);
		int maxY = Math.max(y1, y2);
		int minY = Math.min(y1, y2);
		
		double d = Math.sqrt(Math.pow(maxX-minX, 2) + Math.pow(maxY-minY, 2));
		
		int bigR = r1 + r2;
		int smallR = Math.max(r1, r2) - Math.min(r1, r2);

		if(smallR == 0){
			if(x1==x2 && y1 == y2)
				return -1;
		}
		
		// 원이 교차하지 않음
		if(d > bigR)
			return 0;
		
		// 원의 외부 접점
		if(d == bigR)
			return 1;
		
		// 두 개의 원이 교차
		if(bigR > d && d > smallR)
			return 2;
			
		// 원의 내부 접점
		if(d == smallR)	
			return 1;
		
		// 원이 만나지 않음
		else // d < small
			return 0;
			
	}
}