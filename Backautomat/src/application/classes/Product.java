package application.classes;
//import com.mysql.cj.xdevapi.Statement;

public class Product {
	
	
	private int id;
	private String Produktname;
	private String Kategorie;
	private Double Preis;
	private String Produktbild;
	private int Stueckzahl;
	
	
	
	public int getBestand() {
		return Stueckzahl;
	}
	public void setBestand(int bestand) {
		Stueckzahl = bestand;
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
	
	public Product(String vorname, String nachname, Double preis, String produktbild, int stueckzahl) {
		this.Produktname = vorname;
		this.Kategorie = nachname;
		this.Preis = preis;
		this.Produktbild = produktbild;
		this.Stueckzahl = stueckzahl;
		
	}
}
