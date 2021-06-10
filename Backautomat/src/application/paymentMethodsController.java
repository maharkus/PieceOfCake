package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class paymentMethodsController {

    @FXML
    private Button visaBt;

    @FXML
    private Button mastercardBt;

    @FXML
    private Button ecBt;
    
    
    
    public void handleBtVisa() throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("output.fxml"));
    	
    	Stage window = (Stage) visaBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }
    
    public void handleBtMastercard() throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("output.fxml"));
    	
    	Stage window = (Stage) visaBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }
    
    public void handleBtEC() throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("output.fxml"));
    	
    	Stage window = (Stage) visaBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }

}

