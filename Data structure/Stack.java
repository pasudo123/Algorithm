import java.util.Arrays;

class Stack{
    private int[]array = new int[10];
    private int elementCount;
    private int top;
    
    public Stack(){
        this.elementCount = 0;
        this.top = 0;
    }
    
    public void push(int value){
        if(top >= array.length){
            reSize(top << 1);
        }
        
        elementCount++;
        array[top++] = value;
    }
    
    public Integer pop(){
        if(!(top <= 0)){
            elementCount--;
            return array[--top];
        }
        
        return -1;
    }
    
    public Integer size(){
        return elementCount;
    }
    
    // 비었는지 여부 출력, 
    public Integer empty(){
        if(elementCount == 0)
            return 1;
        
        return 0;
    }
    
    // 스택의 가장 맨 위의 값 출력, 없으면 -1
    public Integer top(){
        if(elementCount == 0)
            return -1;
        
        return array[(top-1)];
    }
    
    public void reSize(int newSize){
        array = Arrays.copyOf(array, newSize);
    }
}


