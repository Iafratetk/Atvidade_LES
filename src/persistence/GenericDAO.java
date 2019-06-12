package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Database=localdb;Data Source=127.0.0.1;User Id=azure;Password=6#vWHD_$
public class GenericDAO {

	private Connection con;

	public Connection getConnection() {

		try {
			//CONEXAO SQL SERVER
			/*
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			con = DriverManager
					.getConnection(
							"jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=atividadeLES;namedPipe=true",
							"lucasSQL", "lucas1996");
			*/				
			//CONEXAO MYSQL PARA O AZURE
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:55816/localdb?useTimezone=true&serverTimezone=UTC","azure","6#vWHD_$");
			
			System.out.println("Conexao ok");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void fechaConexao() {
		try {
			if (con != null)
				con.close();
			con = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}