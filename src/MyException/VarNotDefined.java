package MyException;

public class VarNotDefined extends Exception{
	   String s;
	   public VarNotDefined(String s) {
		   super(s);
	   }
	
	   public String toString() {
	      return s;
	   }
}
