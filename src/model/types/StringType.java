package model.types;
import model.values.*;

public class StringType implements Type{
	public StringType() {};
	public Value defaultValue() {return new StringValue();}
	public boolean equals(Object another){
		 if (another instanceof StringType)
			 return true;
		 else
			 return false;
	 }
	 public String toString() { return "string";}
}
