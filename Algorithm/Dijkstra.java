import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		BufferedWriter bw = new BufferedWriter(osw);
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());	// 정점
		int E = Integer.parseInt(st.nextToken());	// 간선
		int s = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Vertex>> vertexList = new ArrayList<ArrayList<Vertex>>();
		int dist[] = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean visited[] = new boolean[V+1];
		Arrays.fill(visited, false);
		
		for(int i = 0; i <= V; i++)
			vertexList.add(new ArrayList<Vertex>());
		
		for(int i = 0; i < E; i++){
			st = new StringTokenizer(br.readLine());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			Vertex vertex = new Vertex(v2, w);
			vertexList.get(v1).add(vertex);
		}
		
		process(vertexList, s, dist, visited);
		
		for(int v = 1; v < dist.length; v++){
			if(dist[v] == Integer.MAX_VALUE)
				bw.write("INF\n");
			else
				bw.write(dist[v] + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void process(ArrayList<ArrayList<Vertex>> list, int s, int[]dist, boolean[]visited){
		int startPoint = s;
		int endPoint = 0;
		int weight = 0;
		int size = 0;
		
		dist[startPoint] = 0;
		
		/**
		 * (1) 방문
		 * (2) 방문한 정점으로부터 다른 정점 거리 계산
		 * **/
		
		// while 도 사실 정점 개수(V-1) 만큼 루프
		while(true){
			int max = Integer.MAX_VALUE;
			
			size = list.get(startPoint).size();
			visited[startPoint] = true;
			
			for(int e = 0; e < size; e++){
				Vertex vertex = list.get(startPoint).get(e);
				
				endPoint = vertex.e;	// (1) startPoint 에서 endPoint 까지
				weight = vertex.w;		// (2) 의 정점
				
				// (dist[startPoint] + weight) vs (dist[endPoint]) 
				// 기존에 존재하는 최단거리로 가는 것이 빠른가 혹은 경유해서 가는 것이 빠른가 
				if(dist[startPoint] + weight < dist[endPoint]){
					dist[endPoint] = dist[startPoint] + weight;
				}// if
			}// for
			
			// dist 배열에서 방문하지 않은 정점 중의 최소값을 탐색한다.
			for(int v = 1; v <= dist.length - 1; v++){
				if(max > dist[v] && !visited[v]){
					max = dist[v];
					startPoint = v;
				}
			}
			
			// 정점에서 더 이상의 변화가 없으므로 
			// 더 이상의 작업은 하지 않는다.
			if(max == Integer.MAX_VALUE){
				break;
			}
		}// while(true)
	}
}

class Vertex{
	int e;
	int w;
	
	public Vertex(int e, int w){
		this.e = e;
		this.w = w;
	}
}