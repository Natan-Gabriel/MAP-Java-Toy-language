package model.types;
import model.values.*;

public class IntType implements Type{
	public IntType() {};
	public Value defaultValue() {return new IntValue();}
	public boolean equals(Object another){
		 if (another instanceof IntType)
			 return true;
		 else
			 return false;
	 }
	 public String toString() { return "int";}
}
