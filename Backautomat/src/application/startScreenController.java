package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class startScreenController {

    @FXML
    private Button startBt;

    @FXML
    void handleBtStart(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Menue.fxml"));
    	
    	Stage window = (Stage) startBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }

}