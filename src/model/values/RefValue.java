package model.values;
import model.types.*;

public class RefValue implements Value{
	 int address;
	 Type locationType;
	 public RefValue(int a,Type t) {address=a;locationType=t;}
	 public RefValue() {address=1;locationType=new IntType();}
	 public String toString() {return "("+Integer.toString(address)+","+locationType.toString()+")";}
	 public int getAddr() {return address;}
	 public void setVal(Value a) {address=((RefValue)a).getAddr();}
	 public Type getType() { return new RefType(locationType);}
	 public Type getLocationType() { return locationType;}
	 
	 @Override
		public Object clone() throws CloneNotSupportedException {
		 RefValue cloned = (RefValue)super.clone();
		    //cloned.setVal((Department)cloned.getDepartment().clone());   
		   return cloned;
		}
	}

