package model.stmt;
import model.PrgState;
import model.exp.*;
import model.types.Type;
import model.adt.*;
import MyException.*;

public class CompStmt implements IStmt{
	 IStmt first;
	 IStmt snd;
	 public CompStmt(IStmt f,IStmt s) {first=f;snd=s;}
	 public String toString() { return "("+first.toString() + ";" + snd.toString()+")";}
	 public PrgState execute(PrgState state) {
	 
		 MyIStack<IStmt> stk=state.getStk();
		 stk.push(snd);
		 stk.push(first);
		 return null;
	 }
	 @Override
	 public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp, CloneNotSupportedException{
	 //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
	  //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
	  //return typEnv2;
		 return snd.typecheck(first.typecheck(typeEnv));
	 }

}
