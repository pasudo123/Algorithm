import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[]args) throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
//        OutputStreamWriter osw = new OutputStreamWriter(System.out);
//        BufferedWriter bw = new BufferedWriter(osw);
        
        int N = Integer.parseInt(br.readLine());
        
        Tree tree = new Tree();
        
        for(int i = 0; i < N; i++){
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	Character root = st.nextToken().charAt(0);
        	Character left = st.nextToken().charAt(0);
        	left = (left == '.')? null:left;
        	Character right = st.nextToken().charAt(0);
        	right = (right == '.')? null:right;
        		
        	tree.insert(root, left, right);
        }
        
        tree.preOrder(tree.node);
        System.out.println();
        tree.inOrder(tree.node);
        System.out.println();
        tree.postOrder(tree.node);
        
//        bw.flush();
//        bw.close();
        br.close();
    }
    
    static class Tree{
    	Node node;
    	
    	// 삽입
    	public void insert(Object rt, Object l, Object r){
    		if(node == null){
    			node = new Node(rt);
    			node.leftNode = new Node(l);
    			node.rightNode = new Node(r);
    		}
    		else{
    			searchInsert(node, rt, l, r);
    		}
    	}
    	
    	// 탐색 후 삽입 (private)
    	private void searchInsert(Node node, Object rt, Object l, Object r){
    		if(node == null)
    			return;
    		
    		if(node.value == rt){
    			node.leftNode = new Node(l);
    			node.rightNode = new Node(r);
    		}
    		else{
    			searchInsert(node.leftNode, rt, l, r);
    			searchInsert(node.rightNode, rt, l, r);
    		}
    	}
    	
		// 전위 순회
		public void preOrder(Node node){
			if(node.value != null)System.out.print(node.value);
			if(node.leftNode != null) preOrder(node.leftNode);
			if(node.rightNode != null) preOrder(node.rightNode);
		}
		
		// 중위 순회
		public void inOrder(Node node){
			if(node.leftNode != null) inOrder(node.leftNode);
			if(node.value != null)System.out.print(node.value);
			if(node.rightNode != null) inOrder(node.rightNode);
		}
		
		// 후위 순회
		public void postOrder(Node node){
			if(node.leftNode != null) postOrder(node.leftNode);
			if(node.rightNode != null) postOrder(node.rightNode);
			if(node.value != null)System.out.print(node.value);
		}
    }
    
	static class Node{
		Object value;
		Node leftNode;
		Node rightNode;
		
		public Node(){
			this.leftNode = null;
			this.rightNode = null;
		}
		
		public Node(Object value){
			this();
			this.value = value;
		}
	}
}