package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

class HashMap<K, V>{
	
	private int size = 0;					// 해쉬맵 사이즈
	private LinkedList<hmnodes> buckets[];	// 주소를 담고 있는 리스트 배열
	
	// HashMap Nodes
	class hmnodes{
		K key;
		V value;
	}
	
	public HashMap(){
		this.buckets = new LinkedList[4];
		for(int i = 0; i < 4; i++)
			buckets[i] = new LinkedList<>();
	}
	
	public void put(K key, V value) throws Exception{
		int bi = bucketIndex(key);		// 인덱스를 찾아 새로운 키가 해당 인덱스의 링크리스트에 삽입
		int foundAt = find(bi, key);	// key 가 존재하는지 여부 확인
		
		// key 가 존재하지 않는 경우
		if(foundAt == -1){
			// 존재하지 않기 때문에, 새로운 HashMap 노드를 만들고 삽입한다.
			hmnodes temp = new hmnodes();
			temp.key = key;
			temp.value = value;
			buckets[bi].addLast(temp);
			this.size++;
		}
		// key 가 존재하는 경우
		else{
			// 존재하기 때문에, value 값을 변경한다.
			buckets[bi].get(foundAt).value = value;
		}
		
		double lambda = (this.size*1.0)/this.buckets.length;
		
		if(lambda > 2.0){
			// rehashing 함수는  람다의 크기가 2.0 을 초과해서 버킷을 증가시키기 위한 함수이다.
			// doubling : buckets 의 사이즈가 정해져 있기 때문에 배열의 크기를 두 배씩 늘리기 위함. 
			rehash();
		}
		
		return;
	}
	
	public V get(K key) throws Exception{
		int bi = bucketIndex(key);
		int foundAt = find(bi, key);
		
		if(foundAt == -1)
			return null;
		else
			return buckets[bi].get(foundAt).value;
	}
	
	public V remove(K key) throws Exception{
		int bi = bucketIndex(key);
		int foundAt = find(bi, key);
		
		if(foundAt == -1)
			return null;
		else{
			this.size--;
			return buckets[bi].get(foundAt).value;
		}
	}
	
	public boolean containsKey(K key) throws Exception{
		int bi = bucketIndex(key);
		int foundAt = find(bi, key);
		
		if(foundAt == -1)
			return false;
		else
			return true;
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean isempty(){
		return this.size==0;
	}
	
	public ArrayList<K> keyset() throws Exception{
		ArrayList<K> arr = new ArrayList<>();
		
		for(int i = 0; i < buckets.length; i++){
			for(int j = 0; j < buckets[i].size(); j++){
				arr.add(buckets[i].get(j).key);
			}
		}
		
		return arr;
	}
	
	public ArrayList<V> valueset() throws Exception{
		ArrayList<V> arr = new ArrayList<>();
		
		for(int i = 0; i < buckets.length; i++){
			for(int j = 0; j < buckets[i].size(); j++){
				arr.add(buckets[i].get(j).value);
			}
		}
		
		return arr;
	}
	
	public void display() throws Exception{
		for(int i = 0; i < buckets.length; i++){
			System.out.print("Bucket : " + i + " ");
			
			for(int j = 0; j < buckets[i].size(); j++){
				hmnodes temp = buckets[i].get(j);
				System.out.print("[" + temp.key + " -> " + temp.value + "]");
			}
			
			System.out.println();
		}
	}
	
	public int find(int bi, K key) throws Exception{
		for(int i = 0; i < buckets[bi].size(); i++){
			if(key.equals(buckets[bi].get(i).key))
				return i;
		}
		
		return -1;
	}
	
	public int bucketIndex(K key) throws Exception{
		int bi = key.hashCode();
//		System.out.println(key + " : " + bi);
		// 메모리 절약을 위해 저장/조회할 해시 버킷을 계산하는 방식.
		// 객체에 대한 해시코드의 나머지 값을 해시 버킷 인덱스로 이용
		return Math.abs(bi % buckets.length);
	}
	
	private void rehash() throws Exception{
		LinkedList<hmnodes> ob[] = buckets; 		// 복사
		buckets = new LinkedList[ob.length * 2];	// 두배로 버킷의 길이를 증가시킨다.
		
		for(int i = 0; i < ob.length * 2; i++)
			buckets[i] = new LinkedList<>();
		
		// 사이즈 초기화
		size = 0;
		
		for(int i = 0; i < ob.length; i++){
			for(int j = 0; j < ob[i].size(); j++){
				put(ob[i].get(j).key, ob[i].get(j).value);	// put() 메소드 수행
			}
		}
	}
}

public class Hash {
	public static void main(String[]args) throws Exception{
		/*
		 * HashMap & HashTable
		 * Key 에 대한 '해쉬값' 을 사용하여 값을 조회하고 저장하는 기능을 하며
		 * Key - Value 쌍의 개수에 따라 동적으로 크기가 증가하는 Associate-Array(연관배열) 라고 할 수 있다.
		 * 
		 * Java 의 HashMap 방식은 Separate Chaining 이다.
		 * 
		 * reference : http://d2.naver.com/helloworld/831311
		 * reference : https://github.com/TheAlgorithms/Java/blob/master/Data%20Structures/HashMap/HashMap.java
		 * */
		HashMap hashMap = new HashMap();
		hashMap.put("호랑이", 10);
		hashMap.put("캥거루", 20);
		hashMap.put("토마토", 30);
		hashMap.put("고구마", 40);
		hashMap.put("바나나", 50);
		hashMap.put("양배추", 60);
		hashMap.put("옥수수", 70);
		hashMap.put("감자깡", 80);
		hashMap.put("갈비탕", 90);
		System.out.println();
		hashMap.display();
		
//		System.out.println();
//		System.out.println("호랑이".hashCode());
//		System.out.println("캥거루".hashCode());
//		System.out.println("토마토".hashCode());
//		System.out.println("고구마".hashCode());
//		System.out.println("바나나".hashCode());
	}
}
