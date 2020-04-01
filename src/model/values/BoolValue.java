package model.values;
import model.types.*;

public class BoolValue implements Value{
	boolean val;
	public BoolValue(boolean v){val=v;}
	public BoolValue() {val=false;}
	public boolean equals(Object another){
		 if (another instanceof BoolValue)
			 return true;
		 else
			 return false;
	 }
	public boolean getVal() {return val;}
	public void setVal(Value a) {val=((BoolValue)a).getVal();}
	public String toString() {return Boolean.toString(val);}
	public Type getType() { return new BoolType();}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		BoolValue cloned = (BoolValue)super.clone();
	    //cloned.setVal((Department)cloned.getDepartment().clone());   
	    return cloned;
	}
}
