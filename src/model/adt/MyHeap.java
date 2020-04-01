package model.adt;

import java.util.*;

import model.values.*;

public class MyHeap implements MyIHeap{
	HashMap<Integer,Value> heap;
	int firstFree;
	public MyHeap(HashMap<Integer,Value> d){heap=d;firstFree=0;}
	public MyHeap(){heap=new HashMap<Integer,Value>();firstFree=0;}
	public String toString() {return heap.toString();}
	public synchronized int getFirstFree() {return firstFree;}
	public synchronized Value add(Value y) {firstFree=firstFree+1;heap.put(firstFree,y);
								System.out.println(heap);
								return new RefValue(firstFree,y.getType());}
	public synchronized boolean isDefined(int x) {return heap.containsKey(x);}
	public synchronized Value getValue(int x){return heap.get(x);}
	public synchronized void update(int x,Value y) {heap.put(x,y);}
	public synchronized Map<Integer,Value> getContent(){return heap;}
	public synchronized void setContent(Map<Integer,Value> m) {heap=(HashMap<Integer, Value>) m;}
	public synchronized Set<Integer>  keySet() {return heap.keySet();}
	//public HashMap<Integer,Value> getContent(){return heap.getContent();}
	/*public MyDictionary(){dict=new HashMap<K,V>();}
	public String toString() {return dict.toString();}
	public void add(K x,V y) {dict.put(x,y);}
	public V lookup(K x) {return dict.get(x);}
	public V getValue(K x){return dict.get(x);}
	public boolean isDefined(K x) {return dict.containsKey(x);}
	public void update(K x,V y) {dict.put(x,y);}
	public void remove(K x) {dict.remove(x);}
	public HashMap<K,V> getD(){return dict;}
	public Set<K>  keySet() {return dict.keySet();}
	public int  getSize() {return dict.size();}*/
}
