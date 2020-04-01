package ctrl;
import model.*;
import model.adt.*;
import model.stmt.*;
import model.values.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import MyException.*;
import repo.*;

public class Ctrl implements iCtrl{
	iRepo repo; 
	boolean flag=true;
	ExecutorService executor;
	public Ctrl(iRepo r) {repo=r;}
	
	Map<Integer,Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
	return heap.entrySet().stream()
			.filter(e->(symTableAddr.contains(e.getKey()) ||  getAddrFromHeap(heap.values()).contains(e.getKey())))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue) ) ;}
	
	List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
	return symTableValues.stream()
			.filter(v-> v instanceof RefValue)
			.map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
			.collect(Collectors.toList());
	}
	
	List<Integer> getAddrFromHeap(Collection<Value> HeapValues){
		return HeapValues.stream()
				.filter(v-> v instanceof RefValue)
				.map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
				.collect(Collectors.toList());
		}

	public void oneStepForAllPrg(List<PrgState> prgList)  throws Exception,ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined {
		prgList.forEach(prg ->{
			try {
				repo.logPrgStateExec(prg);
			} catch (Exception e) {
				System.out.println(e);
			}
		});

		//RUN concurrently one step for each of the existing PrgStates
		 //prepare the list of callables
		 List<Callable<PrgState>> callList = prgList.stream()
		 .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
		 .collect(Collectors.toList());

		 //start the execution of the callables
		 //it returns the list of new created PrgStates (namely threads)
		 List<PrgState> newPrgList = executor.invokeAll(callList). stream()
		 .map(future -> { try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
		return null;})
		 .filter(p -> p!=null)
		 .collect(Collectors.toList());

		 prgList.addAll(newPrgList);
		 
		 prgList.forEach(prg ->{
			try {
				repo.logPrgStateExec(prg);
			} catch (Exception e) {
				System.out.println(e);
			}
		}); 
		 repo.setPrgList(prgList);
		 displayAllPrgState(prgList);
		}

	public void allStep() throws Exception,ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined{
		executor = Executors.newFixedThreadPool(2);
		 List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
		 while(prgList.size() > 0){
			 prgList.get(0).getHeap().setContent(unsafeGarbageCollector(
					 getAddrFromSymTable(concatenateAll(prgList)),
					 prgList.get(0).getHeap().getContent()));
			 oneStepForAllPrg(prgList);
			 prgList=removeCompletedPrg(repo.getPrgList());
		 }
		 executor.shutdownNow();
		 //HERE the repository still contains at least one Completed Prg
		 // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method

		 repo.setPrgList(prgList); 
		 
	}
	
	public void oneStep() throws Exception,ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined{
		int index=0;
		for (PrgState prg: repo.getPrgList())
			if(!prg.getStk().isEmpty())
				index=1;
		if(index==0)
			{
			System.out.println("The execution is complete!!");
			return;
			}
		executor = Executors.newFixedThreadPool(2);
		 List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
			 prgList.get(0).getHeap().setContent(unsafeGarbageCollector(
					 getAddrFromSymTable(concatenateAll(prgList)),
					 prgList.get(0).getHeap().getContent()));
			 oneStepForAllPrg(prgList);
			 prgList=removeCompletedPrg(repo.getPrgList());
		 
		 executor.shutdownNow();
		 //HERE the repository still contains at least one Completed Prg
		 // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method

		 repo.setPrgList(prgList); 
		 
	}
	
	public List<Collection<Value>> concatenate(List<PrgState> prgList)
	{
		return prgList.stream()
				.map( p -> {return p.getSymTable().getContent().values();})
				.collect(Collectors.toList());
	}
	public Collection<Value> concatenateAll(List<PrgState> prgList)
	{
		List<Collection<Value>> l=concatenate(prgList);
		return l.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());  
	}
	
	public void displayPrgState (PrgState state) {
		System.out.println(state);
	}
	public void displayAllPrgState (List<PrgState> state) {
		for(PrgState s:state)
			System.out.println(s);
	}
	public void setFlag (boolean val) {
		flag=val;
	}
	public int getLength() {
		return repo.getLength();
	}
	
	public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
		return inPrgList.stream()
				 .filter(p -> p.isNotCompleted())
				 .collect(Collectors.toList());
	}
	
	
}
