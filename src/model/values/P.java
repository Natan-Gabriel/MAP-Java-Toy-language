package model.values;
import javafx.beans.property.SimpleStringProperty;
/*public class P {
	private ObservableValue<Number> x;
	private Value y;
	public P ( Integer a,Value b){x=new SimpleIntegerProperty(a);y=b;}
	public SimpleIntegerProperty getInt() {return (SimpleIntegerProperty) x;}
	public Value getValue() {return y;}
	public void setInt(Integer a) { ((IntegerPropertyBase) firstNameProperty()).set(a);}
	public void setValue(Value b) {y=b;}
	public ObservableValue<Integer> firstNameProperty() {
		// TODO Auto-generated method stub
		if (x == null) x = new SimpleIntegerProperty(1);
        return x;
	}
}*/
/*
public class P {
    private StringProperty firstName;
 
		// TODO Auto-generated constructor stub
	
	public void setFirstName(String value) { firstNameProperty().set(value); }
    public String getFirstName() { return firstNameProperty().get(); }
    public StringProperty firstNameProperty() { 
        if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
        return firstName; 
    }

    private StringProperty lastName;
    public P(String string, String string2) {setFirstName(string);setLastName(string2);}
    public void setLastName(String value) { lastNameProperty().set(value); }
    public String getLastName() { return lastNameProperty().get(); }
    public StringProperty lastNameProperty() { 
        if (lastName == null) lastName = new SimpleStringProperty(this, "lastName");
        return lastName; 
    } 
}*/


public class P {
	   private final SimpleStringProperty firstName = new SimpleStringProperty("");
	   private final SimpleStringProperty lastName = new SimpleStringProperty("");


	public P() {
	        this("", "");
	    }
	 
	    public P(String firstName, String lastName) {
	        setFirstName(firstName);
	        setLastName(lastName);
	    }

	    public String getFirstName() {
	        return firstName.get();
	    }
	 
	    public void setFirstName(String fName) {
	        firstName.set(fName);
	    }
	        
	    public String getLastName() {
	        return lastName.get();
	    }
	    
	    public void setLastName(String fName) {
	        lastName.set(fName);
	    }

	}


