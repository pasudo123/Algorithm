package GeeksforGeeks;

import java.util.*;
import java.io.*;

public class StringArrayReversal {
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	
	public static OutputStreamWriter osw = new OutputStreamWriter(System.out);
	public static BufferedWriter bw = new BufferedWriter(osw);
	
	public static void main(String[]args) throws IOException{
		int TC = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < TC; i++){
			String input = br.readLine();
			bw.write(reverse(input)+"\n");
		}
		bw.flush();
	}
	
	// -- PASUDO : Execution Time:0.08
	public static String Solution(String paramStr){
		String answer = "";
		
		List<Character> list = new ArrayList<Character>();
		
		// O(n)
		for(int i = 0; i < paramStr.length(); i++){
			// 알파벳인경우
			if(paramStr.charAt(i) >= 'a' && paramStr.charAt(i) <= 'z' || paramStr.charAt(i) >= 'A' && paramStr.charAt(i) <= 'Z')
				list.add(paramStr.charAt(i));
		}
		
		int mid = list.size() / 2;
		int end = list.size() - 1;
		
		// 서로간의 위치 변경
		for(int start = 0; start < mid; start++){
			char ch = list.get(start);			// ArrayList 인덱스 접근 : O(1);
			list.set(start, list.get(end));		// -- 내부적으로 index 배열 범위 초과 여부와 get() 메소드가 구현되어 있다. O(n)
			list.set(end, ch);					//    그리고 해당 인덱스에 대한 기존의 값을 리턴한다.
			end--;								  
		}
		
		int listIndex = 0;
		for(int i = 0; i < paramStr.length(); i++){
			if(paramStr.charAt(i) >= 'a' && paramStr.charAt(i) <= 'z' || paramStr.charAt(i) >= 'A' && paramStr.charAt(i) <= 'Z'){
				answer += list.get(listIndex++);
			}
			else{
				answer += paramStr.charAt(i);
			}
		}
		
		return answer;
	}
	
	// -- SolutionOfGFG : Execution Time:0.06
	public static String reverse(String paramStr){
		int left = 0;
		int right = paramStr.length() - 1;
		char[] charArray = paramStr.toCharArray();
				
		while(left < right){
			if(!checkAlphabet(charArray[left]))			// 알파벳 아닌 경우
				left++;
			else if(!checkAlphabet(charArray[right]))	// 알파벳 아닌 경우
				right--;
			else{
				// 양쪽다 알파벳이기 때문에 두 위치의 글자를 교체한다.
				char tempCh = charArray[left];
				charArray[left] = charArray[right];
				charArray[right] = tempCh;
				
				left++;
				right--;
			}
		}
		
		String result = new String();
		for(char c : charArray)
			result += c;
		
		return result;
	}
	
	// -- SolutionOfGFG
	public static boolean checkAlphabet(char c){
		// 알파벳인 경우
		if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
			return true;
		// 특수문자인 경우
		else
			return false;
	}
}
