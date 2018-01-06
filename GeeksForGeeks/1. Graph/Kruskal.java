package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class Kruskal {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;
	
	static final int ZERO = 0;
	static int parent[];
	static int rank[];
	static int size[];
	
	public static void main(String[]args) throws IOException{
		int[][]graph = {{ZERO,    4, ZERO, ZERO, ZERO, ZERO, ZERO,    8, ZERO},
						{   4, ZERO,    8, ZERO, ZERO, ZERO, ZERO,   11, ZERO},
						{ZERO,    8, ZERO,    7, ZERO,    4, ZERO, ZERO,    2},
						{ZERO, ZERO,    7, ZERO,    9,   14, ZERO, ZERO, ZERO},
						{ZERO, ZERO, ZERO,    9, ZERO,   10, ZERO, ZERO, ZERO},
						{ZERO, ZERO,    4,   14,   10, ZERO,    2, ZERO, ZERO},
						{ZERO, ZERO, ZERO, ZERO, ZERO,    2, ZERO,    1,    6},
						{   8,   11, ZERO, ZERO, ZERO, ZERO,    1, ZERO,    7},
						{ZERO, ZERO,    2, ZERO, ZERO, ZERO,    6,    7, ZERO}};
		
		// startVertex : 0 ~ 8 
		PASUDO_KruskalAlgorithm(graph);
	}
	
	static class Edge implements Comparable<Edge>{
		int s;
		int e;
		int weight;
		
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.weight = w;
		}
		
		@Override
		public int compareTo(Edge e){
			return this.weight - e.weight;
		}
	}
	
	static void PASUDO_KruskalAlgorithm(int[][]graph){
		/*
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * (1) Sorting the edges in increasing order of their weight;
		 * (2) Pick the smallest edge. Check if it forms a cycle with the Spanning Tree
		 * (3) Repeat (2) until there are (Vertex - 1) edges in spanning tree
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * */
		
		// init
		init(graph.length);
		
		
		// orderByASC
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		for(int i = 0; i < graph.length; i++){
			for(int j = i + 1; j < graph.length; j++){
				if(graph[i][j] != ZERO){
					edgeList.add(new Edge(i, j, graph[i][j]));
				}
			}
		}
		
		orderByASC(edgeList);
		
		
		// process
		process(edgeList, graph.length);
	}
	
	static void init(int paramSize){
		parent = new int[paramSize];
		rank = new int[paramSize];
		size = new int[paramSize];
		
		for(int i = 0; i < parent.length; i++)
			parent[i] = i;
		
		Arrays.fill(rank, 0);	// 높이가 없기 때문에 0
		Arrays.fill(size, 1);	// 각자가 하나의 개별 원소로 존재 1
	}
	
	static void orderByASC(ArrayList<Edge> edgeList){
		Collections.sort(edgeList);
	}
	
	static void process(ArrayList<Edge> edgeList, int minimumEdge){
		int count = 0;
		int index = 0;
		int resWeight = 0;
		
		// MST 구성
		while(count < minimumEdge - 1){
			// edgeList 에 인덱스를 하나씩 접근한다.
			// 내부적으로 간선의 가중치들은 오름차순으로 되어있다.
			Edge edge = edgeList.get(index++);
			
			// find()을 통해서 두 개의 정점의 루트원소를 찾는다.
			int rootS = find(parent, edge.s);
			int rootE = find(parent, edge.e);
			int weight = edge.weight;
			
			// Checking spanning tree
			if(rootS != rootE){
				// 루트원소가 다른 두 개의 subtree 를 합친다.
				union(parent, rootS, rootE);
				resWeight += weight;
				count++;	// 간선의 개수를 하나 추가
				
				// s ㅡㅡ> e : weight 출력
				System.out.println(edge.s + " ㅡㅡ> " + edge.e + " : " + weight);
			}
		}
		
		// 전체 가중치의 값
		System.out.println("graph full weight : " + resWeight);
	}
	
	// UNION - FIND
	static int find(int[]parent, int v){
		// Path Compression
		if(parent[v] != v)
			parent[v] = find(parent, parent[v]);
		
		return parent[v];
	}
	
	static void union(int[]parent, int u, int v){
		int rootU = find(parent, u);
		int rootV = find(parent, v);
		
		// 둘의 루트원소가 다르다면, 
		if(rootU != rootV){
			if(rank[rootU] < rank[rootV]){
				parent[rootU] = rootV;
				size[rootV] += size[rootU];
			}
			
			else if(rank[rootU] > rank[rootV]){
				parent[rootV] = rootU;
				size[rootU] += size[rootV];
			}
			
			// rank[rootU] == rank[rootV] 
			else{
				parent[rootU] = rootV;
				size[rootV] += size[rootU];
				rank[rootV]++;
			}
		}
	}
}
