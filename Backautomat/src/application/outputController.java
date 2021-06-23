package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	private AnchorPane backToMenu;

	@FXML
	public void handleBtBackToMenu(MouseEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("paymentMethods.fxml"));

		Stage window = (Stage) backToMenu.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));
	}

	public void initialize() throws IOException {
		ArrayList<ShoppingCartProduct> shoppingCart = Main.shoppingCart;
		ArrayList<ImageView> productImages = new ArrayList<ImageView>();
		productImages.add(product1);
		productImages.add(product2);
		productImages.add(product3);

		int productDisplay = Main.shoppingCart.size();
		if (productDisplay > 3) {
			productDisplay = 3;
		}

		for (int i = 0; i < productDisplay; i++) {

			File file = new File("res/product_images/" + shoppingCart.get(i).getId() + ".jpg");
			Image image = new Image(file.toURI().toString());
			productImages.get(i).setImage(image);
		}
	}
}
