import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql:Censo_Edu", "postgres", "admin");
			return connection;
		} catch (ClassNotFoundException e) {
			System.err.println("Erro classe nao encontrada");
			return null;
		
		} catch (SQLException e) {
			System.err.println("Erro Na conexao com o db");
			return null;
		}
		
	}
	
	
}
