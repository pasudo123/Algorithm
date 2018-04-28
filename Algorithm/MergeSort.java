package Algorithm;

class MSort{
	int[]arr;

	public void setArray(int[]array){
		this.arr = array;
	}
	
	public void sorting(){
		mergeSort(this.arr, 0, arr.length-1);
	}
	
	public void mergeSort(int[]arr, int left, int right){
		if(left < right){
			int mid = (left + right) / 2;
			
			mergeSort(arr, left, mid);
			mergeSort(arr, mid+1, right);
			merge(arr, left, mid, right);
		}
	}
	
	// Merge two SubArray of arr[]
	public void merge(int[]arr, int left, int mid, int right){
		int n1 = mid - left + 1;	// 서브어레이의 크기를 확인 (left ~ mid)
		int n2 = right - mid;		// 서브어레이의 크기를 확인 ((mid)+1 ~ right)
		
		// 분할된 서브어레이의 배열 형성
		int[]L = new int[n1];
		int[]R = new int[n2];
		
		// 실제 어레이에서 서브어레이로 값 복사
		for(int i = 0; i < n1; i++)
			L[i] = arr[left + i];		// left 부터 차근차근
		for(int i = 0; i < n2; i++)
			R[i] = arr[(mid+1) + i];	// (mid+1) 부터 차근차근 
		
		// 인덱스 초기화
		int i = 0, j = 0;
		
		// 합병인덱스 k
		int k = left;
		
		while(i < n1 && j < n2){
			if(L[i] <= R[j]){
				arr[k] = L[i];
				i++;
			}
			else{ // (L[i] > R[j])
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		
		// 남아있는 L[] 을 삽입하고 이후에 남아있는 R[] 을 삽입한다.
		// (1) : L[] 삽입
		while(i < n1){
			arr[k] = L[i];
			k++;
			i++;
		}
		
		// (2) : R[] 삽입
		while(j < n2){
			arr[k] = R[j];
			k++;
			j++;
		}
	}
}

public class MergeSort {
	public static void main(String[]args){
		/*
		 * MergeSort
		 * 들어온 배열을 크기가 1이 될때까지 재귀호출로 계속 나눈다.
		 * 그리고 배열의 크기가 1이 되면 병합(Merge) 시키며 그 과정을 전체 배열이 병합될 때까지 수행한다.
		 * 
		 * MergeSort SVG Image
		 * https://en.wikipedia.org/wiki/File:Merge_sort_algorithm_diagram.svg
		 * */
		
		int[]array = {10, 24, 2, 44, 38, 92, 43, 5, 18, 34};
//		int[]array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		MSort mergeSort = new MSort();
		mergeSort.setArray(array);
		mergeSort.sorting();
		
		for(int val : mergeSort.arr)
			System.out.print(val + " ");
		System.out.println();
		// >> 2 5 10 18 24 34 38 43 44 92 
	}
}
