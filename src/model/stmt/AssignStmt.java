package model.stmt;
import model.PrgState;
import model.adt.*;
import model.exp.*;
import model.values.*;
import model.types.*;
import MyException.*;

public class AssignStmt implements IStmt{
	 String id;
	 Exp exp;
	 
	 public AssignStmt(String i,Exp e) {id=i;exp=e;}
	 public String toString() { return id+"="+ exp.toString();}
	 public PrgState execute(PrgState state) throws VarNotDefined, DivByZero{
		 MyIStack<IStmt> stk=state.getStk();
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap hp= state.getHeap();
		 if(symTbl.isDefined(id)){
			 Value val = exp.eval(symTbl,hp);
			 Type typId= (symTbl.lookup(id)).getType();
			 if( (val.getType()).equals(typId) )
		 	 	symTbl.update(id, val);
		 	 else throw new VarNotDefined("declared type of variable"+id+" and type of the assigned expression do not match");}
		 else throw new VarNotDefined("the used variable" +id + " was not declared before");
		 return null;
	 }
	 @Override
	 public  MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{
		 Type typevar = typeEnv.lookup(id);
		 Type typexp = exp.typecheck(typeEnv);
	     if (typevar.equals(typexp))
	    	 return typeEnv;
	     else
	    	 throw new MyExp("Assignment: right hand side and left hand side have different types ");
	 }

}


