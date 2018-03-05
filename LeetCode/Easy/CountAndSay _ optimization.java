package LeetCode;

public class Solution {
	
	public String countAndSay(int n) {
		if(n <= 0)
			return "-1";
		String result = "1";
		
		for(int i = 1; i < n; i++){
			result = build(result);
		}
		
		return result;
	}
	
	public String build(String result){
		StringBuilder builder = new StringBuilder();
		int p = 0;
		
		while(p < result.length()){
			char val = result.charAt(p);
			int count = 0;
			
			// 범위 소속 && 동일한 값
			while(p < result.length() && result.charAt(p) == val){
				p++;		// 인덱스 증가
				count++;	// 카운트 증가
			}
			
			// countAndSay 값으로 설정
			builder.append(String.valueOf(count));
			builder.append(val);
		}
		
		return builder.toString();
	}
	
}
