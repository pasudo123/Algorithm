/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode headNode = new ListNode(0);
    	ListNode resultNode = headNode;
    	int currentValue = 0;
    	
    	// 서로 간의 val 값을 비교해간다. ( 오름차순 )
    	while(l1 != null && l2 != null){

    		if(l1.val < l2.val){
    			currentValue = l1.val;
    			l1 = l1.next;
    		}
    		else{
    			currentValue = l2.val;
    			l2 = l2.next;
    		}
    		
    		resultNode.next = new ListNode(currentValue);
    		resultNode = resultNode.next;
    	}
    	
    	if(l1 == null){
    		while(l2 != null){
    			resultNode.next = new ListNode(l2.val);
    			l2 = l2.next;
    			resultNode = resultNode.next;
    		}
    	}
    	
    	if(l2 == null){
    		while(l1 != null){
    			resultNode.next = new ListNode(l1.val);
    			l1 = l1.next;
    			resultNode = resultNode.next;
    		}
    	}
    	
        return headNode.next;
    }
}