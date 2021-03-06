
package application;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import application.classes.Database;
import application.classes.Product;
import application.classes.ShoppingCartProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	private Text productName;
	
    @FXML
    private Text angebotstext;
    
    @FXML
    private ImageView Angebotsbild;
    
    @FXML
    private Button addToSc1;

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
    private Button allProductsBt;


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
	void handleBtAllProducts(ActionEvent event) throws IOException {

		Main.history.add("Menue.fxml");

		Parent root = FXMLLoader.load(getClass().getResource("allProducts.fxml"));
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
	
	public void clickSubmitSearch() throws IOException {
		Main.searchTerm = searchBar.getText();
		Main.history.add("Menue.fxml");
		Parent root = FXMLLoader.load(getClass().getResource("productsSearch.fxml"));
		Stage window = (Stage) goBackBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));

	}
		
	@FXML
	public void initialize() throws IOException {
		setTotal(scBt);
		Product Startseitenprodukt = Main.menuProduct;
		productName.setText(Main.menuProduct.getProduktname());
		
		angebotstext.setText(Startseitenprodukt.getAngebottext());
		
		//change image
        File file = new File("res/product_images/" + Startseitenprodukt.getId() + ".png");
        Image image = new Image(file.toURI().toString());
        Angebotsbild.setImage(image);

        int stock = Startseitenprodukt.getBestand();
        addToSc.setOnAction(
                event -> addToCart((ActionEvent) event, (Product) Startseitenprodukt, (int) stock));
	}
	
	//add to cart
	private void addToCart(ActionEvent event, Product product, int bestand) {
		
		ShoppingCartProduct addedProduct = new ShoppingCartProduct(product, 1);
		
		// Check if product already is in cart
		if(Main.shoppingCart.stream().anyMatch(o -> o.getId() == addedProduct.getId())) {
			for(int i=0; i<Main.shoppingCart.size(); i++) {
				int id = Main.shoppingCart.get(i).getId(); 
			    if (id == addedProduct.getId()) {
			    	int cartAmount = Main.shoppingCart.get(i).getAmount()+1;
			    	
			    	//prevent cartAmound being bigger than stock
			    	if (cartAmount > bestand) {
			    		cartAmount = bestand;
			    	}
			        Main.shoppingCart.get(i).setAmount(cartAmount);
			    }
			}
		}
		else {
			Main.shoppingCart.add(addedProduct);
		}
		System.out.println(Main.shoppingCart.toString());
		setTotal(scBt);
	}


}
