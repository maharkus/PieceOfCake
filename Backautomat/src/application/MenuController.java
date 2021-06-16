
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

public class MenuController {

	@FXML
	private Button goBackBt;

	@FXML
	private Button searchBt;

	@FXML
	private Button scBt;

	@FXML
	private TextField searchTxt;

	@FXML
	private Button catBreadBt;

	@FXML
	private Button catBreadBt;

	@FXML
	private Button catBroetchenBt;

	@FXML
	private Button catSandwichBt;

	@FXML
	private Button catCakeBt;

	@FXML
	private Button catDonutsBt;

	@FXML
	private Button catSweetsBt;

	@FXML
	private Button catSoftdrinksBt;

	@FXML
	private Button catHotdrinksBt;


	@FXML
	void handleBtGoBack(ActionEvent event) throws IOException {

		String lastScene = Main.history.get(Main.history.size() - 1);
		System.out.println(Main.history.size());

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

		Parent root = FXMLLoader.load(getClass().getResource("productsOverview.fxml"));
		Stage window = (Stage) goBackBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));

	}

}
