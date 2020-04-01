package MyException;

public class VarIsDefined extends Exception{
	   String s;
	   public VarIsDefined(String s) {
		   super(s);
	   }
	
	   public String toString() {
	      return s;
	   }
}
