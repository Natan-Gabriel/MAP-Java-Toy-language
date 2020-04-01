package MyException;

public class MyException extends Exception{
	   String s;
	   public MyException(String s) {
		   super(s);
	   }
	   
	   public String toString() {
	      return s;
	   }

}
