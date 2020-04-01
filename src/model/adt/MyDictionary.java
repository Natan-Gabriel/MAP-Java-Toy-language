package model.adt;
import java.util.*;

import model.values.*;

public class MyDictionary<K,V> implements MyIDictionary<K,V>,Cloneable{
	
	HashMap<K,V> dict;
	
	@Override
	public synchronized Object clone() throws CloneNotSupportedException {
        //return super.clone();
		//MyDictionary<K,V> cloned = (MyDictionary<K,V>)super.clone();
        //cloned.setVal((Value)cloned.getValue().clone()); 
		HashMap<K,V> copy=new HashMap<K,V>();
		for (Map.Entry<K, V> entry : dict.entrySet())
	    {
	        copy.put(entry.getKey(),entry.getValue());
	    }
        return new MyDictionary(copy);
	}
	public boolean equals(HashMap<K,V> a,HashMap<K,V> b) {return a.equals(b);}
	public MyDictionary(HashMap<K,V> d){dict=d;}
	public MyDictionary(MyIDictionary<K,V> d){dict=d.getContent();}
	public MyDictionary(){dict=new HashMap<K,V>();}
	public String toString() {return dict.toString();}
	public synchronized void add(K x,V y) {dict.put(x,y);}
	public synchronized V lookup(K x) {return dict.get(x);}
	public synchronized V getValue(K x){return dict.get(x);}
	public synchronized boolean isDefined(K x) {return dict.containsKey(x);}
	public synchronized void update(K x,V y) {dict.put(x,y);}
	public synchronized void remove(K x) {dict.remove(x);}
	public synchronized HashMap<K,V> getD(){return dict;}
	public synchronized Set<K>  keySet() {return dict.keySet();}
	public synchronized int  getSize() {return dict.size();}
	public synchronized HashMap<K,V> getContent(){return dict;}
}
