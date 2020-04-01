package repo;

import model.*;
import model.stmt.*;
import model.values.*;
import java.util.*;
import java.io.*;

public class Repo implements iRepo{
	List<PrgState> list;
	PrintWriter logFile;
	String logFilePath;
	public Repo(List<PrgState> l,String f) {list=l;logFilePath=f;}
	public List<PrgState> getPrgList(){return list;}
	public void setPrgList(List<PrgState> l) {list=l;}
	public int getLength() {return list.size();}

	
	public void logPrgStateExec(PrgState state) throws Exception{
		
		try {
			logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
			//logFile.write("Exe stack\n");
			//logFile.newLine();
			logFile.write("Id of program state: "+Integer.toString(state.getId())+"\n");
			logFile.write("ExeStack:\n");
			for(int i=state.getStk().getSize()-1;i>=0;i--) {
		
				 logFile.write(state.getStk().get(i).toString());
				 logFile.write("\n");
				 
			}
			logFile.write("SymTable:\n");
			
			for(String a:state.getSymTable().keySet()) {
		
				 logFile.write(a.toString()+"="+state.getSymTable().getValue(a).toString()); 
				 logFile.write("\n");
				 
			}
			logFile.write("Out:\n");
			
			for(int i=0;i<state.getOut().getSize();i++) {
		
				 logFile.write(state.getOut().get(i).toString());
				 logFile.write("\n");
				 
			}
			logFile.write("FileTable:\n");
			
			for(StringValue a:state.getFileTable().keySet()) {
		
				logFile.write(a.toString()+"="+state.getFileTable().getValue(a).toString()); 
				logFile.write("\n");
				 
			}
			
			logFile.write("Heap:\n");
			
			for(Integer a:state.getHeap().keySet()) {
		
				 logFile.write(Integer.toString(a)+"->"+state.getHeap().getValue(a).toString()); 
				 logFile.write("\n");
				 
			}
			
			logFile.write("BarTable:\n");
			
			for(Integer a:state.getBar().keySet()) {
		
				 logFile.write(Integer.toString(a)+"->"+state.getBar().getValue(a).toString()); 
				 logFile.write("\n");
				 
			}
			
			logFile.write("\n");
			
		}catch (IOException e) {
			 throw e;
		 } finally {
			 if (logFile!=null)
				 try {
					 logFile.close();
				 } catch (Exception e) {
					 System.err.println("Eroare inchidere fisier "+e);
				 }
		 }
	}
}
