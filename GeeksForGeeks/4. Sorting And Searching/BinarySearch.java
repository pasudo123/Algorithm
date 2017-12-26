package GeeksforGeeks;

import java.util.Arrays;

public class BinarySearch {
	public static void main(String[]args){
		int[]array = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
		int findNumber = 5;
		
		// 이진검색은 우선 정렬되어있음을 가정
		// ** 오름차순 ** 
		Arrays.sort(array);
		
		int searchIndex = 0;
		
//		if((searchIndex = PASUDO_IterativeBinarySearch(array, findNumber)) != -1){
//			System.out.println(array[searchIndex] + "s Is Exist");
//		}
//		else{
//			System.out.println("Not Exist");
//		}
		
//		if((searchIndex = PASUDO_RecursiveBinarySearch(array, findNumber, 0, array.length - 1)) != -1){
//			System.out.println(array[searchIndex] + " Is Exist");
//		}
//		else{
//			System.out.println("Not Exist");
//		}
		
		int[]revArray = new int[array.length];
		
		for(int i = 0; i < array.length; i++)
			revArray[i] = array[array.length - 1 - i];
		System.out.println(Arrays.toString(revArray));
		
		if((searchIndex = PASUDO_DesIterativeBinarySearch(revArray, findNumber)) != -1){
			System.out.println(revArray[searchIndex] + " Is Exist");
		} 
		else {
			System.out.println("Not Exist");
		}
	}
	
	// Iterative : 반복
	public static int PASUDO_IterativeBinarySearch(int[]array, int number){
		int left = 0;
		int right=  array.length - 1;
		int middle = 0;
		
		// O(logn)
		while(left <= right){
			middle = (left + right) / 2;
			// middle = left + (right - left) / 2;
			
			if(array[middle] == number)
				return middle;
			
			// 중간 값보다 더 우측에 존재한다.
			if(array[middle] < number){
				left = middle + 1;
			}
			// (array[middle] > number)
			// 중간 값보다 더 좌측에 존재한다.
			else{
				right = middle - 1;
			}
		}
		
		return -1;
	}
	
	// Iterative : 반복 (Descending Order : 내림차순)
	public static int PASUDO_DesIterativeBinarySearch(int[]array, int number){
		int left = 0;
		int right = array.length - 1;
		int middle = 0;
		
		// O(logn)
		while(left <= right){
			middle = (left + right) / 2;
			
			if(array[middle] == number){
				return middle;
			}
			
			// 중간 값보다 더 우측에 존재한다. 
			// 내림차순이니깐 중간 값보다 값이 더 작다.
			if(array[middle] > number)
				left = middle + 1;
			
			// 중간 값보다 더 좌측에 존재한다.
			// 내림차순이니깐 중간 값보다 값이 더 크다.
			//(array[middle] < number)
			else
				right = middle - 1;
		}
		
		return -1;
	}
	
	// Recursive : 재귀
	public static int PASUDO_RecursiveBinarySearch(int[]array, int number, int left, int right){
		int middle = (left + right) / 2;
		
		if(left <= right){
			if(array[middle] == number)
				return middle;
			
			if(array[middle] < number){
				return PASUDO_RecursiveBinarySearch(array, number, middle + 1, right);
			}
			// (array[middle] > number)
			else{
				return PASUDO_RecursiveBinarySearch(array, number, left, middle - 1);
			}
		}

		return -1;
	}
}
