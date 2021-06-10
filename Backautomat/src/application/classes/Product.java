package application.classes;
//import com.mysql.cj.xdevapi.Statement;

public class Product {
	
	
	private int id;
	private String Produktname;
	private String Kategorie;
	
	public String getProduktname() {
		return Produktname;
	}
	public void setProduktname(String produktname) {
		Produktname = produktname;
	}
	public String getKategorie() {
		return Kategorie;
	}
	public void setKategorie(String kategorie) {
		Kategorie = kategorie;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ");
		sb.append(getId());
		sb.append("\t Produktname:");
		sb.append(getProduktname());
		sb.append("\t Kategorie:");
		sb.append(getKategorie());
		return sb.toString();
	}
	
	public Product() {};
	
	public Product(String vorname, String nachname) {
		this.Produktname = vorname;
		this.Kategorie = nachname;
		
	}
}
