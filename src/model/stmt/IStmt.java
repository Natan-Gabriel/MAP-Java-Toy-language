package model.stmt;
import model.PrgState;
import model.adt.*;
import model.types.*;
import MyException.*;

public interface IStmt {
	public String toString();
	public PrgState execute(PrgState state) throws VarNotDefined,DivByZero,VarIsDefined;
	public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp,CloneNotSupportedException;

}
