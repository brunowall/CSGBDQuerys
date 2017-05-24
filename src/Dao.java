import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class Dao {
	private  Connection connection;
	
	public Dao(boolean temindice) throws SQLException{
		connection = ConnectionFactory.getConnection();
		if(temindice){
			this.connection.prepareStatement("set enable_bitmapscan=off;").execute();
			this.connection.prepareStatement("set enable_seqscan=off;").execute();
		}
		
	}
	
	public long executa() throws SQLException{ // retorna o tempo de execucao da consulta
		
		PreparedStatement smt = this.connection.prepareStatement("select * from DOCENTES_NORDESTE where nu_mes = ?");
		smt.setString(1, "10");
		long inicial = System.currentTimeMillis();
		smt.executeQuery();
		long t_final  = System.currentTimeMillis();
		return (t_final-inicial);
	
	}

	public String getPlano() throws SQLException{
		
		
		PreparedStatement smt=this.connection.prepareStatement("explain select * from DOCENTES_NORDESTE where nu_mes = ?");
		smt.setString(1, "10");
		ResultSet rs = smt.executeQuery();
		
		rs.next();
		return rs.getString(1);
	}
	
	public static void clearCache() {
		Scanner sc = new Scanner(System.in);
		Runtime run = Runtime.getRuntime();
		System.out.println("Digite a senha do root do seu sistema operacional");
		String pass = sc.nextLine();	// senha do root para fazer a limpeza do cache
		try {
			run.exec("sudo su \r\n"+pass+"\r\n echo 3 > /proc/sys/vm/drop_caches \r\n service postgresql restart");
		} catch (IOException e) {
			System.err.println("Erro ao Limpar as Caches");
		}
		
	}
}
