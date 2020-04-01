package model.stmt;

import MyException.*;
import model.PrgState;
import model.adt.*;
import model.exp.*;
import model.types.*;
import model.values.*;
import model.types.*;

public class New implements IStmt{
	String var_name;
	Exp exp;
	public New(String s,Exp e) {var_name=s;exp=e;}
	public String toString() { return var_name+"="+exp.toString();}
	@Override
	public PrgState execute(PrgState state) throws VarNotDefined, DivByZero,VarIsDefined{
		 MyIStack<IStmt> stk=state.getStk();
		 //System.out.println("hehe");
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap heap= state.getHeap();
		 if(!symTbl.isDefined(var_name))
			 throw new VarNotDefined("Var_name is not defined");
		 Type typId= symTbl.getValue(var_name).getType();
		 //System.out.println("hehe");
		// if( !(typId).equals(new RefType(new IntType())) && !(typId).equals(new RefType(new BoolType())) 
		//		 && !(typId).equals(new RefType(new StringType())) && !(typId).equals(new RefType(new RefType(new IntType()))))
		 	// 	throw new VarNotDefined("The var has not RefType");
		 if( !(typId instanceof RefType))
			 throw new VarNotDefined("The var has not RefType");
		// System.out.println("hehe");
		 Type typ= ((RefValue)symTbl.getValue(var_name)).getLocationType();
		 Value val = exp.eval(symTbl,heap);
		// System.out.println("hehe");
		 System.out.println(typ);
		 System.out.println(val.getType());
		 if( !typ.equals(val.getType()) )
		 	 	throw new VarNotDefined("The var_name and exp doesn't have same type");
	//	 System.out.println("hehe");
		 Value newVal=heap.add(val);
		 symTbl.update(var_name,newVal);
	//	 System.out.println("hehe");
		 return null;
	 }
	public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{
		Type typevar = typeEnv.lookup(var_name);
		Type typexp = exp.typecheck(typeEnv);
		if (typevar.equals(new RefType(typexp)))
			return typeEnv;
		else
			throw new MyExp("NEW stmt: right hand side and left hand side have different types ");
	}


}
