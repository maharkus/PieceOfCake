package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class toolBarController {

    @FXML
    private Button goBackBt;

    @FXML
    private Button searchBt;

    @FXML
    private Button scBt;
    
    @FXML
    private TextField searchTxt;

    @FXML
    void handleBtGoBack(ActionEvent event) throws IOException {
    	//Parent root = FXMLLoader.load(getClass()...;				+++i want to load previous scene+++
    	
    	//Stage window = (Stage) goBackBt.getScene().getWindow();
    	//window.setScene(new Scene(root, 1920, 1080));
    }

    @FXML
    void handleBtSc(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
    	
    	Stage window = (Stage) scBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }

    @FXML
    void handleBtSearch(ActionEvent event) {
    	
    }

}


