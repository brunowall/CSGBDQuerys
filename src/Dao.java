import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

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
}
