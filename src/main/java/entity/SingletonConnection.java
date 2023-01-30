package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

	private final String dbURL = "jdbc:mysql://localhost:3306/UY1";
	private final String userName = "root";
	private final String password = "";
	private static Connection connection;

	public SingletonConnection() {
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

	public void closeConnection() {
		if (connection!=null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static Connection getConnection() {
		return connection;
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
