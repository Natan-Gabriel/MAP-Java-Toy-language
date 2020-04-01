package MyException;

public class EmptyCollection extends Exception
{
	   String s;
	   public EmptyCollection(String s) {
		   super(s);
	   }
	
	   public String toString() {
	      return s;
	   }
}
