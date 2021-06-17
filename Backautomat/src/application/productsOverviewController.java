package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.classes.Database;
import application.classes.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class productsOverviewController {

	@FXML
	private Button goBackBt;

	@FXML
	private Button searchBt;

	@FXML
	private Button scBt;
	
	@FXML
    private GridPane productGrid;

    private int row;
    
    ArrayList<Product> pl; 

	@FXML
	void handleBtGoBack(ActionEvent event) throws IOException {

		String lastScene = Main.history.get(Main.history.size() - 1);
		System.out.println(Main.history.size());
    	System.out.println("clicked");

		Parent root = FXMLLoader.load(getClass().getResource(lastScene));
		Stage window = (Stage) goBackBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));

		Main.history.remove(Main.history.size() - 1);
	}

	@FXML
	void handleBtSc(ActionEvent event) throws IOException {

		Main.history.add("productsOverview.fxml");

		Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
		Stage window = (Stage) goBackBt.getScene().getWindow();
		window.setScene(new Scene(root, 1920, 1080));

	}
	
	@FXML
	void getProducts() {

		pl = new ArrayList<Product>();

		try {

			Database database = new Database();

			database.createConnection();
			ResultSet results = database.getStatement().executeQuery("SELECT * FROM produkte WHERE Kategorie='" + Main.selectedCat + "'");

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
	}
	
	public GridPane createGrid() throws IOException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        for(int i = 0; i < pl.size(); i++) {
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/application/products.fxml"));
            grid.add(newLoadedPane, (i+1)%2, (i+1)/2);
            Text productName = (Text) newLoadedPane.lookup("#productName");
            productName.setText(pl.get(i).getProduktname());
            };

        return grid;
    }

	 @FXML
	    public void initialize() throws IOException {
		 	getProducts();
		 	productGrid.add(createGrid(), 0, row);
	        System.out.println("second");
	    }
	
}
