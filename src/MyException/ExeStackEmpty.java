package MyException;

public class ExeStackEmpty extends Exception{
	   String msg;
	   public ExeStackEmpty(String s) {
		   super(s);
	   }
	
	   public String toString() {
	      return msg;
	   }
}
