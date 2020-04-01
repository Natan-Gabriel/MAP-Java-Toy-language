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
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

public class AwaitStmt implements IStmt{
	String var;

	public AwaitStmt(String var1) {var=var1;}
	public String toString() { return "AWAIT "+var;}
	@Override
	public PrgState execute(PrgState state) throws VarNotDefined, DivByZero,VarIsDefined{
		 //lock1 = new Lock();
		
		 MyIStack<IStmt> stk=state.getStk();
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap heap= state.getHeap();
		 MyIBar bar=state.getBar();

		 if(!symTbl.isDefined(var))
			 throw new VarNotDefined("Var is not defined");

		 if(!symTbl.getValue(var).getType().equals(new IntType()))
			 throw new VarNotDefined("Var is not int");

		 IntValue found=(IntValue)symTbl.lookup(var);

		 int found1=found.getVal();
		 if(!bar.isDefined(found1))
			 throw new VarNotDefined("Var is not in BarTable");

		 Pair<Integer,ArrayList<Integer>> ent=bar.getValue(found1);
		 int nl=ent.get2().size();
		 if(ent.get1()>nl)
			 {if((ent.get2()).contains(state.getId()))
				 stk.push(new AwaitStmt(var));
			 else
				 {
				 ent.get2().add(state.getId());
				 stk.push(new AwaitStmt(var));
				 }
			 }
		 else
			 ;

		 return null;
	}
	public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{

		 Type typevar = typeEnv.lookup(var);
	     if (typevar.equals(new IntType()))
	    	 return typeEnv;
	     else
	    	 throw new MyExp("Var doesn't have IntType");
}
}


