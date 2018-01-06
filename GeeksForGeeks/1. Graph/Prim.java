package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class Prim {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[]args) throws IOException{
		// 무방향 그래프
		int[][]graph = {{INF,   4, INF, INF, INF, INF, INF,   8, INF},
						{  4, INF,   8, INF, INF, INF, INF,  11, INF},
						{INF,   8, INF,   7, INF,   4, INF, INF,   2},
						{INF, INF,   7, INF,   9,  14, INF, INF, INF},
						{INF, INF, INF,   9, INF,  10, INF, INF, INF},
						{INF, INF,   4,  14,  10, INF,   2, INF, INF},
						{INF, INF, INF, INF, INF,   2, INF,   1,   6},
						{  8,  11, INF, INF, INF, INF,   1, INF,   7},
						{INF, INF,   2, INF, INF, INF,   6,   7, INF}};
		
//		int graph[][] = new int[][] {{INF, 2, INF, 6, INF},
//            						 {2, INF, 3, 8, 5},
//            						 {INF, 3, INF, INF, 7},
//            						 {6, 8, INF, INF, 9},
//            						 {INF, 5, 7, 9, INF}};
		// startVertex : 0 ~ 8 
		PASUDO_PrimAlgorithm(graph, graph.length);
	}
	
	static void PASUDO_PrimAlgorithm(int[][]graph, int size){
		// 시작 정점
		int startVertex = 0;
				
		// Minimum Spanning Tree Set 
		boolean[]mstSet = new boolean[size]; 
		Arrays.fill(mstSet, false);
		
		// Minimum Distance
		int[]miniDist = new int[size];
		Arrays.fill(miniDist, INF);
		
		// startVertex is 0 & 해당 위치는 0 값부터 시작
		miniDist[startVertex] = 0;
		
		int count = 0;
		int miniEdge = size - 1;
		
		int minVertex = 0;
		int minWeight = 0; 
		
		while(count < miniEdge){
			
			startVertex = findVertex(miniDist, mstSet);
			mstSet[startVertex] = true;
			
			// startVertex 에서 괜찮은 값이 없는 경우
			for(int endVertex = 0; endVertex < size; endVertex++){
				// (1) startVertex ㅡㅡ> endVertex 로 가는 가중치의 값이 기존의 최소값보다 작아야 한다.
				// (2) mstSet[endVertex] 의 값이 False : 방문하지 않아야 한다.
				if(graph[startVertex][endVertex] < miniDist[endVertex] && !mstSet[endVertex]){
					minVertex = endVertex;
					miniDist[endVertex] = graph[startVertex][endVertex];
					minWeight = miniDist[endVertex];
					System.out.println(startVertex + " ㅡㅡ> " + minVertex + " : " + minWeight);
				}
			}
			
			// s ㅡㅡ> e : weight 출력
			count++;
		}
		
		// 마지막 정점은 카운트되지 않기 때문에 방문여부를 해주면 보기 좋겠지??
		mstSet[findVertex(miniDist, mstSet)] = true;

		System.out.println(Arrays.toString(miniDist));
		System.out.println(Arrays.toString(mstSet));
		int sum = 0;
		// dist 에 각 노드에 대한 가중치가 모두 있기 때문에 모두 더해주면 된다.
		for(int dist : miniDist)
			sum += dist;
		System.out.println(sum);
	}
	
	static int findVertex(int[]miniDist, boolean[]mstSet){
		
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		
		for(int i = 0; i < mstSet.length; i++){
			// mstSet 에 포함되지 않으며, miniDist 가 최소값을 찾는다.
			if(!mstSet[i] && miniDist[i] < min){
				min = miniDist[i];
				minIndex = i;
			}
		}
		
		return minIndex;
	}
}
