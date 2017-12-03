package KAKAO;

import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class QualifierKakao1 {
	public static void main(String[]args){
		
		/* 
		 * Kakao Algorithm 1 (카카오코드 예선)
		 * https://programmers.co.kr/learn/challenges/591
		 */
		
		int m = 6;
		int n = 4;
	    int[][]picture = {{1, 1, 1, 0}, 
						  {1, 2, 2, 0}, 
						  {1, 0, 0, 1}, 
						  {0, 0, 0, 1}, 
						  {0, 0, 0, 3}, 
						  {0, 0, 0, 3}};
		
		System.out.println(Arrays.toString(solution(m, n, picture)));
	}
	
	public static int[] solution(int m, int n, int[][] picture) {
	      int numberOfArea = 0;
	      int maxSizeOfOneArea = 0;
	      int size = 0;
	      int[] answer = new int[2];
	      
	      // m : 행, n : 열
	      int move1[] = {1, -1, 0, 0};
	      int move2[] = {0, 0, 1, -1};
	      
	      int[][]visited = new int[m][n];
	      for(int i = 0; i < visited.length; i++)
	    	  Arrays.fill(visited[i], 0);
	      
	      Queue<StringBuilder>q = new LinkedList<StringBuilder>();
	      
	      for(int row = 0; row < picture.length; row++){
	    	  for(int col = 0; col < picture[row].length; col++){
	    		  
	    		  // 색상, 미방문
	    		  if(picture[row][col] != 0 && visited[row][col] == 0){
	    			  numberOfArea++; // 색상영역 추가
	    			  
	    			  int color = picture[row][col];
	    			  StringBuilder start = new StringBuilder(row + "&" + col);
	    			  q.add(start);
	    			  visited[row][col] = 1;
	    			  
	    			  // 특정 색상영역을 통해 BFS
	    			  while(!q.isEmpty()){
	    				  start = q.poll();
	    				  int r = Integer.parseInt(start.toString().split("&")[0]);
	    				  int c = Integer.parseInt(start.toString().split("&")[1]);
	    				  
	    				  size++;
	    				  
	    				  for(int mv = 0; mv < 4; mv++){
	    					  int mr = r + move1[mv];
	    					  int mc = c + move2[mv];
	    					  
	    					  // 범위소속, 미방문, 동일한 색상
	    					  if(mr >= 0 && mr <= m-1 && mc >= 0 && mc <= n-1 && visited[mr][mc] == 0 && picture[mr][mc] == color){
	    						  start = new StringBuilder(mr + "&" + mc);
	    						  q.add(start);
	    						  visited[mr][mc] = 1;
	    					  }
	    				  }
	    			  }// while()
	    		  }// if
	    		  
	    		  if(size > maxSizeOfOneArea)
	    			  maxSizeOfOneArea = size;
	    		  
	    		  size = 0;
	    	  }// for
	      }// for
	      
	      answer[0] = numberOfArea;
	      answer[1] = maxSizeOfOneArea;
	      return answer;
	  }
}
