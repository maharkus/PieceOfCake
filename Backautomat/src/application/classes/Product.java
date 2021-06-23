package application.classes;
//import com.mysql.cj.xdevapi.Statement;

public class Product {
	
	
	protected int id;
	protected String Produktname;
	protected String Kategorie;
	protected Double Preis;
	private String Produktbild;
	protected int Bestand;
	private String Angebottext;
	
	
	
	
	public String getAngebottext() {
		return Angebottext;
	}
	public void setAngebottext(String angebottext) {
		Angebottext = angebottext;
	}
	public int getBestand() {
		return Bestand;
	}
	public void setBestand(int bestand) {
		Bestand = bestand;
	}
	public Double getPreis() {
		return Preis;
	}
	public String getProduktbild() {
		return Produktbild;
	}
	public void setPreis(Double preis) {
		Preis = preis;
	}
	public void setProduktbild(String produktbild) {
		Produktbild = produktbild;
	}
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
		sb.append("\t Produktname: ");
		sb.append(getProduktname());
		sb.append("\t Preis: ");
		sb.append(getPreis());
		return sb.toString();
	}
	
	public Product() {};
	
	public Product(int id, String productname, String category, Double preis, String produktbild, int bestand) {
		this.id = id;
		this.Produktname = productname;
		this.Kategorie = category;
		this.Preis = preis;
		this.Produktbild = produktbild;
		this.Bestand = bestand;
		
	}

public Product(int id, String productname, String category, Double preis, String produktbild, int bestand, String angebottext) {
	this.id = id;
	this.Produktname = productname;
	this.Kategorie = category;
	this.Preis = preis;
	this.Produktbild = produktbild;
	this.Bestand = bestand;
	this.Angebottext= angebottext;
	
}
}