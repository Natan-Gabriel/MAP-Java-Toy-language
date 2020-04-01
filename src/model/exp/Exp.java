package model.exp;
import model.values.*;
import model.adt.*;
import model.types.Type;
import MyException.DivByZero;
import MyException.VarNotDefined;
import MyException.*;

public interface Exp {
	//public  String toString();
	Value eval(MyIDictionary<String, Value> symTbl,MyIHeap hp) throws  VarNotDefined,DivByZero; 
	Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp;
}
