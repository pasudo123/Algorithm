package LeetCode;

import java.util.Hashtable;

public class Solution {
	public int[] twoSum(int[] nums, int target) {
		/**ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * 
		 * 		 ONE - PASS Using HashTable
		 * 
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ**/
		
		Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
		
		// O(n)
		for(int index = 0; index < nums.length; index++)
			hashTable.put(nums[index], index);
			
		// O(n)
		for(int index = 0; index < nums.length; index++){
			int anotherValue = target-nums[index];
			
			if(hashTable.containsKey(anotherValue) && hashTable.get(anotherValue) != index){
				int i = index;
				int j = hashTable.get(anotherValue);
			
				return new int[]{i, j};
			}
		}
		
		return null;
	}
}
