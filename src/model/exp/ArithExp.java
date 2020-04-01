package model.exp;
import model.values.*;
import model.adt.*;
import model.types.*;
import MyException.*;

public class ArithExp implements Exp{
	 Exp e1;
	 Exp e2;
	 int op; //1-plus, 2-minus, 3-star, 4-divide
	 char a='+';
	 char b='-';
	 char c='*';
	 char d='/';
	 char oper;
	 //....
	 //public ArithExp(Exp m,Exp n) {e1=m;e2=n;}
	 public ArithExp(char a,Exp m,Exp n) {oper=a;e1=m;e2=n;if (a=='+') op=1;
	 		if (a=='-') op=2;if (a=='*') op=3;if (a=='/') op=4;}
	 public  String toString() {return e1.toString()+oper+e2.toString();}
	 public Value eval(MyIDictionary<String,Value> tbl,MyIHeap hp) throws VarNotDefined,DivByZero {
		 Value v1,v2;
		 v1= e1.eval(tbl,hp);
		 if (v1.getType().equals(new IntType())) {
			 v2 = e2.eval(tbl,hp);
			 if (v2.getType().equals(new IntType())) {
				 IntValue i1 = (IntValue)v1;
				 IntValue i2 = (IntValue)v2;
				 int n1,n2;
				 n1= i1.getVal();
				 n2 = i2.getVal();
				 if (op==1) return new IntValue(n1+n2);
				 if (op ==2) return new IntValue(n1-n2);
				 if(op==3) return new IntValue(n1*n2);
				 if(op==4)
					 if(n2==0) 
						 throw new DivByZero("division by zero");
					 else return new IntValue(n1/n2);
			 }else
				 throw new VarNotDefined("second operand is not an integer");
		 }else
			 throw new VarNotDefined("first operand is not an integer");
		 return new IntValue(1/1);
	 }
	 public Type getType(MyIDictionary<String,Value> tbl,MyIHeap hp) throws VarNotDefined,DivByZero {
		 return eval(tbl,hp).getType();
	 }
	 public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyExp{
		 Type typ1, typ2;
		  typ1=e1.typecheck(typeEnv);
		  typ2=e2.typecheck(typeEnv);
		  if (typ1.equals(new IntType())) {
			  if(typ2.equals(new IntType())) {
				  return new IntType();
			  } else
				  throw new MyExp("second operand is not an integer");
		 }else
			 throw new MyExp("first operand is not an integer");
		  } 

}
