package model.exp;
import model.values.*;
import model.adt.*;
import model.types.*;


public class ValueExp implements Exp{
	Value val;
	//....
	public ValueExp(Value v) {val=v;}
	//public ValueExp(IntValue v) {
	//}
	public  String toString() {return val.toString();}
	public Value eval(MyIDictionary<String,Value> tbl,MyIHeap hp) {return val;}
	public Type getType(MyIDictionary<String,Value> tbl,MyIHeap hp) {
		 return val.getType();}
	public Type typecheck(MyIDictionary<String,Type> typeEnv) {
		return val.getType();
		 } 
}
