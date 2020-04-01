package view;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class newWindow {
	@FXML
	private Text text;
	
	private Stage mainStage;

	// Event Listener on Button[#button1].onAction
	@FXML
	public void initialize() {
		 Label secondLabel = new Label("new window");
		 
         StackPane secondaryLayout = new StackPane();
         secondaryLayout.getChildren().add(secondLabel);

         Scene secondScene = new Scene(secondaryLayout, 230, 100);

         // New window (Stage)
         Stage newWindow = new Stage();
         newWindow.setTitle("Second Stage");
         newWindow.setScene(secondScene);

         // Set position of second window, related to primary window.
         newWindow.setX(mainStage.getX() + 200);
         newWindow.setY(mainStage.getY() + 100);

         newWindow.show();
	}
	// Event Listener on Button[#button2].onAction
	
	public void setMainStage(Stage primaryStage) {
		// TODO Auto-generated method stub
		mainStage = primaryStage;
	}
}


