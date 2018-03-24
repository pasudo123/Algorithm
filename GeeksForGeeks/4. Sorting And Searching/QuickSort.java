import java.util.*;
import java.io.*;

public class Main{
  static InputStreamReader isr = new InputStreamReader(System.in);
  static BufferedReader br = new BufferedReader(isr);
  
  static OutputStreamWriter osw = new OutputStreamWriter(System.out);
  static BufferedWriter bw = new BufferedWriter(osw);
  static StringTokenizer st = null;
  
  public static void main(String[]args) throws IOException{
    int TC = Integer.parseInt(br.readLine()); // Test Case
    
    for(int t = 0; t < TC; t++){
      int size = Integer.parseInt(br.readLine());
      int[]array = new int[size];
      st = new StringTokenizer(br.readLine());
      
      for(int i = 0; i < size; i++)
        array[i] = Integer.parseInt(st.nextToken());
        
      quickSort(array, 0, size-1);
      
      for(int element : array)
        bw.write(String.valueOf(element) + " ");
      bw.write("\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }
  
  // GFG : Execution Time:0.12
  static void quickSort(int[]array, int left, int right){
    // left < right
    if(left < right){
      int pivot = partition(array, left, right);
      
      quickSort(array, left, pivot-1);
      quickSort(array, pivot+1, right);
    }
  }
  
  static int partition(int[]array, int left, int right){
    // pivot은 마지막 인덱스를 선택
    int pivot = array[right];
    int i = left - 1;
    int j = 0;
    
    /*
    i는 pivot보다 작은 값
    j는 piovt보다 큰 값을 가진다.
    */
    for(j = left; j <= right - 1; j++){
      // j의 인덱스 값은 pivot 보다 커야하는데,
      // 값이 작기 때문에 swap() 과정을 거친다.
      if(array[j] <= pivot){
        // smallestIndex 
        i++;
        
        // pivot을 기준으로 i와 j의 인덱스가 다른 경우
        if(i != j){
          int temp = array[i];
          array[i] = array[j];
          array[j] = temp;          
        }
      }// if
    }// for
    
    // swap(array[i+1], pivot);
    // pivot의 인덱스 고정
    int temp = array[i+1];
    array[i+1] = array[right];
    array[right] = temp;
    
    // i+1 은 현재 피봇의 위치값이다.
    // i+1 값을 제외한 partition을 나눈다.
    return (i+1);
  }
}
