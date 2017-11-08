package Git;

import java.util.*;

public class Fibonacci {
	
	public static long arrayFibo[] = new long[10000];		// 메모이제이션  
	public static long matrixFibo[][] = {{1, 1}, {1, 0}};	// 피보나치 행렬
	
	public static void main(String[]args){
//		System.out.println(fibo(10));
//		System.out.println(fiboTailRecursion(100, 1, 0));
		
		// 메모이제이션을 위한 초기화
		Arrays.fill(arrayFibo, 0);
		arrayFibo[0] = 0;
		arrayFibo[1] = 1;
		
//		System.out.println(memoFibo(100));
//		System.out.println(matrixFibo(100)[0][1]);
	}
	
	
	// 일반적인 피보나치 수열 함수.
	public static int fibo(int n){
		if (n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else
			return (fibo(n-1) + fibo(n-2));
	}
	
	
	// 꼬리 재귀 함수
	public static long fiboTailRecursion(int n, long beforeFibo, long beforebeforeFibo){
		long currentFibo = 0;
		
		if (n == 0)
			return n * beforeFibo;
		else if(n == 1)
			return n * beforeFibo;
		else{
			// 이번 호출에서 피보나치 수를 계산
			currentFibo = beforeFibo + beforebeforeFibo;
			
			// (1) 이후 재귀호출을 위한 이전 피보나치 수를 한 칸 뒤로 미룬다.
			beforebeforeFibo = beforeFibo;
			
			// (2) 이후 재귀호출을 위한 현재 피보나치 수를 한 칸 뒤로 미룬다.
			beforeFibo = currentFibo;
			
			return fiboTailRecursion(n-1, beforeFibo, beforebeforeFibo);
		}
	}
	
	
	// 정리된 꼬리 재귀 함수
	public static long fiboTailRecursion2(int n, long beforeFibo, long beforebeforeFibo){
		long currentFibo = 0;
		
		if (n == 0)
			return n * beforeFibo;
		else if(n == 1)
			return n * beforeFibo;
		else
			return fiboTailRecursion(n-1, beforeFibo + beforebeforeFibo, beforebeforeFibo);
	}
	
	
	// memoization 방식
	public static long memoFibo(int n){
		if(n == 0 || n == 1)
			return arrayFibo[n];
		else if (arrayFibo[n] != 0)
			return arrayFibo[n];
		else{
			arrayFibo[n] = memoFibo(n-1) + memoFibo(n-2);
			return arrayFibo[n];
		}
	}
	
	
	public static long[][] multiply(long[][]mat1, long[][]mat2){
		long newMat[][] = new long[2][2];
		
		newMat[0][0] = mat1[0][0] * mat2[0][0] + mat1[0][1] * mat2[1][0];
		newMat[0][1] = mat1[0][0] * mat2[0][1] + mat1[0][1] * mat2[1][1];
		newMat[1][0] = mat1[1][0] * mat2[0][0] + mat1[1][1] * mat2[1][0];
		newMat[1][1] = mat1[1][0] * mat2[0][1] + mat1[1][1] * mat2[1][1];
		
		return newMat;
	}	
	
	// Fibo Matrix 방식
	public static long[][] matrixFibo(int n){
		if(n == 1)
			return matrixFibo;
		
		long[][]mat = matrixFibo(n / 2);
		mat = multiply(mat, mat);
		
		// 홀수인 경우
		// 기본 매트릭스를 하나 더 곱해준다.
		if(n % 2 == 1){
			mat = multiply(mat, matrixFibo);
		}
		
		// 결과값으로 나온 매트릭스.
		// mat[0][1] 또는 mat[1][0] 값이 n 에 해당하는 피보나치 수의 값이다. 
		return mat;
	}
}