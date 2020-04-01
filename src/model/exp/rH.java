package model.exp;

import MyException.*;
import model.adt.*;
import model.types.*;
import model.values.*;

public class rH implements Exp{
	Exp e;
	//....
	public rH(Exp e1) {e=e1;}
	public  String toString() {return e.toString();}
	public Value eval(MyIDictionary<String,Value> tbl,MyIHeap hp) throws VarNotDefined, DivByZero{
		 Value v= e.eval(tbl,hp);
		 if(!(v instanceof RefValue))
			 throw new VarNotDefined("The value is not a RefValue");
		 int a=((RefValue)v).getAddr();
		 if( !(hp.isDefined(a)))
			throw new VarNotDefined("address is not a key in Heap table");
		 return hp.getValue(a);
			 
	}
	public Type getType(MyIDictionary<String,Value> tbl,MyIHeap hp) throws VarNotDefined,DivByZero {
		 return eval(tbl,hp).getType();
	 }
	public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{
		Type typ=e.typecheck(typeEnv);
		if (typ instanceof RefType) {
			RefType reft =(RefType) typ;
			return reft.getInner();
		}else
			throw new MyExp("the rH argument is not a Ref Type");
		}

}
