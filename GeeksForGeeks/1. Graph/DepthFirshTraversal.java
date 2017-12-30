package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class DepthFirshTraversal {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	static BufferedWriter bw = new BufferedWriter(osw);
	
	static StringTokenizer st = null;
	
	static int evenCount = 1;	// 짝수거리를 가진 노드의 개수
	static int oddCount = 0;	// 홀수거리를 가진 노드의 개수
	
	public static void main(String[]args) throws IOException{
		
		/* 
		 * reference : https://practice.geeksforgeeks.org/problems/nodes-at-even-distance/0
		 * 
		 * 일단 문제 자체부터 이해하기 어렵다. 그래프는 비순환형이다. 
		 * (1) TestCase 입력
		 * (2) 노드의 개수 N 개 입력
		 * (3) 엣지의 개수 (N-1) 개 입력
		 * 
		 * 이후에 해당 노드의 양쪽 길이가 동일한 쌍이 몇개 있는지 확인하는 문제이다.
		 * ex) 1 - 2 - 3 으로 연결되어 있는 그래프에서는
		 *     (1, 3) 이 2 를 사이에 두고 각각 1의 길이를 가지고 있으므로 정답은 1 이다.
		 * 
		 * ex) 1 - 2 - 3 - 4 - 5 으로 연결되어 있는 그래프에서는
		 * 	   (1, 3), (2, 4), (3, 5), (1, 5) 가 각각 동일한 길이를 사이에 두고 있으므로 정답은 4 이다.
		 * 
		 * => 따라서 거치는 간선의 개수가 짝수인 경우에 정답을 도출해낼 수 있다.
		 */
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++){
			// N   :  노드의 개수
			// N-1 :  엣지의 개수, 에지는 노드의 개수 - 1이다.
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			LinkedList<Integer>[] llist = new LinkedList[N];
			
			for(int node = 0; node < N; node++)
				llist[node] = new LinkedList<Integer>();
			
			// (N-1) 개의 엣지
			for(int n = 1; n < N; n++){
				int s = Integer.parseInt(st.nextToken()) - 1;	// 0 인덱스부터 접근하기 위함
				int e = Integer.parseInt(st.nextToken()) - 1; 	// ""
				
				llist[s].add(e);
				llist[e].add(s);
			}
			
			/*
			 * 짝수거리 + 짝수거리 = 짝수거리
			 * 홀수거리 + 홀수거리 = 짝수거리
			 * 
			 * 쌍을 구하는 것이기 때문에 조합의 형태로 값을 구한다.
			 * evenCount_Combination_2 + oddCount_Combination_2 의 모습으로 구한다.
			 * 
			 * 왜냐하면  특정노드에 따라 노드 간의 거리를 구하고, 이후에 홀수거리끼리 짝수거리끼리의 노드들의 쌍을 구해주면, 
			 * 그것또한 짝수거리만 가지는 노드들의 개수가 되기 때문이다.
			 *
			 * 그리고 evenCount 를 초기에 1을 준 이유는 시작노드를 우선적으로 포함하려고 하기 때문이다.
			 * 그래서 시작노드부터 거리가 짝수가 되는 것을 추가해주기 위함이다.
			 * 
			 * 반면에 oddCount 는 0 인 이유는 시작노드를 포함하지 않고 이후에 홀수 노드를 포함하려고 하기 때문이다.
			 */
			
			UseRecursiveSolution(llist, N);
			int res = (evenCount*(evenCount-1))/2 + (oddCount*(oddCount-1))/2;
			evenCount = 1; oddCount = 0;
			bw.write(res + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int UseRecursiveSolution(LinkedList<Integer>[] startList, int N){
		int answer = 0;
		boolean visited[] = new boolean[N];
		Arrays.fill(visited, false);
		
		recursiveDFS(startList, visited, 0, 1);
		
		return answer;
	}
	
	public static void recursiveDFS(LinkedList<Integer>[] startList, boolean[]visited, int startNode, int edgeCount){
		visited[startNode] = true;
		LinkedList<Integer> endList = startList[startNode];
		ListIterator<Integer> listIterator = endList.listIterator();
		
		while(listIterator.hasNext()){
			int nextNode = listIterator.next();
			// 미방문
			if(visited[nextNode] == false){
				if(edgeCount % 2 == 0){
					evenCount++;
				}
				else{
					oddCount++;
				}
				
				recursiveDFS(startList, visited, nextNode, (edgeCount+1));
			}
		}
	}
}
