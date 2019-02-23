package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import conexao.Conexao;
import modelo.ModeloServico;

public class ServicoDAO {
	
	
		public void salvar(ModeloServico s)throws SQLException {

			StringBuilder sql = new StringBuilder();
			sql.append("insert into modeloservico ");
			sql.append("(data,cliente,tel,celular,preco,estatos) ");
			sql.append("Values(?,?,?,?,?,?)");
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setString(1,s.getData());
			comando.setString(2,s.getCliente());
			comando.setString(3,s.getTel());
			comando.setString(4,s.getCelular());
			comando.setDouble(5,s.getPreco());
			comando.setString(6,s.getEstatos());
			
			
			comando.executeUpdate();
		}
		
		
		public void excluir(ModeloServico s)throws SQLException {
			StringBuilder sql = new StringBuilder();
			sql.append("Delete from modeloservico ");
			sql.append("Where codigo=? ");
			
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setInt(1, s.getCodigo());
			comando.executeUpdate();
		}
		
		
				public void editar(ModeloServico s)throws SQLException {
					StringBuilder sql = new StringBuilder();
					sql.append("Update modeloservico ");
					sql.append("Set data=?,cliente=?,tel=?, celular=?,preco=?,estatos=? ");
					sql.append("Where codigo=? ");
					
					Connection conexao = Conexao.conectar();
					PreparedStatement comando = conexao.prepareStatement(sql.toString());
					comando.setInt(7, s.getCodigo());
					comando.setString(1,s.getData());
					comando.setString(2,s.getCliente());
					comando.setString(3,s.getTel());
					comando.setString(4,s.getCelular());
					comando.setDouble(5,s.getPreco());
					comando.setString(6,s.getEstatos());
					
					comando.executeUpdate();
				}
		
		
		
		public ArrayList<ModeloServico> listar()throws SQLException{
			
			StringBuilder sql = new StringBuilder();
			sql.append("Select codigo,data, cliente, tel,celular,preco, estatos ");
			sql.append("From modeloservico ");
		//	sql.append("Order by codigo desc ");//em ordem decrescente
			
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			
			ResultSet resultado = comando.executeQuery();
			
			ArrayList<ModeloServico>lista = new ArrayList<ModeloServico>();
			
			while(resultado.next()) {
				ModeloServico s = new ModeloServico();
				s.setCodigo(resultado.getInt("codigo"));
				s.setData(resultado.getString("data"));
				s.setCliente(resultado.getString("cliente"));
				s.setTel(resultado.getString("tel"));
				s.setCelular(resultado.getString("celular"));
				s.setPreco(resultado.getDouble("preco"));
				s.setEstatos(resultado.getString("estatos"));
				
				
				lista.add(s);
			}
			return lista;
		}

		
		
		public static void main(String[] args) {
		
			
		/*	
			
			ModeloServico s = new ModeloServico();
			s.setCliente("Paulo Fernandes");
			s.setCelular("Moto G 5S");
			s.setPreco(180);
			
			ServicoDAO sdao = new ServicoDAO();
			try {
				sdao.salvar(s);
				System.out.print("Salvo com sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("erro ao salvar");
				e.printStackTrace();
			}}
			/*
			
		/*
		   
		   
			ModeloServico s = new ModeloServico();
			f1.setCodigo(16);
			ServicoDAO sdao = new ServicoDAO();
			try {
				fdao.excluir(f1);
				System.out.print("Deletado com sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("erro ao excluir ");
				e.printStackTrace();
			}
			*/
			
			
			
			 
			
			ModeloServico s = new ModeloServico();
			s.setCodigo(2);
			s.setCliente("Edna Matos");
			s.setCelular("Samsung Galaxy S8");
			s.setPreco(290);
			
			ServicoDAO sdao = new ServicoDAO();
			try {
				sdao.editar(s);
				System.out.print("editado com sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("erro ao editar ");
				e.printStackTrace();
			}
		}
		/*	
		
			ModeloServico s = new ModeloServico();;
			
			s.setCodigo(4);
			
			ServicoDAO sdao = new ServicoDAO();
			
			try {
				sdao.buscar(f1);
				System.out.print("Resultado: " + f1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("erro ao buscar");
				e.printStackTrace();
			}
			*/
			
			/*

		     
			try {
				
				ServicoDAO sdao = new ServicoDAO();
				ArrayList<ModeloServico>lista = sdao.listar();
				
				
				for(ModeloServico s: lista) {
				System.out.println("\nOrdem de serviço: " + s.getCodigo());
				System.out.println("Cliente: " + s.getCliente());
				System.out.println("Celular: " + s.getCelular());
				System.out.println("Preço: " + s.getPreco());
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("erro ao buscar");
				e.printStackTrace();
			}
		}}
			*/
		}