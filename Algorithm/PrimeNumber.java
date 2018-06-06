import java.util.ArrayList;
import java.util.Arrays;

public class PrimeNumber {
	
	// 1 부터 구할 소수의 숫자
	public static final int END_NUMBER = 1000;
	
	public static void main(String[]args){
		/*
		 * (1) 2 ~ 10000 까지 소수를 출력하는 예제
		 * 
		 * */
		ArrayList<Integer> primeNumber = new ArrayList<Integer>();
		
		/** nano second 단위 (10^-9) **/
		long startTime = System.nanoTime();
		
//		process1(primeNumber);
//		process2(primeNumber);
//		process3(primeNumber);
//		process4(primeNumber);
		
		long endTime = System.nanoTime();
		long termTime = endTime - startTime;
		
		System.out.println("소요시간 : " + termTime + " (nano sec)");
	}
	
	// 2부터 차례대로 나누는 방법
	public static void process1(ArrayList<Integer> primeNumber) {
		clearList(primeNumber);
		
		// 1은 소수가 아니기에 제외
		for (int number = 2; number <= END_NUMBER; number++) {

			boolean flag = true;

			for (int div = 2; div < number; div++) {
				if (number % div == 0) {
					flag = false;
					break;
				}
			}

			if (flag)
				primeNumber.add(number);
		}

		listDisplay(primeNumber);
	}
	
	// 소수는 자연수를 이루는 성분
	// PrimeNumber 로 나눈다.
	public static void process2(ArrayList<Integer> primeNumber) {
		clearList(primeNumber);
		
		for (int number = 2; number <= END_NUMBER; number++) {

			boolean flag = true;

			if (primeNumber.size() != 0) {
				for (int i = 0; i < primeNumber.size(); i++) {
					if (number % primeNumber.get(i) == 0) {
						flag = false;
						break;
					}
				}
			}

			if (flag)
				primeNumber.add(number);
		}

		listDisplay(primeNumber);
	}

	// 찾고자 하는 범위의 수를 나열한 이후에, 
	// 배수를 제거하는 방식
	// [ 에라토스테네스 체 ]
	public static void process3(ArrayList<Integer> primeNumber) {
		clearList(primeNumber);
		
		boolean[]primeArray = new boolean[END_NUMBER + 1]; // 1 ~ END_NUMBER
		Arrays.fill(primeArray, true);
		
		for(int i = 2; i <= END_NUMBER; i++){
			if(primeArray[i]){
				int mul = 2;
				int value = 0;
				
				while(true){
					value = i*mul;
					
					if(value <= END_NUMBER){
						// 자연수는 소수로
						if(primeArray[value])
							primeArray[value] = false;
					}
					// 범위 초과
					else
						break;
					
					mul++;
				}
			}
		}
		
		for(int i = 2; i < primeArray.length; i++){
			if(primeArray[i]){
				primeNumber.add(i);
			}
		}
		
		listDisplay(primeNumber);
	}
	
	public static void process4(ArrayList<Integer> primeNumber){
		clearList(primeNumber);
		
		boolean[]primeArray = new boolean[END_NUMBER + 1]; // 1 ~ END_NUMBER
		Arrays.fill(primeArray, true);
		
		int sqrtValue = (int)Math.sqrt(END_NUMBER);

		for(int i = 2; i <= sqrtValue; i++){
			if(!primeArray[i])
				continue;
			
			/** j = i*i 로 하여도 된다. **/
			for(int j = i+i; j <= END_NUMBER; j += i){
				primeArray[j] = false;
			}
		}
		
		for(int i = 2; i < primeArray.length; i++){
			if(primeArray[i]){
				primeNumber.add(i);
			}
		}
		
		listDisplay(primeNumber);
	}
	
	public static void listDisplay(ArrayList<Integer> list){
		
		for(int i = 0; i < list.size(); i++){
			if((i+1) % 20 == 0)
				System.out.println();
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println("\n소수개수 : " + list.size());
	}
	
	public static void clearList(ArrayList<Integer> list){
		list.clear();
	}
}

