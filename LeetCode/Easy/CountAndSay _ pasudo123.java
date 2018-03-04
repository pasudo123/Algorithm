import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static String countAndSay(int n) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		String dynamic[] = new String[n+1];
		Arrays.fill(dynamic, "");
		dynamic[0] = null;
		dynamic[1] = "1";
		
		// O(n)
		for(int i = 1; i < n; i++){
			String countAndSay = dynamic[i];
			
			char say = 0;
			
			// O(m)
			for(int s = 0; s < countAndSay.length(); s++){
				say = countAndSay.charAt(s);
				
				if(map.containsKey(say))
					map.put(say, map.get(say) + 1);
				else{
					if(s != 0){
						char tempSay = countAndSay.charAt(s-1);
						dynamic[i+1] += map.get(tempSay) + "" + tempSay;
						map.remove(tempSay);
					}
					
					map.put(say, 1);
				}
			}// for
			
			dynamic[i+1] += map.get(say) + "" +  say;
			map.clear();
		}// for
		
		return dynamic[n];
	}
}
