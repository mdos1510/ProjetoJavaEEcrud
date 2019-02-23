package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import conexao.Conexao;
import modelo.ModeloConserto;
import modelo.ModeloFornecedor;
import modelo.ModeloServico;





public class ConsertoDAO {

	
		public void salvar(ModeloConserto cs)throws SQLException {

			StringBuilder sql = new StringBuilder();
			sql.append("insert into modeloconserto ");
			sql.append("(defeito,gastos,garantia,servico,fornecedor) ");
			sql.append("Values(?,?,?,?,?)");
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1,cs.getDefeito());
			comando.setDouble(2,cs.getGastos());
			comando.setString(3,cs.getGarantia());
			comando.setInt(4,cs.getServico().getCodigo());
			comando.setInt(5,cs.getFornecedor().getCodigo());
			comando.executeUpdate();
		}
		
		

		
		public ArrayList<ModeloConserto> listar()throws SQLException{
			
			StringBuilder sql = new StringBuilder();
			sql.append("Select cs.codigo, cs.defeito,cs.gastos,cs.garantia,ss.codigo,ss.cliente,ss.celular,f.fornecedor ");
			sql.append("From modeloconserto cs ");
			sql.append("INNER JOIN modeloservico ss ON ss.codigo = cs.servico ");
			sql.append("INNER JOIN modelofornecedor f ON f.codigo = cs.fornecedor ");//em ordem decrescente
			
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
			ResultSet resultado = comando.executeQuery();
			
			ArrayList<ModeloConserto>lista = new ArrayList<ModeloConserto>();
			
			while(resultado.next()) {
				ModeloFornecedor f = new ModeloFornecedor();
			//	f.setCodigo(resultado.getInt("f.codigo"));
				f.setFornecedor(resultado.getString("f.fornecedor"));
				
				ModeloServico ss = new ModeloServico();
				ss.setCodigo(resultado.getInt("ss.codigo"));
				ss.setCliente(resultado.getString("ss.cliente"));
				ss.setCelular(resultado.getString("ss.celular"));
				
				ModeloConserto cs = new ModeloConserto();
				cs.setCodigo(resultado.getInt("cs.codigo"));
				cs.setDefeito(resultado.getString("cs.defeito"));
				cs.setGastos(resultado.getInt("cs.gastos"));
				cs.setGarantia(resultado.getString("cs.garantia"));
				cs.setServico(ss);
				cs.setFornecedor(f);
				
				lista.add(cs);
			}
			return lista;
		}
				
		
				
				public void excluir(ModeloConserto cs)throws SQLException {
					StringBuilder sql = new StringBuilder();
					sql.append("Delete from modeloconserto ");
					sql.append("Where codigo=? ");
					
					Connection conexao = Conexao.conectar();
					PreparedStatement comando = conexao.prepareStatement(sql.toString());
					comando.setInt(1, cs.getCodigo());
					comando.executeUpdate();
				}
		
		
				
				public void editar(ModeloConserto cs)throws SQLException {
					StringBuilder sql = new StringBuilder();
					sql.append("Update modeloconserto ");
					sql.append("Set defeito=?, gastos=?,garantia=?, servico=?, fornecedor=? ");
					sql.append("Where codigo=?");
					
					Connection conexao = Conexao.conectar();
					PreparedStatement comando = conexao.prepareStatement(sql.toString());
					
					comando.setInt(6, cs.getCodigo());
					comando.setString(1,cs.getDefeito());
					comando.setDouble(2, cs.getGastos());
					comando.setString(3,cs.getGarantia());
					comando.setInt(4, cs.getServico().getCodigo());
					comando.setInt(5, cs.getFornecedor().getCodigo());
					
					
					comando.executeUpdate();
				}
					
		
		
		
		

		public static void main(String[] args) throws SQLException {
		/*	
      
			
			
			ModeloConserto cs = new ModeloConserto();
			
			cs.setDefeito("AA");
			cs.setGastos(100);
			cs.setGarantia("90 dias");
			//chave estrangeira
			ModeloServico s = new ModeloServico();
			s.setCodigo(4);
			cs.setServico(s);
			
			ModeloFornecedor f = new ModeloFornecedor();
			f.setCodigo(3);
			cs.setFornecedor(f);
			
			ConsertoDAO csdao = new ConsertoDAO();
			csdao.salvar(cs);

			
		*/
		
			
		
		try {
			
			ConsertoDAO fdao = new ConsertoDAO();
			ArrayList<ModeloConserto>lista = fdao.listar();
			
			
			for(ModeloConserto cs: lista) {
			System.out.println("\nCodigo de servico: " + cs.getCodigo());
			System.out.println("Defeito: " + cs.getDefeito());
			System.out.println("Gastos: " + cs.getGastos());
		    System.out.println("Garantia: " + cs.getGarantia());
		    
			System.out.println("cod do Cliente: " +cs.getServico().getCodigo());
			System.out.println("Celular: " +cs.getServico().getCelular());
		//	System.out.println("Cliente: " +cs.getServico().getCliente());
			
			//System.out.println("cod do fornecedor: " +cs.getFornecedor().getCodigo());
			System.out.println("Fornecedor: " +cs.getFornecedor().getFornecedor());
			
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("erro ao buscar");
			e.printStackTrace();
		}
	
				
		/*
			 
			 
					
			ModeloConserto cs= new ModeloConserto();
			cs.setCodigo(1);
			cs.setDefeito("Bateria");
			
			cs.setGastos(4.50);
			
			ModeloServico ss = new ModeloServico(); //alterar chave estrangeira
			ss.setCodigo(1);
			cs.setServico(ss);
			
			ConsertoDAO csdao = new ConsertoDAO();
			try {
				csdao.editar(cs);
				
				System.out.print("editado com sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("erro ao editar ");
				e.printStackTrace();
			}
				
			
			*/
		}	
}
