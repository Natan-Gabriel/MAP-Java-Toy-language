package MyException;

public class DivByZero extends Exception{
	   String s;
	   public DivByZero(String s) {
		   super(s);
	   }
	
	   public String toString() {
	      return s;
	   }
	
}
