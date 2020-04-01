package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.PrgState;
import model.adt.*;
import model.stmt.*;
import model.values.*;
import repo.iRepo;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import MyException.DivByZero;
import MyException.ExeStackEmpty;
import MyException.VarIsDefined;
import MyException.VarNotDefined;
import ctrl.iCtrl;

import java.util.Collections;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TextFieldController{
	int index=0,index2=0;
	PrgState crtPrg;
	iRepo crtRepo;
	iCtrl crtCtrl;
	@FXML
	private TextField textField;
	@FXML
    private TableView<P> table1;
	@FXML
    private ListView<Value> list1;
	@FXML
    private ListView<String> list2;
	@FXML
    private ListView<String> list3;
	@FXML
    private TableView<P> table2;
	@FXML
    private ListView<IStmt> list4;
	@FXML
    private TableView<P1> table3;
	
	
	@FXML
	public void initialize() {
		//number of prg states
		textField.setText("2");
		//new window
		ListView list0 = new ListView();
		list0.getItems().add(main.ex1.toString());
	    list0.getItems().add(main.ex2.toString());
		 
	    StackPane secondaryLayout = new StackPane();
	    secondaryLayout.getChildren().add(list0);

	    Scene secondScene = new Scene(secondaryLayout, 600, 300);

	    // New window (Stage)
	    Stage newWindow = new Stage();
	    newWindow.setTitle("Second Stage");
	    newWindow.setScene(secondScene);

	    // Set position of second window, related to primary window.
	    newWindow.setX(400);
	    newWindow.setY(300);

	    newWindow.show();
	    
	    
	    list0.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            index = list0.getSelectionModel().getSelectedIndex();
	            setCrt();
	            populate();
	        }
	    });
	    
	    list3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            index2 = list3.getSelectionModel().getSelectedIndex();
	            if(index2!=-1)
	            	populate2();
	        }
	    });
	    //
		TableColumn id=new TableColumn("id");
		TableColumn col=new TableColumn("col");
		table1.getColumns().addAll(id,col);
		
		id.setCellValueFactory(new PropertyValueFactory<P,String>("firstName")); 
		col.setCellValueFactory(new PropertyValueFactory<P,String>("lastName")); 


		TableColumn id2=new TableColumn("id");
		TableColumn col2=new TableColumn("col");
		table2.getColumns().addAll(id2,col2);
		
		id2.setCellValueFactory(new PropertyValueFactory<P,String>("firstName")); 
		col2.setCellValueFactory(new PropertyValueFactory<P,String>("lastName")); 
		
		TableColumn id3=new TableColumn("index");
		TableColumn col3=new TableColumn("value");
		TableColumn col33=new TableColumn("list of values");
		table3.getColumns().addAll(id3,col3,col33);
		
		id3.setCellValueFactory(new PropertyValueFactory<P1,String>("firstName")); 
		col3.setCellValueFactory(new PropertyValueFactory<P1,String>("lastName")); 
		col33.setCellValueFactory(new PropertyValueFactory<P1,String>("lastName1")); 
    
   
}
	
	private void setCrt() {
		 if(index==0)
		 {
			 crtPrg=main.prg1;
			 crtRepo=main.repo1;
			 crtCtrl=main.ctrl1;
		 }
		 if(index==1)
		 {
			 crtPrg=main.prg2;
			 crtRepo=main.repo2;
			 crtCtrl=main.ctrl2;
		 }
		 

	}
	
	 private void populate() {
			 table1.setItems(getHeap(crtPrg));
			 table3.setItems(getBarTable(crtPrg));
			 
			 MyIList<Value> a=crtPrg.getOut();
			 ArrayList<Value> b=a.getList();
			 ObservableList<Value> l2 = FXCollections.observableArrayList(b);
			 list1.setItems(l2);
			 
			 MyIDictionary<StringValue,BufferedReader> fTbl=crtPrg.getFileTable();
			 HashMap<StringValue,BufferedReader> hash=fTbl.getContent();
			 ObservableList<String> l3 = fromHashMap2(hash);
			 list2.setItems(l3);
			 
			 ArrayList<String> c=new ArrayList<String>();
			 List<PrgState> l4=crtRepo.getPrgList();
			 for(int i=0;i<l4.size();i++) 
			 {
				 c.add(Integer.toString(l4.get(i).getId()));
			 }
			 ObservableList<String> l5 = FXCollections.observableArrayList(c);
			 list3.setItems(l5);
	}
	 
	 
	 private void populate2() {
		 PrgState a1=crtRepo.getPrgList().get(index2);
		 MyIDictionary<String,Value> a2=a1.getSymTable();
		 HashMap<String,Value> a3=a2.getContent();
		 
		 ArrayList<P> l=new ArrayList<P>();
		 for (Map.Entry<String,Value> entry : a3.entrySet()) {
			   l.add(new P(entry.getKey(),entry.getValue().toString()));
			}
		 ObservableList<P> l1 = FXCollections.observableArrayList(l);
		 table2.setItems(l1);
		 
		 
		 MyIStack<IStmt> a4=a1.getStk();
		 Stack<IStmt> a5=a4.getStack();
		 Stack<IStmt> a6=new Stack<IStmt>();
		 while(!a5.isEmpty())
			 a6.push(a5.pop());
		 ObservableList<IStmt> l2 = FXCollections.observableArrayList(a6);
		 while(!a6.isEmpty())
			 a5.push(a6.pop());
		 list4.setItems(l2);
	}

	 private final ObservableList<P> getHeap(PrgState crtPrg) {
		 Map<Integer,Value> c=new HashMap<Integer,Value>();

		 MyIHeap b=crtPrg.getHeap();
		 c=b.getContent();
		 ArrayList<P> d=new ArrayList<P>();
		 for (Map.Entry<Integer,Value> entry : c.entrySet()) {
			   d.add(new P(Integer.toString(entry.getKey()),entry.getValue().toString()));
			}
		 ObservableList<P> list = FXCollections.observableArrayList(d);
	      return list;
  }
	 
	 private final ObservableList<P1> getBarTable(PrgState crtPrg) {
		 Map<Integer,Pair<Integer,ArrayList<Integer>>> c=new HashMap<Integer,Pair<Integer,ArrayList<Integer>>>();

		 MyIBar bar=crtPrg.getBar();
		 c=bar.getContent();
		 ArrayList<P1> d=new ArrayList<P1>();
		 for (Map.Entry<Integer,Pair<Integer,ArrayList<Integer>>> entry : c.entrySet()) {
			   d.add(new P1(Integer.toString(entry.getKey()),entry.getValue().get1().toString(),entry.getValue().get2().toString()));
			}
		 ObservableList<P1> list = FXCollections.observableArrayList(d);
	      return list;
  }

	 private final ObservableList<String> fromHashMap2(HashMap<StringValue,BufferedReader> h) {
		 ArrayList<String> l=new ArrayList<String>();
		 for (Map.Entry<StringValue,BufferedReader> entry : h.entrySet()) {
			   l.add(entry.getKey().toString());
			}
		 ObservableList<String> list = FXCollections.observableArrayList(l);
	      return list;
  }
	 
	    @FXML
		public void handleSubmitButtonAction(ActionEvent event) {
			try{
				    crtCtrl.oneStep();
				}
				catch(ExeStackEmpty | VarNotDefined | DivByZero | VarIsDefined e1) {
					System.out.println(e1);
				}
				catch(Exception e) {
					System.out.println(e);
				}

		 ArrayList<P> l=new ArrayList<P>();
		 ObservableList<P> l1 = FXCollections.observableArrayList(l);
		 table2.setItems(l1);
		 
		 Stack<IStmt> a6=new Stack<IStmt>();
		 ObservableList<IStmt> l2 = FXCollections.observableArrayList(a6);
		 list4.setItems(l2);
		 
		 populate();
		 
		}
	 
}
















