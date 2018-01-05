package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class UnionFindSet {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static StringTokenizer st;

	
	/* 
	 * [ UnionFind Set(=Disjoint Set or MergeFind Set) ]
	 * (1) Union By Rank
	 * (2) Path Compression
	 * */
	
	int[]parent;
	int[]rank;
	int[]size; // 사용해도 되고 사용하지 않아도 상관 없음 (해당 그래프의 크기를 알고자 하기 위함)
	
	public static void main(String[]args) throws IOException{
		UnionFindSet ufs = new UnionFindSet();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			ufs.init(N);
			// query
			st = new StringTokenizer(br.readLine());
			
			for(int q = 0; q < K; q++){
				String query = st.nextToken();
				int res = 0;
				// find(v)
				if(query.equals("FIND")){
					res = ufs.find(Integer.parseInt(st.nextToken()));
					bw.write(res + " ");
				}
				// union(u, v);
				// UNION X Z (parent:Z, child:X)
				else
					ufs.union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			bw.flush();
			bw.close();
			br.close();
		}
	}
	
	/*
	 * -- init()
	 * (1) 각각의 원소들은 개별의 집합이 된다.
	 * (2) 각각의 원소들은 각각이 루트노드가 되며, 자기 자신을 가리킨다.
	 * (3) 각각을 초기화하는 경우 랭크는 0 으로 된다.
	 * (4) 각각의 모든 원소들은 사이즈가 1이다. (자기자신)
	 */
	void init(int size){
		this.parent = new int[size + 1];
		this.rank = new int[size + 1];
		this.size = new int[size + 1];
		
		// init();
		for(int i = 0; i < parent.length; i++){
			this.parent[i] = i;
			this.rank[i] = 1;	// 랭크 0
			this.size[i] = 1;	// 사이즈 1
		}
	}
	
	// Linear 한 경우에 시간복잡도가 O(n) 이 되는 것을  O(Logn) 으로 만들어준다.
	int find(int v){
		if(parent[v] != v){
			// Path Compression (경로압축)
			parent[v] = find(parent[v]);
		}
		
		return parent[v];
	}
	
	void union(int u, int v){
		int ru = find(u);
		int rv = find(v);
		
		if(ru == rv)
			return;
		
		// 두 원소의 루트 원소가 다른 경우
		// (1) 랭크를 비교해서 더 낮은 랭크 값을 가지는 원소트리가 더 높은 랭크 값을 가지는 원소트리 밑으로 들어간다.
		// (2) 더 낮은 루트원소의  parent[] 값 조정
		// (3) 더 높은 루트원소의 size[] 값 조정
		if(ru != rv){
			if(rank[ru] < rank[rv]){
				parent[ru] = rv;
				size[rv] += size[ru];
			}
			else if(rank[ru] > rank[rv]){
				parent[rv] = ru;
				rank[ru] += rank[rv];
				
			}
			// 동일한 랭크값인 경우에 랭크값을 변경
			// 부모가 rv, 자식이 ru 가 된다
			else{
				parent[ru] = rv;
				size[rv] += size[ru];
				rank[rv]++;
			}
		}
	}
}
