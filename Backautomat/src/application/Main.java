package application;

import javafx.application.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.classes.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    public static ArrayList<String> history = new ArrayList<String>();
	
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

		ArrayList<Product> pl = new ArrayList<Product>();

		try {

			Database database = new Database();

			database.createConnection();
			ResultSet results = database.getStatement().executeQuery("SELECT * FROM produkte");

			while (results.next()) {
				Product p =new Product();
				p.setId(results.getInt("index"));
				p.setKategorie(results.getString("kategorie"));
				p.setProduktname(results.getString("produktname"));
				pl.add(p);
			}

			pl.forEach((item) -> {
				System.out.println(item.toString());
			});

			database.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}
}
