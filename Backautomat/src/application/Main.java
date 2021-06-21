package application;

import javafx.application.Application;

import java.util.ArrayList;

import application.classes.ShoppingCartProduct;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    public static ArrayList<String> history = new ArrayList<String>();
    public static ArrayList<ShoppingCartProduct> shoppingCart = new ArrayList<ShoppingCartProduct>();
    public static String selectedCat;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
			Scene scene = new Scene(root, 1920, 1080);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		launch(args);
	}
}
