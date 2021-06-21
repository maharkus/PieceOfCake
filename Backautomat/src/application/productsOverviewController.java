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
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
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
	private Text categoryText;

	@FXML
	private GridPane productGrid;

	@FXML
	private ScrollPane productWrap;

	@FXML
	private TextField searchBar;

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
			ResultSet results = database.getStatement()
					.executeQuery("SELECT * FROM produkte WHERE Kategorie='" + Main.selectedCat + "'");

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
	}

	public GridPane createGrid() throws IOException {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);

		for (int i = 0; i < pl.size(); i++) {
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/application/products.fxml"));
			grid.add(newLoadedPane, (i) % 2, Integer.valueOf((int) Math.floor((i) / 2)));

			// Change Product Image
			ImageView productImage = (ImageView) newLoadedPane.lookup("#productImage");
			File file = new File("res/product_images/" + pl.get(i).getId() + ".jpg");
			Image image = new Image(file.toURI().toString());
			productImage.setImage(image);

			// Amount Buttons

			// Add To Cart Handler
			Button BtAddToCart = (Button) newLoadedPane.lookup("#addToSc");
			int productID = i;
			Text productAmountText = (Text) newLoadedPane.lookup("#productAmount");
			int productAmount = Integer.parseInt(productAmountText.getText());
			BtAddToCart.setOnAction(
					event -> addToCart((ActionEvent) event, (int) pl.get(productID).getId(), (int) productAmount));

			// Change Product name
			Text productName = (Text) newLoadedPane.lookup("#productName");
			productName.setText(pl.get(i).getProduktname());

			// Change Product Price
			Text productPrice = (Text) newLoadedPane.lookup("#productPrice");
			Locale locale = Locale.GERMANY;
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
			String euroPrice = numberFormat.format(pl.get(i).getPreis());
			productPrice.setText(euroPrice);
		}
		;

		return grid;
	}

	private void addToCart(ActionEvent event, int index, int amount) {
		System.out.println(index + ", " + amount);
	}

	@FXML
	public void initialize() throws IOException {

		getProducts();
		productGrid.add(createGrid(), 0, row);
		categoryText.setText(Main.selectedCat);
	}

	public void keyPressesSubmitSearch(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			searchBarBT();
		}
	}

	public void searchBarBT() {
		String eingabe = searchBar.getText();
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
		ArrayList<Product> funde = new ArrayList<Product>();

		String suche = eingabe;

		for (Product item : pl) {
			if (item.getProduktname().toLowerCase().contains(suche.toLowerCase())) {
				funde.add(item);
			}
		}

		System.out.println(funde.toString());
	}

}
