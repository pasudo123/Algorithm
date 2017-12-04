package KAKAO;

public class QualifierKakao3 {
	public static void main(String[] args) {
		int m = 3;
		int n = 6;
//		int[][]cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		int[][]cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
		
		System.out.println(solution(m, n, cityMap));
	}


	public static int solution(int m, int n, int[][] cityMap) {
		int MOD = 20170805;
		
		int[][]vertical = new int[m+1][n+1];
		int[][]horizontal  = new int[m+1][n+1];
		vertical[1][1] = 1;		// 수직
		horizontal[1][1] = 1;	// 수평
		
		for(int i = 1; i < m+1; i++){
			for(int j = 1; j < n+1; j++){
				// 자유롭게 이동
				if(cityMap[i-1][j-1] == 0){
					vertical[i][j] += (vertical[i-1][j] + horizontal[i][j-1]) % MOD;
					horizontal[i][j] += (horizontal[i][j-1] + vertical[i-1][j]) % MOD; 
				}
				// 통행 금지
				else if(cityMap[i-1][j-1] == 1){
					vertical[i][j] = 0;
					horizontal[i][j] = 0;
				}
				// 일방향 통행
				else{
					vertical[i][j] += vertical[i-1][j];
					horizontal[i][j] += horizontal[i][j-1];
				}
			}
		}
		
//		for(int i = 1; i <= m; i++){
//			for(int j = 1; j <= n; j++){
//				System.out.print(vertical[i][j] + "  ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
//		
//		for(int i = 1; i <= m; i++){
//			for(int j = 1; j <= n; j++){
//				System.out.print(horizontal[i][j] + "  ");
//			}
//			System.out.println();
//		}
		
		return (horizontal[m][n-1] + vertical[m-1][n]) % MOD;
	}

}
