package model.stmt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import MyException.*;
import model.*;
import model.adt.*;
import model.exp.Exp;
import model.types.* ;
import model.values.*;

public class readFile implements IStmt{
	 Exp exp;
	 BufferedReader file;
	 String var_name;
	 String line="";
	 
	 public readFile(Exp e,String v) {exp=e;var_name=v;}
	 public String toString() { return exp.toString();}
	 public PrgState execute(PrgState state) throws VarNotDefined, DivByZero,VarIsDefined{
		 MyIStack<IStmt> stk=state.getStk();
		 MyIDictionary<String,Value> symTbl= state.getSymTable();
		 MyIHeap hp= state.getHeap();
		 
		 if(!symTbl.isDefined(var_name))
			 throw new VarNotDefined("var_name is not defined");
		 if(!symTbl.lookup(var_name).getType().equals(new IntType()))
			 throw new VarNotDefined("var_name is not int");
		 
		 Value val = exp.eval(symTbl,hp);
		 if( !(val.getType()).equals(new StringType()) )
		 	 	throw new VarNotDefined("The var is not a string");
		 StringValue val1=(StringValue)val;
		 MyIDictionary<StringValue, BufferedReader> fTbl=state.getFileTable();
		 if(!fTbl.isDefined(val1))
			 throw new VarNotDefined("File does not exist");
			 
		 file=fTbl.getValue(val1);
		 try {
		 line=file.readLine();
		 System.out.println(line);
		 }
		 catch(Exception e){
			 return state;
		 }
		 int comp;
		 if(line==null)
			 comp=0;
		 else
			 comp=Integer.parseInt(line);
		 symTbl.update(var_name,new IntValue(comp));

		 
		 return null;
	 }
	 public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{
			Type typexp = exp.typecheck(typeEnv);
			if (var_name instanceof String)
				return typeEnv;
			else
				throw new MyExp("var_name is not instanceof String");
		}
}
