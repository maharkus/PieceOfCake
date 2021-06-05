package application;

import javafx.application.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.classes.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ArrayList<Person> pl = new ArrayList<Person>();

		try {

			Database database = new Database();

			database.createConnection();
			ResultSet results = database.getStatement().executeQuery("SELECT * FROM Test");

			while (results.next()) {
				Person p = new Person();
				p.setId(results.getInt("id"));
				p.setNachname(results.getString("nachname"));
				p.setVorname(results.getString("vorname"));
				pl.add(p);
			}
			
			Person person = new Person("Johann", "Meier");
			
			String sqlCommand = "INSERT INTO Test (id, vorname, nachname) VALUES ( NULL, '" + person.getVorname() + "','" + person.getNachname() + "')";
			database.getStatement().executeUpdate(sqlCommand);

			database.getConnection().close();
			database.createConnection();

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
