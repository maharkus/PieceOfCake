package application;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.classes.Database;
import application.classes.Product;
import application.classes.ShoppingCartProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class startScreenController {

	@FXML
	private Button startBt;

	@FXML
	private Text productName;
	
    @FXML
    private Text angebotstext;
    
    @FXML
    private ImageView Angebotsbild;
    
    @FXML
    private Button addToSc1;

	@FXML
	public void initialize() throws IOException, SQLException {

		Database database = new Database();
		database.createConnection();
		ResultSet results = database.getStatement()
				.executeQuery("SELECT * FROM produkte where produktname= 'Tagesmenü'");
		Product Startseitenprodukt = new Product();

		while (results.next()) {
			Startseitenprodukt.setId(results.getInt("index"));
			Startseitenprodukt.setKategorie(results.getString("kategorie"));
			Startseitenprodukt.setProduktname(results.getString("produktname"));
			Startseitenprodukt.setPreis(results.getDouble("preis"));
			Startseitenprodukt.setAngebottext(results.getString("angebottext"));
		}

		productName.setText(Startseitenprodukt.getProduktname());
		
		angebotstext.setText(Startseitenprodukt.getAngebottext());
		
		//change image
        File file = new File("res/product_images/" + Startseitenprodukt.getId() + ".png");
        Image image = new Image(file.toURI().toString());
        Angebotsbild.setImage(image);

        int stock = Startseitenprodukt.getBestand();
        addToSc1.setOnAction(
                event -> addToCart((ActionEvent) event, (Product) Startseitenprodukt, (int) stock));


		database.getConnection().close();
		

		}
		//add to cart
	private void addToCart(ActionEvent event, Product product, int bestand) {
		int amount = 1;
		ShoppingCartProduct addedProduct = new ShoppingCartProduct(product, amount);
		
		// Check if product already is in cart
		if(Main.shoppingCart.stream().anyMatch(o -> o.getId() == addedProduct.getId())) {
			for(int i=0; i<Main.shoppingCart.size(); i++) {
				int id = Main.shoppingCart.get(i).getId(); 
			    if (id == addedProduct.getId()) {
			    	int cartAmount = Main.shoppingCart.get(i).getAmount()+amount;
			    	
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
	}

	
	@FXML
	void handleBtStart(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Menue.fxml"));

		Main.history.add("startScreen.fxml");

		Stage window = (Stage) startBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));
	}

}