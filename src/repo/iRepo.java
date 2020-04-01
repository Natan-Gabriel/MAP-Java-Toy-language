package repo;
import java.io.IOException;
import java.util.List;

import model.*;
public interface iRepo {
	//public PrgState getCrtPrg();
	//public PrgState getCrtPrg(int b);
	public List<PrgState> getPrgList();
	public void setPrgList(List<PrgState> l);
	public int getLength();
	
	void logPrgStateExec(PrgState state) throws Exception;

}
