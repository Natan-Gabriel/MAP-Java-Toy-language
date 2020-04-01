package model.adt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.values.*;

public interface MyIHeap{
	public String toString();
	int getFirstFree();
	public Value add(Value y);
	public boolean isDefined(int x);
	public Value getValue(int x);
	public void update(int x,Value y);
	public Map<Integer,Value> getContent();
	public void setContent(Map<Integer,Value> m);
	public Set<Integer>  keySet();
	/*public boolean equals(HashMap<K,V> a,HashMap<K,V> b);
	public void add(K x,V y);//
	public void update(K x,V y);//
	public V lookup(K x);//
	public V getValue(K x);//
	public boolean isDefined(K x);//
	public HashMap<K,V> getD();
	public void remove(K x);//
	public Set<K>  keySet();
	public int  getSize();*/
}
