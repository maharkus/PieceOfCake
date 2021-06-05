package application.classes;
//import com.mysql.cj.xdevapi.Statement;

public class Person {
	
	
	private int id;
	private String vorname;
	private String nachname;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ");
		sb.append(getId());
		sb.append("\t Vorname:");
		sb.append(getVorname());
		sb.append("\t Nachname:");
		sb.append(getNachname());
		return sb.toString();
	}
	
	public Person() {};
	
	public Person(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
		
	}
}
