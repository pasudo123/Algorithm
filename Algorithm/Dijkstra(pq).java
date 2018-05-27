import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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
		
		// 출발점 0
		dist[startPoint] = 0;
		Distance distance = new Distance(startPoint, dist[startPoint]);
		
		// 우선순위 큐
		PriorityQueue<Distance> pq = new PriorityQueue<Distance>();
		pq.add(distance);
		
		while(!pq.isEmpty()){
			distance = pq.poll();
			startPoint = distance.v;
			
			if(visited[startPoint])
				continue;
			visited[startPoint] = true;
			
			size = list.get(startPoint).size();
			
			for(int e = 0; e < size; e++){
				Vertex vertex = list.get(startPoint).get(e);
				
				endPoint = vertex.e;	// (1) startPoint 에서 endPoint 까지
				weight = vertex.w;		// (2) 의 정점
				
				// (dist[startPoint] + weight) vs (dist[endPoint]) 
				
				if(dist[startPoint] + weight < dist[endPoint]){
					dist[endPoint] = dist[startPoint] + weight;
					pq.offer(new Distance(endPoint, dist[endPoint]));
				}// if
			}// for
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

class Distance implements Comparable<Distance>{
	int v;
	int dist;
	
	public Distance(int v, int dist){
		this.v = v;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Distance distance) {
		return dist - distance.dist;
	}
}