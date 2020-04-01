package model.adt;

import java.util.Stack;

public interface MyIStack<T> {
	public String toString();
	public T pop();
	public void push(T v);
	public boolean isEmpty();
	public Stack<T> getStack();
	public int getSize();
	public T get(int i);

}
