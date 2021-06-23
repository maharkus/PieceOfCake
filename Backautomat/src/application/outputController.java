package application;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.classes.Database;
import application.classes.Product;
import application.classes.ShoppingCartProduct;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class outputController {

	@FXML
	private ImageView product1;

	@FXML
	private ImageView product2;

	@FXML
	private ImageView product3;

	@FXML
	private ImageView backToStart;

	@FXML
	public void backToStart(MouseEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));

		Stage window = (Stage) backToStart.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));
	}

	public void initialize() throws IOException, SQLException {
		
		ArrayList<ShoppingCartProduct> shoppingCart = Main.shoppingCart;
		ImageView[] productImages = {product1, product2, product3};

		int productDisplay = Main.shoppingCart.size();
		if (productDisplay > 3) {
			productDisplay = 3;
		}

		for (int i = 0; i < productDisplay; i++) {

			File file = new File("res/product_images/" + shoppingCart.get(i).getId() + ".jpg");
			Image image = new Image(file.toURI().toString());
			productImages[i].setImage(image);
		}

		Database database = new Database();

		database.createConnection();
		String SQLQuery = "";
		Statement statement = database.getConnection().createStatement();
		for (ShoppingCartProduct p : shoppingCart) {
			SQLQuery = "UPDATE produkte SET Stueckzahl =" + (p.getBestand() - p.getAmount()) + " WHERE `Index` =" + p.getId() + ";";
			statement.addBatch(SQLQuery);
		}
		statement.executeBatch();
		database.getConnection().close();
		
		shoppingCart.clear();
		Main.history.clear();

	}

}
