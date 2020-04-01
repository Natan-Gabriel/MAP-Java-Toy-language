package model.types;

import model.values.*;

public class BoolType implements Type{
	public Value defaultValue() {return new BoolValue();}
	public boolean equals(Object another){
		 if (another instanceof BoolType)
			 return true;
		 else
			 return false;
	 }
	 public String toString() { return "bool";}
}
