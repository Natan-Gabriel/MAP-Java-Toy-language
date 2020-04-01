package model.adt;
import java.util.*;

public class MyList<T> implements MyIList<T>{
	ArrayList<T> list;
	//public boolean equals(MyList<T> b) {
		
	//	ArrayList<T> list1 = new ArrayList<T>(list); 
	//	ArrayList<T> b1 = new ArrayList<T>(b.getList()); 
	//    return list1.equals(b1);}
	/*@Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof MyList))
            return false;
        MyIList fc = (MyIList) other;
        return list.equals(fc.getList());
    }*/
	public MyList(ArrayList<T> l){list=l;}
	public MyList(){list=new ArrayList<T>();}
	public synchronized ArrayList<T> getList(){return list;}
	public String toString() {return list.toString();}
	public synchronized void add(T x) {list.add(x);}
	public synchronized T  get(int x) {return list.get(x);}
	public synchronized int  getSize() {return list.size();}
}
