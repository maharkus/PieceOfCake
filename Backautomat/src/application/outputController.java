package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class outputController {

    /*
	@FXML
    private Button backToMenu;
    
    public void handleBtBackToMenu() throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("paymentMethods.fxml"));
    	
    	Stage window = (Stage) backToMenu.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }
    */
    
    @FXML
	private AnchorPane backToMenu;
	
	@FXML
	public void handleBtBackToMenu(MouseEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("paymentMethods.fxml"));
    	
    	Stage window = (Stage) backToMenu.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
	}
}

