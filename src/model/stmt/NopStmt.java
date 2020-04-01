package model.stmt;

import MyException.*;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.types.Type;

public class NopStmt implements IStmt{
	public String toString() {return "nop";}
	public PrgState execute(PrgState state) {
		MyIStack<IStmt> stk=state.getStk();
		stk.pop();
		return null;
		}
	public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp,CloneNotSupportedException{
		 return typeEnv;
		 
	 }
	 }
