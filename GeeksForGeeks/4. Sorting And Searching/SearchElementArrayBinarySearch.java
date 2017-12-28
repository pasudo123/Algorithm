package GeeksforGeeks;

/*
 * [ Bitonic Sequence ]
 * 
 * - Sequence is called Bitonic if it is first increasing, then decreasing
 * - In other words, an array arr[0, ... , n-1] is Bitonic if there exists 
 * - index i where 0 <= i <= n-1 such that
 * - x0 <= x1 <= x2 ... <= xi and xi >= x(i+1) >= x(i+2) ... >= x(n-1)
 *
 * 증가하는 순서대로 정렬된 시퀀스는 감소 부분이 비어있는 Bitonic 으로 간주된다.
 * 비슷하게, 감소하는 순서대로 정렬된 시퀀스는 증가부분이 비어있는 Bitonic 으로 간주. 
 * */

final class SearchElementArrayBinarySearch {
	// 오름차순 바이너리 서치
	public static int PASUDO_AscIterativeBinarySearch(int[]array, int start, int end, int number){
		int left = start;
		int right=  end;
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
	
	// 내림차순 바이너리 서치
	public static int PASUDO_DesIterativeBinarySearch(int[]array, int start, int end, int number){
		int left = start;
		int right = end;
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
	
	// Finds the index of maximum element
	// or the Bitonic point
	public static int findBitonicPoint(int[]array){
		int left = 0;
		int right = array.length-1;
		
		// Bitonic 을 찾기 위함. 
		while(left <= right){
			if(left == right)
				return left;
			
			if(left + 1 == right)
				return (array[left] > array[right])?left:right;
			
			int middle = (left + right) / 2;
			
			if(array[middle] > array[middle - 1] && array[middle] > array[middle + 1])
				return middle;
			
			else if(array[middle] > array[middle - 1] && array[middle + 1] > array[middle])
				left = middle + 1;
			
			else if(array[middle] < array[middle-1] && array[middle+1] < array[middle])
				right = middle - 1;
			
			else
				return -1;
		}
		
		// 해당하는 값이 존재하지 않는 경우
		return -1;
	}
	
	public static int searchBitonic(int[]array, int findNumber){
		
		// returns the index of maximum element
		int k = findBitonicPoint(array);
		
		if(array[k] == findNumber)
			return k;
		
		else if(findNumber > array[k])
			return -1;
		
		int temp = PASUDO_AscIterativeBinarySearch(array, 0, k-1, findNumber);
		if(temp != -1)
			return temp;
		
		return PASUDO_DesIterativeBinarySearch(array, k+1, array.length-1, findNumber);
	}
}
