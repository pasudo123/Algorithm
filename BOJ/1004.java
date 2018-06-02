import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		BufferedWriter bw = new BufferedWriter(osw);

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			
			int n = Integer.parseInt(br.readLine());
			
			Planet[] planet = new Planet[n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				planet[i] = new Planet(x, y, r);
			}
			
			bw.write(process(sx, sy, ex, ey, planet) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int process(int sx, int sy, int ex, int ey, Planet[] planet){
		/**
		 * 출발점에서 모든 행성의 포함여부를 구함
		 * **/
		
		int inAndOutCount = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		// 출발점
		for(int i = 0; i < planet.length; i++){
			int x = planet[i].x;
			int y = planet[i].y;
			int r = planet[i].r;
			
			int bigX = Math.max(sx, x);
			int smallX = Math.min(sx, x);
			
			int bigY = Math.max(sy, y);
			int smallY = Math.min(sy, y);
			
			// 출발점과 행성 중심과의 거리
			double d = Math.sqrt(Math.pow(bigX-smallX, 2) + Math.pow(bigY-smallY, 2));
			
			// d 가 행성의 반지름보다 작으면 행성 내부에 존재
			// map 은 확인용
			if( d < r ){
				inAndOutCount++;
				map.put(x + "&" + y + "&" + r, 1); 
			}
		}
		
		// 도착점
		for(int i = 0; i < planet.length; i++){
			int x = planet[i].x;
			int y = planet[i].y;
			int r = planet[i].r;
			
			int bigX = Math.max(ex, x);
			int smallX = Math.min(ex, x);
			
			int bigY = Math.max(ey, y);
			int smallY = Math.min(ey, y);
			
			// 출발점과 행성 중심과의 거리
			double d = Math.sqrt(Math.pow(bigX-smallX, 2) + Math.pow(bigY-smallY, 2));
			
			// d 가 행성의 반지름보다 작으면 행성 내부에 존재
			if( d < r ){
				
				// ** 여기가 중요
				// 출발점과 도착점에 대해서 같은 원에 소속된 경우, count 를 -1 실시
				if(map.get(x + "&" + y + "&" + r) != null){
					inAndOutCount--;
				}
				else{
					inAndOutCount++;
				}
			}
		}
		
		return inAndOutCount;
	}
}

class Planet {
	int x;
	int y;
	int r;

	public Planet(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
}