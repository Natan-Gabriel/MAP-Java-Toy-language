package model.stmt;

import java.util.ArrayList;

import MyException.DivByZero;
import MyException.MyExp;
import MyException.VarIsDefined;
import MyException.VarNotDefined;
import model.PrgState;
import model.adt.MyIBar;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.adt.MyIStack;
import model.adt.Pair;
import model.exp.Exp;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

public class NewBarrierStmt implements IStmt{
	String var;
	Exp exp;
	//private final Lock lock1=new Object();
	public NewBarrierStmt(String s,Exp e) {var=s;exp=e;}
	public String toString() { return "NEW BARRIER "+var+"="+exp.toString();}
	@Override
	public PrgState execute(PrgState state) throws VarNotDefined, DivByZero,VarIsDefined{
		 //lock1 = new Lock();
		
		 MyIStack<IStmt> stk=state.getStk();
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap heap= state.getHeap();
		 MyIBar bar=state.getBar();
		 
		 Value number1=exp.eval(symTbl,heap);
		 if(!number1.getType().equals(new IntType()))
			 throw new VarNotDefined("the exp hasn't int type");
		 
		 int nr=((IntValue)number1).getVal();

		 int res;
		 
		 ArrayList<Integer> l=new ArrayList<Integer>();
		 Pair<Integer,ArrayList<Integer>> t=new Pair<Integer,ArrayList<Integer>>(nr,l);
		 synchronized(this){
			 res=bar.add(t);
		 }
		 
		 if(symTbl.isDefined(var) && symTbl.lookup(var).getType().equals(new IntType()))
			 symTbl.update(var,new IntValue(res));
		 else
			 throw new VarNotDefined("Var is not defined or has not IntType");
		 
		 return null;
	 }
	public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{
         Type typevar = typeEnv.lookup(var);
		 Type typexp = exp.typecheck(typeEnv);
	     if (typevar.equals(typexp) &&  typevar.equals(new IntType()))
	    	 return typeEnv;
	     else
	    	 throw new MyExp("Var or exp doesn't have IntType");
	}
}