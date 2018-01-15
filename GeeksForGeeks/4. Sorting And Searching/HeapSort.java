import java.util.*;
import java.io.*;

class HeapSort {
  public static void main(String[] args) {
    int[]array = {10, 24, 2, 44, 38, 92, 43, 5, 18, 34};
    
    sort(array);
    
    for(int element : array)
      System.out.print(element + "  ");
    System.out.println();
  }
  
  static void sort(int[]array){
    int size = array.length;
    
    // leafNode(childNode가 없는 Node)를 제외한 모든 부모노드들을 대상으로 
    // bottom-up 방식으로 이어간다.
    // O(N)
    for(int i = size/2 - 1; i >= 0; i--)
      buildHeap(array, i, size-1);
    
    // rooNode 와 가장 하위 노드의 값을 변경
    for(int i = size-1; i >= 0; i--){
      int temp = array[0];
      array[0] = array[i];
      array[i] = temp;
      
      // heapSize 는 -1씩 줄어든다.
      // reference : http://priv.tistory.com/61
      buildHeap(array, 0, i);
    }
  }
  
  // buildHeap : O(LogN)
  static void buildHeap(int[]array, int index, int heapSize){
    // 부모노드는 자식노드보다 항상 커야한다. >> 최대 힙 트리 구성 (BuildHeap)
    // 배열의 시작인덱스가 0이기 때문에 leftIndex 와 rightIndex +1 & +2 로 설정
    int largest = index;
    int leftIndex = index * 2 + 1;
    int rightIndex = index * 2 + 2;
    int temp = 0;
    
    // heapSize 범위에 소속 && 왼쪽 자식의 값이 더 큰 경우 swap()
    // if(leftIndex < heapSize && array[leftIndex] > array[index]){
    //   temp = array[leftIndex];
    //   array[leftIndex] = array[index];
    //   array[index] = temp;
    // }
    
    // 오른쪽 자식의 값이 더 큰 경우 swap()
    // if(rightIndex < heapSize && array[rightIndex] > array[index]){
    //   temp = array[rightIndex];
    //   array[rightIndex] = array[index];
    //   array[index] = temp;
    // }
    
    // 위의 방식을 하는 것보단 index 만 변경해주는 것이 더 효율적
    if(leftIndex < heapSize && array[leftIndex] > array[largest])
      largest = leftIndex;
      
    if(rightIndex < heapSize && array[rightIndex] > array[largest])
      largest = rightIndex;
      
    // 변경이 일어났다는 의미.
    if(largest != index){
      temp = array[largest];
      array[largest] = array[index];
      array[index] = temp;
      
      buildHeap(array, largest, heapSize);
    }
  }
}