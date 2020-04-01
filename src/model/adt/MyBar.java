package model.adt;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.values.*;

public class MyBar implements MyIBar{
	HashMap<Integer,Pair<Integer,ArrayList<Integer>>> heap;
	int firstFree;
	public MyBar(HashMap<Integer,Pair<Integer,ArrayList<Integer>>> d){heap=d;firstFree=0;}
	public MyBar(){heap=new HashMap<Integer,Pair<Integer,ArrayList<Integer>>>();firstFree=0;}
	public String toString() {return heap.toString();}
	public synchronized int getFirstFree() {return firstFree;}
	public synchronized int add(Pair<Integer,ArrayList<Integer>> y) {firstFree=firstFree+1;heap.put(firstFree,y);
								System.out.println(heap);
								return firstFree;}
	public synchronized boolean isDefined(int x) {return heap.containsKey(x);}
	public synchronized Pair<Integer,ArrayList<Integer>> getValue(int x){return heap.get(x);}
	public synchronized void update(int x,Pair<Integer,ArrayList<Integer>> y) {heap.put(x,y);}
	public synchronized Map<Integer,Pair<Integer,ArrayList<Integer>>> getContent(){return heap;}
	public synchronized void setContent(Map<Integer,Pair<Integer,ArrayList<Integer>>> m) {heap=(HashMap<Integer,Pair<Integer,ArrayList<Integer>>>) m;}
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
