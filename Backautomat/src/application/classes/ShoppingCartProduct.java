package application.classes;

public class ShoppingCartProduct extends Product {
	

	private int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ShoppingCartProduct(Product product, int amount) {
		this.id = product.getId();
		this.Produktname = product.getProduktname();
		this.Kategorie = product.getKategorie();
		this.Preis = product.getPreis();
		this.amount = amount;
	}
}
