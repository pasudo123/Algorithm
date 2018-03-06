package LeetCode;

import java.util.Arrays;

public class Solution {
	public static boolean isValid(String s) {
        Stack stack = new Stack();
        char openParenthness[] = {'(', '[', '{'};
        char closeParenthness[] = {')', ']', '}'};
        
        for(int i = 0; i < s.length(); i++){
        	char c = s.charAt(i);
        	
        	for(int bIndex = 0; bIndex < 3; bIndex++){
        		if(c == openParenthness[bIndex]){
        			stack.push(c);
        			break;
        		}
        		if(c == closeParenthness[bIndex]){
        			if(stack.size() == 0)
        				return false;
        			
        			char openChar = stack.pop();
        			if(!(openChar == '(' && c == ')' || openChar == '[' && c == ']' || openChar == '{' && c == '}'))
        				return false;
        		}
        	}
        }
        
        if(stack.size() >= 1)
        	return false;
        
        return true;
    }
	
	static class Stack{
		char[]array;
		int capacity;
		int size;
		
		Stack(){
			size = 0;
			capacity = 10;
			array = new char[capacity];
		}
		
		void push(char c){
			if(size < capacity){
				array[size++] = c;
				return;
			}
			
			// size >= 10
			resize();
			array[size++] = c;
		}
		
		void resize(){
			capacity = capacity + (capacity >> 1);
			array = Arrays.copyOf(array, capacity);
		}
		
		char pop(){
			if(size <= 0){
				System.out.println("배열 음수 값 반환");
				System.exit(1);
			}
			
			return array[--size];
		}
		
		int size(){
			return size;
		}
	}
	
	public static void main(String[]args){
		System.out.println(isValid("()"));
	}
}
