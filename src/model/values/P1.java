package model.values;

import javafx.beans.property.SimpleStringProperty;


public class P1 {
	   private final SimpleStringProperty firstName = new SimpleStringProperty("");
	   private final SimpleStringProperty lastName = new SimpleStringProperty("");
	   private final SimpleStringProperty lastName1 = new SimpleStringProperty("");


	public P1() {
	        this("", "","");
	    }
	 
	    public P1(String firstName, String lastName,String lastName1) {
	        setFirstName(firstName);
	        setLastName(lastName);
	        setLastName1(lastName1);
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

	    public String getLastName1() {
	        return lastName1.get();
	    }
	    
	    public void setLastName1(String fName) {
	        lastName1.set(fName);
	    }
	}


