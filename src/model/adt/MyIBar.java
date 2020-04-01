package model.adt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.values.*;

public interface MyIBar{
	public String toString();
	int getFirstFree();
	public int add(Pair<Integer,ArrayList<Integer>> y);
	public boolean isDefined(int x);
	public Pair<Integer,ArrayList<Integer>> getValue(int x);
	public void update(int x,Pair<Integer,ArrayList<Integer>> y);
	public Map<Integer,Pair<Integer,ArrayList<Integer>>> getContent();
	public void setContent(Map<Integer,Pair<Integer,ArrayList<Integer>>> m);
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