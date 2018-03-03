import java.util.Hashtable;

public class Solution {
	public int[] twoSum(int[] nums, int target) {
		/**ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * 
		 * 		 ONE - PASS Using HashTable
		 * 
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ**/
		
		Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
	
		// O(n)
		for(int index = 0; index < nums.length; index++){
			int anotherValue = target-nums[index];
			
			if(hashTable.containsKey(anotherValue)){
				int i = hashTable.get(anotherValue);
				int j = index;
			
				return new int[]{i, j};
			}
			
			hashTable.put(nums[index], index);
		}
		
		return null;
	}
}
