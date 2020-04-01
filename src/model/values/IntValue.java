package model.values;
import model.types.*;

public class IntValue implements Value{
	int val;
	public IntValue(int v) {val=v;}
	public IntValue() {val=0;}
	public boolean equals(Object another){
		 if (another instanceof IntValue)
			 return true;
		 else
			 return false;
	 }
	public Type getType() {return new IntType();}
	public int getVal() {return val;}
	public void setVal(Value a) {val=((IntValue)a).getVal();}
	public String toString() {return Integer.toString(val);}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		IntValue cloned = (IntValue)super.clone();
	    //cloned.setVal((Department)cloned.getDepartment().clone());   
	    return cloned;
	}
}
