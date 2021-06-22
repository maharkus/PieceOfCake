package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.classes.Database;
import application.classes.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class startScreenController {

    @FXML
    private Button startBt;
    
    @FXML
    private Text productName;
    
    @FXML
    public void initialize() throws IOException, SQLException {
    	
         
         Database database = new Database();
         database.createConnection();
         ResultSet results = database.getStatement()
                 .executeQuery("SELECT * FROM produkte where produktname= 'Tagesmenü'");
Product Startseitenprodukt = new Product();
             Startseitenprodukt.setId(results.getInt("index"));
             Startseitenprodukt.setKategorie(results.getString("kategorie"));
             Startseitenprodukt.setProduktname(results.getString("produktname"));
             Startseitenprodukt.setPreis(results.getDouble("preis"));

             productName.setText(Startseitenprodukt.getProduktname());
             
         database.getConnection().close();
        
}


           



    @FXML
    void handleBtStart(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Menue.fxml"));
    	
    	Main.history.add("startScreen.fxml");
    	
    	
    	Stage window = (Stage) startBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }

}