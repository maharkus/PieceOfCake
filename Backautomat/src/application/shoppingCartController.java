package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class shoppingCartController {

    @FXML
    private Line bigline;

    @FXML
    private Button cancelBuyBt;

    @FXML
    private Button buyBt;

    @FXML
    private Button addBt;

    @FXML
    private Button substractBt;

    @FXML
    private Button goBackBt;
    
    public void handleBtBuy() throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("paymentMethods.fxml"));
    	
    	Stage window = (Stage) buyBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }

    public void handleBtBack() throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
    	
    	Stage window = (Stage) goBackBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }
}
