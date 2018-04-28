class Deque{
    private Node front;
    private Node back;
    private int elementCount;
    
    Deque(){
        this.front = null;
        this.back = null;
        this.elementCount = 0;
    }
    
    class Node{
        Node leftNode;
        Node rightNode;
        Integer value;
        
        Node(){
            this.leftNode = null;
            this.rightNode = null;
        }
        
        Node(Integer value){
            this();
            this.value = value;
        }
    }// Node
    
    void initPush(int value){
        this.front = new Node(value);
        this.back = this.front;
        
        this.front.rightNode = null;
        this.back.leftNode = null;
        
        this.elementCount += 1;
    }
    
    void pushFront(int value){
        if(elementCount == 0){
            initPush(value);
            return;
        }
        
        Node node = new Node(value);
        node.leftNode = front;
        node.rightNode = null;
        front.rightNode = node;
        front = node;
        
        elementCount += 1;
    }
    
    void pushBack(int value){
        if(elementCount == 0){
            initPush(value);
            return;
        }
        
        Node node = new Node(value);
        node.rightNode = back;
        node.leftNode = null;
        back.leftNode = node;
        back = node;
        
        elementCount += 1;
    }
    
    int popFront(){
        if(elementCount <= 0){
            return -1;
        }
        
        int value = front.value;
        front = front.leftNode;
        elementCount--;
        return value;
    }
    
    int popBack(){
        if(elementCount <= 0){
            return -1;
        }
        
        int value = back.value;
        back = back.rightNode;
        elementCount--;
        return value;
    }
    
    int size(){
        return elementCount;
    }
    
    int empty(){
        if(elementCount <= 0)
            return 1;
            
        return 0;
    }
    
    int front(){
        if(elementCount <= 0)
            return -1;
        
        return front.value;
    }
    
    int back(){
        if(elementCount <= 0)
            return -1;
        
        return back.value;
    }
}// Deque