package model.stmt;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.adt.MyIStack;
import model.exp.*;
import model.types.*;
import model.values.*;
import MyException.*;

public class IfStmt implements IStmt{
	 Exp exp;
	 IStmt thenS;
	 IStmt elseS;
	 
	 public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}
	 public String toString(){ return "IF("+ exp.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+")";}
	 public PrgState execute(PrgState state) throws VarNotDefined, DivByZero{
		 MyIStack<IStmt> stk=state.getStk();
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap hp= state.getHeap();
		 Value val = exp.eval(symTbl,hp);
		 if(val.getType().equals(new BoolType())){
			 BoolValue v=(BoolValue)val;
			 if( v.getVal()==true )
		 	 {
				 stk.push(thenS);
		 	 }
			 else
			 {
				 stk.push(elseS);
			 }
		 }
		 else throw new VarNotDefined("type of expression"+exp.toString()+" is not a boolean");

		 return null;
	 }
	 public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp,CloneNotSupportedException{
		 Type typexp=exp.typecheck(typeEnv);
		 if (typexp.equals(new BoolType())) {
			 //thenS.typecheck(clone(typeEnv));
			 //elseS.typecheck(clone(typeEnv));
			 thenS.typecheck((MyIDictionary<String, Type>)typeEnv.clone());
			 elseS.typecheck((MyIDictionary<String, Type>)typeEnv.clone());
			 return typeEnv;
		 }
	     else
	    	 throw new MyExp("The condition of IF has not the type bool");
	 }
}

