package model.adt;

import java.util.HashMap;
import java.util.Set;

public interface MyIDictionary<K,V> extends Cloneable {
	public Object clone() throws CloneNotSupportedException ;
	public String toString();//
	public boolean equals(HashMap<K,V> a,HashMap<K,V> b);
	public void add(K x,V y);//
	public void update(K x,V y);//
	public V lookup(K x);//
	public V getValue(K x);//
	public boolean isDefined(K x);//
	public HashMap<K,V> getD();
	public void remove(K x);//
	public Set<K>  keySet();
	public int  getSize();
	public HashMap<K,V> getContent();
}
