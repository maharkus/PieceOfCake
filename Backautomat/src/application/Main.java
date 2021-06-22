package application;

import javafx.application.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.classes.Database;
import application.classes.Product;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    public static ArrayList<String> history = new ArrayList<String>();
    public static String selectedCat;
    public static String searchTerm;

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
