package model;
import model.stmt.*;
import model.adt.*;
import model.values.*;
import model.types.*;
import MyException.*;
import java.io.*;
import java.util.ArrayList;

public class PrgState{
	 MyIStack<IStmt> exeStack;
	 MyIDictionary<String, Value> symTable;
	 MyIDictionary<StringValue,BufferedReader> FileTable;
	 MyIList<Value> out;
	 MyIHeap heap;
	 MyIBar bar;
	 IStmt originalProgram; //optional field, but good to have
	 private int id;
	 private static int count = 0;


	 public synchronized int getId() {
	    return id;
	 }
	 @Override
	 public String toString() {return "Execution stack of "+getId()+" is "+exeStack.toString()
			 +", Symbol table is "+symTable.toString()+", Out table is"+out.toString()+", Heap is"+heap.toString()+",Bar is "+bar.toString();}
	 public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl,MyIList<Value> ot,MyIDictionary<StringValue, BufferedReader >fTbl, MyIHeap heap1,MyIBar bar1,IStmt prg){
		 exeStack=stk;
		 symTable=symtbl;
		 out = ot;
		 originalProgram=prg;
		 FileTable=fTbl;
		 heap=heap1;
		 bar=bar1;
		 //incrementId();
		 synchronized(this) {id=generateKey();}
		 //IStmt originalProgram=(IStmt)Object.clone(prg);
		 //IStmt originalProgram=(IStmt)deepCopy(prg);//recreate the entire original prg
		 stk.push(prg);
		 //prgStates.add(prg);
	 }
	 
	 public synchronized static int generateKey() {return count++;}
	 
	 //public int getId() {return id;}
	 public MyIStack<IStmt> getStk() {return exeStack;}
	 public MyIDictionary<String,Value> getSymTable(){return symTable;}
	 public MyIList<Value> getOut() {return out;}
	 public MyIDictionary<StringValue, BufferedReader > getFileTable() {return FileTable;}
	 public MyIHeap getHeap() {return heap;}
	 public MyIBar getBar() {return bar;}
	 
	 public void setStk(MyIStack<IStmt> e) {exeStack=e;}
	 public void setSymTable(MyIDictionary<String,Value> e){symTable=e;}
	 public void setOut(MyIList<Value> e) {out=e;}
	 public void setFileTable(MyIDictionary<StringValue,BufferedReader> e) {FileTable=e;}
	 public void setHeap(MyIHeap heap1) {heap=heap1;}
	 public void setBar(MyIBar bar1) {bar=bar1;}
	 
	 public boolean isNotCompleted() {if(!exeStack.isEmpty()) return true;return false;}
	 
	 public PrgState oneStep() throws MyException, VarNotDefined, DivByZero, VarIsDefined{
		 if(exeStack.isEmpty()) 
			 throw new MyException("prgstate stack is empty");
		 IStmt crtStmt = exeStack.pop();
		 return crtStmt.execute(this);
		 }

}
