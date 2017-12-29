package GeeksforGeeks;

import java.io.*;
import java.util.*;
import java.util.LinkedList; 
// LinkedList 를 명시적으로 해준 이유는, 해당 LinkedList 클래스가 동일한 패키지에 존재.
// 따라서 직접적인 임포트를 하였다.

class AreaPoint{
	int row;
	int col;
}

public class BreadthFirstTraversal {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			
			char[][]shape = new char[row][col];
			
			// 한번의 라인에 모든 값이 들어온다.
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < row; i++){
				String inputLine = st.nextToken();
				shape[i] = inputLine.toCharArray();
			}
			
			bw.write(UseQueueSolution(shape, row, col) + "\n");
		}// for
		
		bw.flush();
		bw.close();
		br.close();
	}// public static void main();
	
	// -- Execution Time:0.07
	public static int UseQueueSolution(char[][]shape, int paramRow, int paramCol){
		int resArea = 0;
		
		int[][]visited = new int[paramRow][paramCol];
		Queue<AreaPoint> queue = new LinkedList<AreaPoint>();
		
		int move1[] = {1, -1, 0, 0};
		int move2[] = {0, 0, 1, -1};
		
		for(int i = 0; i < visited.length; i++)
			Arrays.fill(visited[i], 0);
		
		// 'X' 를 찾고 해당 영역에서 BFS 를 실시한다.
		for(int row = 0; row < shape.length; row++){
			for(int col = 0; col < shape[row].length; col++){
				if(shape[row][col] == 'X' && visited[row][col] == 0){
					resArea++;
					
					AreaPoint ap = new AreaPoint();
					ap.row = row;
					ap.col = col;
					
					queue.add(ap);
					
					while(!queue.isEmpty()){
						AreaPoint getAP = queue.poll();
						int r = getAP.row;
						int c = getAP.col;
						
						visited[r][c] = 1;
						
						for(int m = 0; m < 4; m++){
							int mr = move1[m] + r;
							int mc = move2[m] + c;
							
							if(mr >= 0 && mr <= (paramRow - 1) && mc >= 0 && mc <= (paramCol - 1) && visited[mr][mc] == 0 && shape[mr][mc] == 'X'){
								getAP = new AreaPoint();
								getAP.row = mr;
								getAP.col = mc;
								queue.add(getAP);
							}
						}
					}// while
				}// if
			}
		}
		
		return resArea;
	}
	
	// -- Execution Time:0.07
	public static int UseRecursiveSolution(char[][]shape, int paramRow, int paramCol){
		int resArea = 0;
		
		int[][]visited = new int[paramRow][paramCol];
		for(int i = 0; i < visited.length; i++)
			Arrays.fill(visited[i], 0);
		
		for(int row = 0; row < paramRow; row++){
			for(int col = 0; col < paramCol; col++){
				if(shape[row][col] == 'X' && visited[row][col] == 0){
					// Recursive
					resArea++;
					recursiveBFS(shape, visited, row, col);
				}
			}
		}
		
		return resArea;
	}
	
	public static void recursiveBFS(char[][]shape, int[][]visited, int row, int col){
		int move1[] = {1, -1, 0, 0};
		int move2[] = {0, 0, 1, -1};
		
		// 방문
		visited[row][col] = 1;
		
		for(int m = 0; m < 4; m++){
			int mr = row + move1[m];
			int mc = col + move2[m];
			
			if(mr >= 0 && mr <= (shape.length - 1) && mc >= 0 && mc <= (shape[row].length - 1) && shape[mr][mc] == 'X' && visited[mr][mc] == 0){
				recursiveBFS(shape, visited, mr, mc);
			}
		}
	}
}
