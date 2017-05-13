import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
	
	
	public static void main(String[] args) throws SQLException {
		
		Dao dao;
		System.out.println("digite 0 para consulta com indice e 1 para consulta sem indice");
		Scanner sc = new Scanner(System.in);
		int valor = sc.nextInt();
		switch (valor) {
		case 0:
			dao = new Dao(true);
			System.out.println("O tempo de execucao de consulta com indice: "+dao.executa()+" millisegundos");
			System.out.println(dao.getPlano());
		break;
		case 1:
		 dao =  new Dao(false);
		 System.out.println("O tempo de execucao de consulta sem indice: "+dao.executa()+" millisegundos");
		 System.out.println(dao.getPlano());
		default:
			System.out.println("Opcao invalida, encerrando a execucao");
			System.exit(1);
		}
		
	}
}
