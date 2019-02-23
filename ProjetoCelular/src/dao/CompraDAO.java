package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import modelo.ModeloCompra;


public class CompraDAO {

	
	public void salvar(ModeloCompra c)throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into modelocompra ");
		sql.append("(data,celular,preco,estatos) ");
		sql.append("Values(?,?,?,?)");
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1,c.getData());
		comando.setString(2,c.getCelular());
		comando.setDouble(3,c.getPreco());
		comando.setString(4,c.getEstatos());
		
		comando.executeUpdate();
	}
	
			
			
			public void excluir(ModeloCompra c)throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("Delete from modelocompra ");
				sql.append("Where codigo=? ");
				
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setInt(1, c.getCodigo());
				comando.executeUpdate();
			}
			
			
			public void editar(ModeloCompra c)throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("Update modelocompra ");
				sql.append("Set data=?,celular=?,preco=?,estatos=?");
				sql.append("Where codigo=? ");
				
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
				comando.setInt(5, c.getCodigo());
				comando.setString(1,c.getData());
				comando.setString(2,c.getCelular());
				
				comando.setDouble(3,c.getPreco());
				comando.setString(4,c.getEstatos());
				
				
				comando.executeUpdate();
			}
			
			
			

			
			public ArrayList<ModeloCompra> listar()throws SQLException{
				
				StringBuilder sql = new StringBuilder();
				sql.append("Select data,codigo,celular,preco,estatos ");
				sql.append("From modelocompra ");
			   //sql.append("Order by celular asc ");//em ordem decrescente
				
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
				
				ResultSet resultado = comando.executeQuery();
				
				ArrayList<ModeloCompra>lista = new ArrayList<ModeloCompra>();
				
				while(resultado.next()) {
					ModeloCompra c = new ModeloCompra();
					c.setCodigo(resultado.getInt("codigo"));
					c.setData(resultado.getString("data"));
					c.setCelular(resultado.getString("celular"));
					c.setPreco(resultado.getDouble("preco"));
					c.setEstatos(resultado.getString("estatos"));
					
					lista.add(c);
				}
				return lista;
			}
         
			//Metodo main para testar metodos
			
			public static void main(String[] args) {
			
				
			/*	
				
				ModeloCompra c= new ModeloCompra();
				
				c.setData("01/05/2017");
				c.setCelular("Samsung Galaxy J7 ");
				c.setPreco(100.0);
				c.setEstatos("Pendente");
				CompraDAO cdao = new CompraDAO();
				try {
					cdao.salvar(c);
					System.out.print("Salvo com sucesso");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("erro ao salvar");
					e.printStackTrace();
				}
				
				*/
			
				/*
			   //execusao metodo excluir
			   
				ModeloCompra f1 = new ModeloCompra();
				f1.setCodigo(2);
				CompraDAO fdao = new CompraDAO();
				try {
					fdao.excluir(f1);
					System.out.print("Deletado com sucesso");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("erro ao excluir ");
					e.printStackTrace();
				}
				*/
				
				
	/*			
			
				
				 
				
				ModeloCompra f1 = new ModeloCompra();
				f1.setCodigo(1);
				
				f1.setCelular("Asus Zenfone");
				f1.setPreco(50.0);
				
				CompraDAO fdao = new CompraDAO();
				try {
					fdao.editar(f1);
					System.out.print("editado com sucesso");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("erro ao editar ");
					e.printStackTrace();
				}
				
		*/		
				
			
				
				try {
					
					CompraDAO cdao = new CompraDAO();
					ArrayList<ModeloCompra>lista = cdao.listar();
					
					
					for(ModeloCompra c: lista) {
					System.out.println("\nCodigo do produto: " + c.getCodigo());
					System.out.println("Data: " + c.getData());
					System.out.println("Celular: " + c.getCelular());
					System.out.println("Preço: " + c.getPreco());
					System.out.println("Estatos: " + c.getEstatos());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("erro ao buscar");
					e.printStackTrace();
				}
						
    }

}



