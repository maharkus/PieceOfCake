package application.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	final String dbUrl ="jdbc:mysql://localhost:3306/PieceOfCake?autoReconnect=true&serverTimezone=UTC";
	final String dbUsername = "root";
	final String dbPassword = "";
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	public Connection getConnection() {
		return connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void createConnection() {
		try {
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.print(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
