package application;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import application.classes.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
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
    
    @FXML
	private GridPane shoppingCartGrid;

	private int row;
    
    ArrayList<Product> pl;
    
    public void handleBtBuy() throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("paymentMethods.fxml"));

    	Main.history.add("shoppingcart.fxml");
    	
    	Stage window = (Stage) buyBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    }

    public void handleBtBack() throws Exception {
    	
    	String lastScene = Main.history.get(Main.history.size()-1);
    	
    	Parent root = FXMLLoader.load(getClass().getResource(lastScene));
    	Stage window = (Stage) goBackBt.getScene().getWindow();
    	window.setScene(new Scene(root, 1920, 1080));
    	
    	
    	Main.history.remove(Main.history.size()-1);
    }
    
    public GridPane createGrid() throws IOException {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(15);

		for (int i = 0; i<Main.shoppingCart.size(); i++) {
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/application/shoppingCartItems.fxml"));
			grid.add(newLoadedPane, 0, i);
			
			// Change Product name
			Text productName = (Text) newLoadedPane.lookup("#productName");
			productName.setText(Main.shoppingCart.get(i).getProduktname());

			// Change Product Price
			Text productPrice = (Text) newLoadedPane.lookup("#productPrice");
			Locale locale = Locale.GERMANY;
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
			String euroPrice = numberFormat.format(Main.shoppingCart.get(i).getPreis());
			productPrice.setText(euroPrice);
			
			Text productAmount = (Text) newLoadedPane.lookup("#productAmount");
			productAmount.setText(String.valueOf(Main.shoppingCart.get(i).getAmount()));
			
		}
		;

		return grid;
	}
    
    public void initialize() throws IOException {
		shoppingCartGrid.add(createGrid(), 0, row);
	}
}
