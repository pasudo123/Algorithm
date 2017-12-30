package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class ShortestPathDijkstra {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	public static void main(String[]args) throws IOException{
		/*
		 * reference : https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
		 * 
		 * [ Dijkstra : Shortest Path from source to all vertices ]
		 * Given a graph and a source vertex in graph, 
		 * find shortest paths from source to all vertices in the given graph
		 * 
		 * Problem Rule
		 * - There is an edge from a vertex i to a vertex j iff either j = i + 1 or j = 3i. 
		 * 
		 * 위의 문제는  BFS 혹은 DFS 혹은 DP 로 풀 수 있다고 하나. 
		 * 하지만 다익스트라 페이지에서 리디렉션되서 해당 문제에 접근하려고 하는데 안된다. 타임아웃이 된다.
		 * */
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			int n = Integer.parseInt(br.readLine());
			
			ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>();
			
			// 0 Index, exclude
			for(int i = 0; i <= n; i++)
				path.add(new ArrayList<Integer>());
			
			// Input Vertex 
			// Consider a Directed Graph : a vertex 'i' to a vertex 'j'
			int j = 0;
			for(int i = 1; i <= n; i++){
				// Rule1
				j = i+1;
				
				if(j <= n)
					path.get(i).add(j);
				
				// Rule2
				j = i * 3;
				
				if(j <= n)
					path.get(i).add(j);
			}
			
//			bw.write(PASUDO_DijkstraSolution(path, n) + "\n");
			bw.write(PASUDO_bfsSolution(path, n) + "\n");
		}
		
		bw.close();
		br.close();
	}
	
	
	// -- PASUDO : Expected Time Limit < 0.7sec
	// Please optimize your code and submit again.
	public static int PASUDO_DijkstraSolution(ArrayList<ArrayList<Integer>> path, int vertexNumber){
		int[]visited = new int[vertexNumber+1];
		int[]shortest = new int[vertexNumber+1];
		
		Arrays.fill(visited, 0);
		Arrays.fill(shortest, Integer.MAX_VALUE);
		
		int minimumEdge = 0;
		int startVertex = 1;		// 출발 정점 1
		visited[startVertex] = 1;	// 출발 정점 방문 1
		shortest[startVertex] = 0;	// 출발 정점 거리 0
		
		// 첫 출발점을 기준으로 연결된 간선들의 값 초기화
		for(int i = 0; i < path.get(startVertex).size(); i++){
			int ev = path.get(startVertex).get(i);
			shortest[ev] = 1;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startVertex);
		
		while(!queue.isEmpty()){
			int sv = queue.poll();
			
			ArrayList<Integer> endVertex = path.get(sv);
			
			for(int i = 0; i < endVertex.size(); i++){
				// startVertex 와 연결된 endVertex 를 찾는다.
				int ev = endVertex.get(i);
				
				// 미방문
				if(visited[ev] == 0){
					// 기존 거리 = min(기존거리, startVertex 를 지나서 가는 거리)
					shortest[ev] = Math.min(shortest[ev], shortest[sv] + 1);
					visited[ev] = 1;
					queue.add(ev);
					minimumEdge++;
				}
			}
			
			if(minimumEdge >= vertexNumber - 1)
				break;
		}
		
		return shortest[vertexNumber];
	}
	

	// -- PASUDO(BFS) : Execution Time:0.29
	public static int PASUDO_bfsSolution(ArrayList<ArrayList<Integer>> path, int vertexNumber){
		int[]visited = new int[vertexNumber+1];
		int[]shortest = new int[vertexNumber+1];
		Arrays.fill(visited, 0);
		
		Queue<Integer>queue = new LinkedList<Integer>();
		queue.add(1);
		visited[1] = 1;
		shortest[1] = 0;
		
		while(!queue.isEmpty()){
			int sv = queue.poll();
			
			for(int i = 0; i < path.get(sv).size(); i++){
				int ev = path.get(sv).get(i);
				
				if(visited[ev] == 0){
					queue.add(ev);
					visited[ev] = 1;
					shortest[ev] = shortest[sv] + 1;
				}
			}
		}
		
		return shortest[vertexNumber];
	}
}
