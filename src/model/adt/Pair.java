package model.adt;


public class Pair<X,Y> { 
	  public X x; //public final X x; 
	  public Y y; 
	  
	  public Pair(X x, Y y) { 
		this.x = x; 
	    this.y = y;  
	  } 
	  public String toString() {return "  "+x.toString()+"  "+y.toString();}
	  public X get1() {return x;}
	  public Y get2() {return y;}
	} 