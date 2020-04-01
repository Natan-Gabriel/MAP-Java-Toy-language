package model.adt;

import java.util.*;


public class MyStack<T> implements MyIStack<T>{
	 Stack<T> stack;
	 public MyStack(Stack<T> s){stack=s;}
	 //public MyStack(MyStack<T> s){stack=s.getStack();}
	 public MyStack(){stack=new Stack<T>();}
	 public Stack<T> getStack(){return stack;}
	 public String toString() {return stack.toString();}
	 public T pop() {return stack.pop();}
	 public void push(T v) {stack.push(v);}
	 public boolean isEmpty() {return stack.isEmpty();}
	 public int getSize() {return stack.size();}
	 public T get(int i) {return stack.get(i);}
}
