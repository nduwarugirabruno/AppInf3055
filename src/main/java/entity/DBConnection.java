package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private final String dbURL = "jdbc:mysql://localhost:3306/TP3055";
	private final String userName = "root";
	private final String password = "";
	private Connection connection;

	public DBConnection() {
		System.out.println("\nConnexion à la base de donnée...");;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, userName, password);
			if (connection!=null) System.out.println("Connexion établie.\n");
			else System.out.println("Connexion échouée.\n");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		if (connection!=null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public String toString() {
		return "DBConnection:" +
				"\n\tURL = " + dbURL +
				"\n\tUserName = " + userName +
				"\n\tPassword = " + password +
				"\n\tConnection = " + connection;
	}

}
