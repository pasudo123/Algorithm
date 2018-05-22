import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		BufferedWriter bw = new BufferedWriter(osw);
		
		char[]array = br.readLine().toCharArray();
		Stack<String> stack = new Stack<String>();
		boolean flag = true;
		
		for(int i = 0; i < array.length; i++){
			char c = array[i];
			
			if(c == '[' || c == '(')
				stack.push(String.valueOf(c));
			
			else{
				if(stack.size() == 0){
					flag = false;
					break;
				}
				
				String s = stack.peek();
				
				if(c == ']'){
					if("[".equals(s)){
						stack.pop();
						stack.push("3");
					}
					else if("(".equals(s)){
						flag = false;
						break;
					}
					// 숫자 (감싼 여부 확인)
					else{
						int val = Integer.parseInt(stack.pop());
						val *= 3;
						
						if(stack.size() == 0){
							flag = false;
							break;
						}
						
						s = stack.peek();
						
						if("[".equals(s)){
							stack.pop();
							stack.push(String.valueOf(val));
						}
						
						if("(".equals(s)){
							flag = false;
							break;
						}
					}
				}
				// c == ')'
				else{
					if("(".equals(s)){
						stack.pop();
						stack.push("2");
					}
					else if("[".equals(s)){
						flag = false;
						break;
					}
					// 숫자
					else{
						int val = Integer.parseInt(stack.pop());
						val *= 2;
						
						if(stack.size() == 0){
							flag = false;
							break;
						}
						
						s = stack.peek();
						
						if("(".equals(s)){
							stack.pop();
							stack.push(String.valueOf(val));
						}
						
						if("[".equals(s)){
							flag = false;
							break;
						}
					}
				}
				
				// 닫힌괄호를 만나고 스택의 현재 위치에서 숫자가 여러개 연속해서 존재하는 지 여부 확인
				ArrayList<Integer> list = new ArrayList<Integer>();
				while(!stack.isEmpty()){
					s = stack.peek();

					// 숫자
					if(!("(".equals(s) || "[".equals(s))){
						
						list.add(Integer.parseInt(s));
						stack.pop();
					}
					else{
						break;
					}
				}
				
				// integerArray 에 있는 값을 전체 **더한** 뒤, stack 에 푸쉬
				int sum = 0;
				
				for(int index = 0; index < list.size(); index++)
					sum += list.get(index);
				
				stack.push(String.valueOf(sum));
				
//				System.out.println(Arrays.toString(stack.toArray()));
			} // else 닫힌 괄호를 만난경우
		}// for
		
		if(flag){
			if(stack.size() >= 2)
				bw.write("0\n");
			else{
				String s = stack.pop();
				if("[".equals(s) || "(".equals(s))
					bw.write("0\n");
				else
					bw.write(s);
			}
		}
		else{
			bw.write("0\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}