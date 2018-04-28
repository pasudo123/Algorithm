import java.util.Arrays;

class Queue{
    Object[]array;
    int back;
    int front;
    int elementCount;
    int capacity;
    
    public Queue(){
        this(10);
    }
    
    public Queue(int size){
        this.array = new Object[size];
        
        for(int i = 0; i < array.length; i++)
            array[i] = new Object();
        
        this.capacity = size;
        this.back = 0;
        this.front = 0;
    }
    
    public void push(Object value){
        
        if(back >= capacity){
            // resize
            reSize(capacity << 1);
        }
        
        array[back++] = value;
        elementCount += 1;
    }
    
    public Object pop(){
        if(elementCount <= 0){
            // null
            return -1;
        }
        
        elementCount -= 1;
        Object value = array[front++];
        array[front-1] = null;
        return value;
    }
    
    public Object size(){
        return elementCount;
    }
    
    public Object empty(){
        // 개수가 없는 경우
        if((int)size() <= 0)
            return 1;
        
        return 0;
    }
    
    // 첫번째 요소 값 획득
    public Object front(){
        if((int)empty() == 0)
            return array[(front)];
        
        return -1;
    }
    
    // 마지막 요소 값 획
    public Object back(){
        if((int)empty() == 0)
            return array[(back-1)];
        
        return -1;
    }
    
    // 사이즈 증가
    public void reSize(int newCapacity){
        array = Arrays.copyOf(array, newCapacity);
        capacity = newCapacity;
    }
}