package model.stmt;

import java.io.BufferedReader;
import java.io.Serializable;

import MyException.*;
import model.*;
import model.adt.*;
import model.exp.*;
import model.types.*;
import model.values.*;

public class ForkStmt implements IStmt{
	 IStmt s;
	 PrgState prg=null;
	 
	 public ForkStmt(IStmt s1) {s=s1;}
	 public String toString() { return "Fork("+s.toString()+") ";}
	 public PrgState execute(PrgState state) throws VarNotDefined, DivByZero{
		 MyIStack<IStmt> stk=state.getStk();
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap hp= state.getHeap();
		 
		 
	     //MyIDictionary<String, Value> symTable1=new MyDictionary<String, Value>(symTbl);
		 MyIDictionary<String, Value> symTable1;
		try {
			symTable1 = (MyIDictionary<String, Value>) symTbl.clone();
			MyIStack<IStmt> exeStack1=new MyStack<IStmt>();
			prg=new PrgState(exeStack1,symTable1,state.getOut(),state.getFileTable(),hp,state.getBar(),s);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	     
	     //MyIStack<IStmt> exeStack1=new MyStack<IStmt>();
		 //PrgState prg=new PrgState(exeStack1,symTable1,state.getOut(),state.getFileTable(),hp,s);
		 //state.getRepo();
		 return prg;
	 }
	 public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp,CloneNotSupportedException{
		 //Type typexp=s.typecheck(typeEnv);
		 return s.typecheck(typeEnv);
		 
	 }
}
