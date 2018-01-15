import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
    int[]array = {10, 24, 2, 44, 38, 92, 43, 5, 18, 34};
    
    mergeSort(array, 0, array.length-1);
    
    for(int element : array)
      System.out.print(element + "  ");
    System.out.println();
  }
  
  static void mergeSort(int[]array, int left, int right){
    if(left < right){
      int middle = (left + right) / 2;
    /*
    left 0
    right 1
    middle 0
    */
    mergeSort(array, left, middle);
    mergeSort(array, middle + 1, right);
    merge(array, left, middle, right);
    }
  }
  
  // -- PASUDO : Execution Time:0.43
  static void merge(int[]array, int left, int middle, int right){
    int size1 = middle - left + 1;
    int size2 = right - middle;
    
    /*
    size1 = 0 - 0 + 1
    size2 = 1 - 0
    */    
    
    int[]array1 = new int[size1];
    int[]array2 = new int[size2];
    
    for(int i = 0; i < array1.length; i++)
      array1[i] = array[i + left];
    
    for(int i = 0; i < array2.length; i++)
      array2[i] = array[middle + 1 + i];
      
    int i = 0, j = 0;
    int k = left; // 실제 배열의 인덱스 역할
    
    while(i < size1 && j < size2){
      if(array1[i] <= array2[j]){
        array[k] = array1[i];
        k += 1;
        i += 1;
      }
      else{
        array[k] = array2[j];
        k += 1;
        j += 1;
      }
    }// while
    
    while(i < size1){
      array[k] = array1[i];
      k++;
      i++;
    }
    
    while(j < size2){
      array[k] = array2[j];
      k++;
      j++;
    }
  }
}