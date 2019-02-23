package dao;

import java.sql.Connection;

//problema ao editar


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import conexao.Conexao;
import modelo.ModeloCompra;
import modelo.ModeloVenda;


public class VendaDAO {

	
	public void salvar(ModeloVenda v)throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into modelovenda ");
		sql.append("(data,cliente,tel,preco,compra) ");
		sql.append("Values(?,?,?,?,?)");
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1,v.getData());
		comando.setString(2,v.getCliente());
		comando.setString(3,v.getTel());
		comando.setDouble(4,v.getPreco());
		
		comando.setInt(5,v.getCompra().getCodigo());
		
		comando.executeUpdate();
	}
	
	
		public ArrayList<ModeloVenda> listar()throws SQLException{
			
			StringBuilder sql = new StringBuilder();
			sql.append("Select v.codigo,v.data, v.cliente,v.tel,v.preco, c.codigo, c.celular ");
			sql.append("From modelovenda v ");
			sql.append("INNER JOIN modelocompra c ON c.codigo = v.compra  ");//em ordem decrescente
			
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			
			ResultSet resultado = comando.executeQuery();
			
			ArrayList<ModeloVenda>lista = new ArrayList<ModeloVenda>();
			
			while(resultado.next()) {
				ModeloCompra c= new ModeloCompra();
				c.setCodigo(resultado.getInt("c.codigo"));
				
				c.setCelular(resultado.getString("c.celular"));
				
				ModeloVenda v = new ModeloVenda();
				v.setCodigo(resultado.getInt("v.codigo"));
				v.setData(resultado.getString("v.data"));
				v.setCliente(resultado.getString("v.cliente"));
				v.setTel(resultado.getString("v.tel"));
				v.setPreco(resultado.getDouble("v.preco"));
				v.setCompra(c);
				
				lista.add(v);
			}
			return lista;
		}
	
	public void excluir(ModeloVenda v)throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("Delete from modelovenda ");
		sql.append("Where codigo=? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, v.getCodigo());
		comando.executeUpdate();
	}
	
	
		public void editar(ModeloVenda v)throws SQLException {
			StringBuilder sql = new StringBuilder();
			sql.append("Update modelovenda ");
			sql.append("Set data=?,cliente=?,tel=?,preco=?,compra=? ");
			sql.append("Where codigo=?");
			
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setInt(6, v.getCodigo());
			comando.setString(1,v.getData());
			comando.setString(2,v.getCliente());
			comando.setString(3,v.getTel());
			comando.setDouble(4, v.getPreco());
			comando.setInt(5, v.getCompra().getCodigo());
			
			
			
			comando.executeUpdate();
		}
			

	
	
	public static void main(String[] args) {
	
		
		
		
		
		try {
			ModeloVenda v = new ModeloVenda();
		
		v.setData("02/05/2017");	
		v.setCliente("Tiago Vilela");
		v.setTel("7454-4788");
		v.setPreco(600);
		
		
		ModeloCompra cc = new ModeloCompra();
		cc.setCodigo(1);
		v.setCompra(cc);
		
		VendaDAO vdao = new VendaDAO();
			vdao.salvar(v);
			System.out.print("Salvo com sucesso");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("erro ao salvar");
			e.printStackTrace();
		}

		
			
		/*
		
		 
		try {
			
			VendaDAO fdao = new VendaDAO();
			ArrayList<ModeloVenda>lista = fdao.listar();
			
			
			for(ModeloVenda v: lista) {
			System.out.println("\nCodigo de venda: " + v.getCodigo());
			
			System.out.println("Cliente: " + v.getCliente());
			System.out.println("Preço: " + v.getPreco());
			System.out.println("cod da compra: " +v.getCompra().getCodigo());
			System.out.println("Descrição do celular: " +v.getCompra().getCelular());
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("erro ao buscar");
			e.printStackTrace();
		}
	  */
		/*
	  
	   
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
		
		
		
		
}
}