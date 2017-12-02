package Algorithm;

class HSort{
	int[]arr;
	
	public HSort(){
		arr = new int[100];
	}
	
	// BuildMaxHeap(arr, 10);
	public void buildMaxHeap(int[]heapArr, int length){
		if(length == 1){
			arr[1] = heapArr[1];
			return;
		}
		
		// 마지막 인덱스부터 차례대로 올라가는 형태 
		// 루트노드 이전까지 최대 힙을 구성한다.
		for(int end = length; end >= 2; end--){
			int parent = end / 2;
			int child = end;
			
			// 최대 힙을 위함.
			if(heapArr[parent] < heapArr[child]){
				int temp = heapArr[parent];
				heapArr[parent] = heapArr[child];
				heapArr[child] = temp;
			}
		}
		
		// heapPrint(heapArr); : 값을 체크하기 위함.
		// 루트노드를 어레이 마지막에 삽입 >> 루트노드에 마지막 값 대입.
		int temp = heapArr[1];
		heapArr[1] = heapArr[length];
		this.arr[length] = temp;
		buildMaxHeap(heapArr, length-1);
	}
	
	public void buildMinHeap(){
		// 다른 부분은 동일하고 최소힙을 위한 부등호만 변경해주면 가능하다.
//		if(heapArr[parent] > heapArr[child]){
//			int temp = heapArr[parent];
//			heapArr[parent] = heapArr[child];
//			heapArr[child] = temp;
//		}
	}
	
	// 매개변수 있는 것
	public void heapPrint(int[]paramArr){
		System.out.print(">> ");
		for(int i = 1; i < paramArr.length; i++){
			System.out.print(paramArr[i] + " ");
		}
		System.out.println();
	}
	
	// 매개변수 없는 것
	public void heapPrint(){
		for(int i = 1; i < this.arr.length; i++){
			if(this.arr[i] == 0)
				break;
			System.out.print(this.arr[i] + " ");
		}
		System.out.println();
	}
}

public class HeapSort {
	public static void main(String[]args){
		/*
		 * 힙소트 (Heap Sort)
		 * 힙 자료구조를 구성해야하며, 힙의 자료구조는 크게 두가지로 나뉜다.
		 * (1) 최대 힙
		 * 부모노드가 자식노드 보다 항상 크거나 같아야 한다.
		 * 
		 * (2) 최소 힙
		 * 부모노드가 자식노드 보다 항상 작거나 같아야 한다.
		 * */
		
		// 힙소트에서 들어오는 어레이의 0 인덱스는 비운다.
		// 인덱스는 1부터 시작한다.
		int[]array = {0, 10, 24, 2, 44, 38, 92, 43, 5, 18, 34};
//		int[]array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		HSort heapSort = new HSort();
		heapSort.buildMaxHeap(array, array.length-1);
		heapSort.heapPrint();
	}
}
