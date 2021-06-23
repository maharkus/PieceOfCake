
package application;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import application.classes.ShoppingCartProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	private Button goBackBt;
	
	@FXML
	private Button addToSc;
	
	@FXML
	private TextField searchBar;

	@FXML
	private Button searchBt;

	@FXML
	private Button scBt;

	@FXML
	private TextField searchTxt;

	@FXML
    private Button brot;

    @FXML
    private Button broetchen;

    @FXML
    private Button sandwiches;

    @FXML
    private Button kuchen;

    @FXML
    private Button suessgebaeck;

    @FXML
    private Button suesswaren;

    @FXML
    private Button softdrinks;

    @FXML
    private Button heissgetraenke;


	@FXML
	void handleBtGoBack(ActionEvent event) throws IOException {

		String lastScene = Main.history.get(Main.history.size() - 1);

		Parent root = FXMLLoader.load(getClass().getResource(lastScene));
		Stage window = (Stage) goBackBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));

		Main.history.remove(Main.history.size() - 1);
	}

	@FXML
	void handleBtSc(ActionEvent event) throws IOException {

		Main.history.add("Menue.fxml");

		Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
		Stage window = (Stage) goBackBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));
	}

	@FXML
	void handleBtSearch(ActionEvent event) {

	}
	
	
	@FXML
	void handleCatSelection(ActionEvent event) throws IOException {

		Main.history.add("Menue.fxml");
	    
	    final Node source = (Node) event.getSource();
	    Main.selectedCat = source.getId();

		Parent root = FXMLLoader.load(getClass().getResource("productsOverview.fxml"));
		Stage window = (Stage) goBackBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));

	}
	
	public void setTotal(Button b) {
		Double total = 0.00;
		for(ShoppingCartProduct p : Main.shoppingCart) {
			total += p.getAmount()*p.getPreis();
		}

		Locale locale = Locale.GERMANY;
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		String euroPrice = numberFormat.format(total);
		b.setText(euroPrice);
		
	}
	
	public void keyPressesSubmitSearch(KeyEvent e) throws IOException {
		if (e.getCode().equals(KeyCode.ENTER)) {
			Main.searchTerm = searchBar.getText();
			Main.history.add("Menue.fxml");
			Parent root = FXMLLoader.load(getClass().getResource("productsSearch.fxml"));
			Stage window = (Stage) goBackBt.getScene().getWindow();
			window.setScene(new Scene(root, 1920, 1080));

		}
	}
	@FXML
	public void initialize() throws IOException {
		setTotal(scBt);
	}


}
