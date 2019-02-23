package conexao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao {

	private static final String USUARIO="root";
	private static final String SENHA="";
	private static final String URL="jdbc:mysql://localhost:3306/kevin3";
	private static final String Driver="org.gjt.mm.mysql.Driver";
	private static Connection conexao;
	
	
	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	 conexao = (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	/*
	
	public static void main(String[] args) {
		try {
	   conexao = Conexao.conectar();
		System.out.print("Conectado com sucesso");
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.print("falha na conexao BD");
		}
	}
	
	
	*/
	
}
