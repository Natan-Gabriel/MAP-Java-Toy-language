package model.adt;
import java.util.*;

public interface MyIList<T> {
	//public boolean equals(MyList<T> b);
	public void add(T x);
	public String toString();
	public ArrayList<T> getList();
	public T  get(int x);
	public int  getSize();
}
