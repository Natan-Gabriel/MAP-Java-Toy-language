package model.exp;
import model.values.*;
import model.types.*;
import model.adt.*;

public class VarExp implements Exp{
	String id;
	//....
	public VarExp(String s) {id=s;}
	public String toString() {return id;}
	public Value eval(MyIDictionary<String,Value> tbl,MyIHeap hp)  {return tbl.lookup(id);}
	public Type getType(MyIDictionary<String,Value> tbl,MyIHeap hp) {
			return eval(tbl,hp).getType();
	}
	public Type typecheck(MyIDictionary<String,Type> typeEnv) {
		return typeEnv.lookup(id);
		 } 
}
 