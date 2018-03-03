import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        int resultNum[] = new int[2];
        int index = 0;
        
        for(int i = 0; i < end; i++){
          for(int j = i + 1; j <= end; j++){
            if(target == nums[i] + nums[j]){
              resultNum[0] = i;
              resultNum[1] = j;
              
              return resultNum;            
            }
          }
        }
        
        return resultNum;
    }
}