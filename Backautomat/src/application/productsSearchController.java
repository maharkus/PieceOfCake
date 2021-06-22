package application;

import java.io.File;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import application.classes.Database;
import application.classes.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class productsSearchController {

	@FXML
	private Button goBackBt;

	@FXML
	private Button searchBt;

	@FXML
	private Button scBt;

	@FXML
	private GridPane productGrid;

	@FXML
	private ScrollPane productWrap;

	@FXML
	private TextField searchBar;

	private int row;

	ArrayList<Product> funde = new ArrayList<Product>();

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


	private void addToCart(ActionEvent event, int index, int amount) {
		System.out.println(index + ", " + amount);
	}



	public void searchBarBT(String eingabe) {
		ArrayList<Product> pl = new ArrayList<Product>();

		try {

			Database database = new Database();

			database.createConnection();
			ResultSet results = database.getStatement().executeQuery("SELECT * FROM produkte");

			while (results.next()) {
				Product p = new Product();
				p.setId(results.getInt("index"));
				p.setKategorie(results.getString("kategorie"));
				p.setProduktname(results.getString("produktname"));
				p.setPreis(results.getDouble("preis"));
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
		;
		

		String suche = eingabe; 

		for (Product item : pl) {
			if (item.getProduktname().toLowerCase().contains(suche.toLowerCase())) {
				funde.add(item);
			}
		}
		System.out.println(sortieralgorithmus(funde));
		
	}

	public ArrayList<Product> sortieralgorithmus(ArrayList<Product> products) {
		
		for (int i = 0; i <= products.size() - 1; i++) {
            int minIndex = i;
            Product index = products.get(i);
            int compare = 0;
            int j;
            for (j = i + 1; j < products.size(); j++) {
                compare = index.getProduktname().compareTo(products.get(j).getProduktname());
                if (compare < 0) {
                    index = products.get(j);
                    minIndex = j; } }
            if(minIndex != 1) {
                Product temporaer = products.get(minIndex);
                products.set(minIndex, products.get(i));    
                products.set(i, temporaer); }

        }
		return products;
	}
	public GridPane createGridSearch(ArrayList<Product> sorted) throws IOException {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);

		for (int i = 0; i < sorted.size(); i++) {
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/application/products.fxml"));
			grid.add(newLoadedPane, (i) % 2, Integer.valueOf((int) Math.floor((i) / 2)));

			// Change Product Image
			ImageView productImage = (ImageView) newLoadedPane.lookup("#productImage");
			File file = new File("res/product_images/" + sorted.get(i).getId() + ".jpg");
			Image image = new Image(file.toURI().toString());
			productImage.setImage(image);

			// Amount Buttons

			// Add To Cart Handler
			Button BtAddToCart = (Button) newLoadedPane.lookup("#addToSc");
			int productID = i;
			Text productAmountText = (Text) newLoadedPane.lookup("#productAmount");
			int productAmount = Integer.parseInt(productAmountText.getText());
			BtAddToCart.setOnAction(
					event -> addToCart((ActionEvent) event, (int) sorted.get(productID).getId(), (int) productAmount));

			// Change Product name
			Text productName = (Text) newLoadedPane.lookup("#productName");
			productName.setText(sorted.get(i).getProduktname());

			// Change Product Price
			Text productPrice = (Text) newLoadedPane.lookup("#productPrice");
			Locale locale = Locale.GERMANY;
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
			String euroPrice = numberFormat.format(sorted.get(i).getPreis());
			productPrice.setText(euroPrice);
		}
		;

		return grid;
	}
	
	public void keyPressesSubmitSearch(KeyEvent e) throws IOException {
		if (e.getCode().equals(KeyCode.ENTER)) {
			Main.searchTerm = searchBar.getText();
			System.out.println("Hallo");
			Parent root = FXMLLoader.load(getClass().getResource("productsSearch.fxml"));
			Stage window = (Stage) goBackBt.getScene().getWindow();
			window.setScene(new Scene(root, 1920, 1080));

		}
	}
	
	public void initialize() throws IOException {
		searchBarBT(Main.searchTerm);
		productGrid.add(createGridSearch(sortieralgorithmus(funde)), 0, row);
	}
}
