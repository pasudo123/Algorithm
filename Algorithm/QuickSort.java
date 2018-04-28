package Algorithm;


class QSort{
	int[]arr;
	
	public void setArray(int[]array){
		this.arr = array;
	}
	
	public void sorting(){
		quickSort(this.arr, 0, arr.length-1);
	}
	
	public void quickSort(int[]arr, int low, int high){
		if(low < high){
			// pivot(=middle) 값 선정 이후
			// pivot 이전과 이후로 나누어서 quickSort
			int mid = partition(arr, low, high);
			
			System.out.println("quickSort");
			quickSort(arr, low, mid-1);
			quickSort(arr, mid+1, high);
		}
	}
	
	public int partition(int[]arr, int low, int high){
		// 마지막 수를 피봇으로 정한다.
		int pivot = arr[high];
		
		int i = low - 1; // 해당 왼쪽값보다 이전의 인덱스
		int j = low;	 // 처음 시작하는 왼쪽 인덱스
		
		// i 는 -1 부터 시작한다.
		for (j = low; j <= high - 1; j++){
			// 현재 값이 피봇보다 동일하거나 작은 경우
			if(arr[j] <= pivot){
				i++;
				
				// Swap()
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		// (i + 1) 이전은 피봇보다 작은 값
		// (i + 1) 이후는 피봇보다 큰 값
		// 피봇(인덱스 마지막 값)을 고정시키기 위한 Swap()
		System.out.println(arr[i+1] + ", " + arr[high]);
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		
		return (i+1);
	}
}

public class QuickSort {
	public static void main(String[]args){
		/*
		 * QuickSort 에는 피봇을 선정하는 다양한 기준이 존재한다.
		 * (1) 첫번째 수를 피봇으로 정하는 경우
		 * (2) 마지막 수를 피봇으로 정하는 경우
		 * (3) 랜덤한 수를 피봇으로 정하는 경우
		 * (4) 중앙값 수를 피봇으로 정하는 경우
		 * 
		 * reference : http://www.geeksforgeeks.org/quick-sort/
		 * */
		
		int[]array = {10, 24, 2, 44, 38, 92, 43, 5, 18, 34};
//		int[]array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		QSort qSort = new QSort();
		qSort.setArray(array);
		qSort.sorting();
		
		for(int val : qSort.arr)
			System.out.print(val + " ");
		System.out.println();
		// >> 2 5 10 18 24 34 38 43 44 92 
		
		/* 
		 * QuickSort 시간복잡도
		 * 모든 배열이 정렬 되어있는 경우, 피봇을 최대 혹은 최소 값을 선택하여 최악의 시간복잡도를 가지게 되는 것이다. 그 경우 O(n^2)
		 * 
		 * 하지만, 
		 * 모든 배열이 정렬 되어있지 않고, 파티션 되는 부분이 일정한 개수로 나뉘게 된다면 O(nlogn) 의 평균적인 시간복잡도를 가진다.
		 * */
	}
}
