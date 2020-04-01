package ctrl;

import java.io.IOException;
import java.util.*;

import MyException.*;
import model.PrgState;

public interface iCtrl {
	//public PrgState oneStep(PrgState state) throws ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined,Exception;
	void oneStepForAllPrg(List<PrgState> prgList)  throws Exception,ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined;
	public void allStep() throws ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined,Exception;
	//public void allStep(int b) throws ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined,Exception;
	public void displayPrgState (PrgState state);
	public int getLength();
	public void setFlag(boolean b);
	public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
	public void oneStep() throws ExeStackEmpty, VarNotDefined, DivByZero, VarIsDefined,Exception;

}
