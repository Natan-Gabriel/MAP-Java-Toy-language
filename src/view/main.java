package view;
import model.*;
import model.adt.*;
import model.stmt.*;
import model.types.*;
import model.values.*;
import model.exp.*;
import java.util.*;

import MyException.*;

import java.io.*;
//import application.NewWindowController;
import ctrl.*;
import repo.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class main extends Application{
	public static IStmt ex1,ex2;
	public static PrgState prg1,prg2;
	public static iRepo repo1,repo2;
	public static iCtrl ctrl1,ctrl2;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Scene.fxml"));
			Scene scene = new Scene(root,900,700);
			primaryStage.setTitle("FXML Welcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] arg) {
		
		
    ex1=new CompStmt(new CompStmt(new CompStmt(new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
			new New("v",new ValueExp(new IntValue(20)))),
			new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
				new New("a",new VarExp("v")))),new PrintStmt(new rH(new VarExp("v")))),
			new PrintStmt(new ArithExp('+',new rH(new rH(new VarExp("a"))),new ValueExp(new IntValue(5)))));
	
    MyIStack<IStmt> exeStack1=new MyStack<IStmt>();
	MyIDictionary<String, Value> symTable1=new MyDictionary<String, Value>();
	MyIList<Value> out1=new MyList<Value>();
	MyIDictionary<StringValue,BufferedReader> fTbl1=new MyDictionary<StringValue,BufferedReader>();
	MyIHeap heap1=new MyHeap();
	MyIBar bar1=new MyBar();
    
    MyIDictionary<String,Type> ch1=new MyDictionary<String,Type>();
	try {
		ex1.typecheck(ch1);
		prg1=new PrgState(exeStack1,symTable1,out1,fTbl1,heap1,bar1,ex1);
		
		ArrayList<PrgState> list1 = new ArrayList<PrgState>();
		list1.add(prg1);
		repo1=new Repo(list1,"log1.txt");
		ctrl1=new Ctrl(repo1);
		
		try{
			System.out.println("The execution begins");
			ctrl1.oneStep();
			System.out.println("The execution is over");
		}
		catch(ExeStackEmpty | VarNotDefined | DivByZero | VarIsDefined e1) {
			System.out.println(e1);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}catch(MyExp e) {
		System.out.println(e.toString());
	}
	catch(CloneNotSupportedException e) {
		System.out.println(e.toString());
	}
	
    
	
	ex2=new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),
			new VarDeclStmt("v2",new RefType(new IntType()))),
			new VarDeclStmt("v3",new RefType(new IntType()))   ),
			new VarDeclStmt("cnt",new IntType())  ),
			new New("v1",new ValueExp(new IntValue(2)))   ),
			new New("v2",new ValueExp(new IntValue(3)))  ),
			new New("v3",new ValueExp(new IntValue(4)))   ),
			new NewBarrierStmt("cnt",new rH(new VarExp("v2")))    ),
			new ForkStmt(new CompStmt(new CompStmt(new AwaitStmt("cnt"),
				    new wH("v1",new ArithExp('*',new rH(new VarExp("v1")),new ValueExp(new IntValue(10))))),
					new PrintStmt(new rH(new VarExp("v1")) )	)  )  ),
			new ForkStmt(new CompStmt(new CompStmt(new CompStmt(new AwaitStmt("cnt"),
				    new wH("v2",new ArithExp('*',new rH(new VarExp("v2")),new ValueExp(new IntValue(10))))),
					new wH("v2",new ArithExp('*',new rH(new VarExp("v2")),new ValueExp(new IntValue(10))))	),
					new PrintStmt(new rH(new VarExp("v2")) )	)   )    ),
			new AwaitStmt("cnt")),
			new PrintStmt(new rH(new VarExp("v3")) )			   );
			    
    MyIStack<IStmt> exeStack2=new MyStack<IStmt>();
	MyIDictionary<String, Value> symTable2=new MyDictionary<String, Value>();
	MyIList<Value> out2=new MyList<Value>();
	MyIDictionary<StringValue,BufferedReader> fTbl2=new MyDictionary<StringValue,BufferedReader>();
	MyIHeap heap2=new MyHeap();
	MyIBar bar2=new MyBar();
	
	MyIDictionary<String,Type> ch2=new MyDictionary<String,Type>();
	try {
		ex2.typecheck(ch2);
		prg2=new PrgState(exeStack2,symTable2,out2,fTbl2,heap2,bar2,ex2);
		
		ArrayList<PrgState> list2 = new ArrayList<PrgState>();
		list2.add(prg2);
		repo2=new Repo(list2,"log2.txt");
		ctrl2=new Ctrl(repo2);
		
		try{
			System.out.println("The execution begins");
			ctrl2.oneStep();
			System.out.println("The execution is over");
		}
		catch(ExeStackEmpty | VarNotDefined | DivByZero | VarIsDefined e1) {
			System.out.println(e1);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}catch(MyExp e) {
		System.out.println(e.toString());
	}
	catch(CloneNotSupportedException e) {
		System.out.println(e.toString());
	}
	
		
	launch(arg);
	
	
    
	}
}




















/*try {
	;//repo.logPrgStateExec();
}
catch(Exception e) {
	System.out.println(e);
}

int b=1;//nr of program
while(true) {
	
	System.out.println("1.Input a program: ");
    System.out.println("2.Complete execution of given program: ");
    System.out.println("3.Break");
    Scanner scan1= new Scanner(System.in);
    int a= scan1.nextInt();
    if(a==1)
    {
    	System.out.println("1.Select a program: ");
    	Scanner scan2= new Scanner(System.in);
        b= scan2.nextInt();
        if(b>ctrl.getLength())
        	System.out.println("Index out of bounds");
    }
    if (a==2)
    {	try{
			System.out.println("The execution begins");
			ctrl.allStep(b);
			System.out.println("The execution is over");
		}
		catch(ExeStackEmpty | VarNotDefined | DivByZero | VarIsDefined e1) {
			System.out.println(e1);
		}
    }
    if(a==3)
    	break;
}*/
