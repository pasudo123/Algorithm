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

		int V = Integer.parseInt(st.nextToken());	// ����
		int E = Integer.parseInt(st.nextToken());	// ����
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
		 * (1) �湮
		 * (2) �湮�� �������κ��� �ٸ� ���� �Ÿ� ���
		 * **/
		
		// while �� ��� ���� ����(V-1) ��ŭ ����
		while(true){
			int max = Integer.MAX_VALUE;
			
			size = list.get(startPoint).size();
			visited[startPoint] = true;
			
			for(int e = 0; e < size; e++){
				Vertex vertex = list.get(startPoint).get(e);
				
				endPoint = vertex.e;	// (1) startPoint ���� endPoint ����
				weight = vertex.w;		// (2) �� ����
				
				// (dist[startPoint] + weight) vs (dist[endPoint]) 
				// ������ �����ϴ� �ִܰŸ��� ���� ���� ������ Ȥ�� �����ؼ� ���� ���� ������ 
				if(dist[startPoint] + weight < dist[endPoint]){
					dist[endPoint] = dist[startPoint] + weight;
				}// if
			}// for
			
			// dist �迭���� �湮���� ���� ���� ���� �ּҰ��� Ž���Ѵ�.
			for(int v = 1; v <= dist.length - 1; v++){
				if(max > dist[v] && !visited[v]){
					max = dist[v];
					startPoint = v;
				}
			}
			
			// �������� �� �̻��� ��ȭ�� �����Ƿ� 
			// �� �̻��� �۾��� ���� �ʴ´�.
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