package model.stmt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import MyException.*;
import model.*;
import model.adt.*;
import model.exp.*;
import model.types.*;
import model.values.*;
import java.util.*;
import java.io.*;

public class OpenRFile implements IStmt{
	 Exp exp;
	 BufferedReader file;
	 
	 public OpenRFile(Exp e) {exp=e;}
	 public String toString() { return exp.toString();}
	 public PrgState execute(PrgState state) throws VarNotDefined, DivByZero,VarIsDefined{
		 MyIStack<IStmt> stk=state.getStk();
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap hp= state.getHeap();
		 Value val = exp.eval(symTbl,hp);
		 Type typId= val.getType();
		 if( !(val.getType()).equals(new StringType()) )
		 	 	throw new VarNotDefined("The var is not a string");
		 StringValue val1=(StringValue)val;
		 MyIDictionary<StringValue, BufferedReader> fTbl=state.getFileTable();
		 if(fTbl.isDefined(val1))
			 throw new VarIsDefined("File is opened");
		 String s=val1.getVal();
		 try {
			 file=new BufferedReader(new FileReader(s));
		 }
		 catch(Exception e){
			 throw new VarNotDefined("File does not exist");
		 }
		 fTbl.add(val1,file);
		 
		 return null;
	 }
	 public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{
			exp.typecheck(typeEnv);
			return typeEnv;
		}
}
