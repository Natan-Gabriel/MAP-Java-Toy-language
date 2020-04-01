package model.values;
import model.types.*;

public interface Value extends Cloneable {
	public Object clone() throws CloneNotSupportedException ;
	public Type getType();
	public String toString();
	public void setVal(Value v);
}
