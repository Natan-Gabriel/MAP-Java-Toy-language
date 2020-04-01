package view;
import java.io.IOException;

import MyException.*;
import ctrl.*;

public class RunExample extends Command{
	 private iCtrl ctrl;
	 public RunExample(String key, String desc,iCtrl ctrl){
		 super(key, desc);
		 this.ctrl=ctrl;
	 }
	 @Override
	 public void execute() {
		 try{
				System.out.println("The execution begins");
				ctrl.allStep();
				System.out.println("The execution is over");
			}
			catch(ExeStackEmpty | VarNotDefined | DivByZero | VarIsDefined e1) {
				System.out.println(e1);
			}
			catch(Exception e) {
				System.out.println(e);
			}
	 }

}
