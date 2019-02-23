package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import modelo.ModeloFornecedor;

public class FornecedorDAO {

	
		public void salvar(ModeloFornecedor f)throws SQLException {

			StringBuilder sql = new StringBuilder();
			sql.append("insert into modelofornecedor ");
			sql.append("(fornecedor,produto,tel,end) ");
			sql.append("Values(?,?,?,?)");
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setString(1,f.getFornecedor());
			comando.setString(2,f.getProduto());
			comando.setString(3,f.getTel());
			comando.setString(4,f.getEnd());
			
			comando.executeUpdate();
		}
		
				
				
				public void excluir(ModeloFornecedor f)throws SQLException {
					StringBuilder sql = new StringBuilder();
					sql.append("Delete from modelofornecedor ");
					sql.append("Where codigo=? ");
					
					Connection conexao = Conexao.conectar();
					PreparedStatement comando = conexao.prepareStatement(sql.toString());
					comando.setInt(1, f.getCodigo());
					comando.executeUpdate();
				}
				
				
				public void editar(ModeloFornecedor f)throws SQLException {
					StringBuilder sql = new StringBuilder();
					sql.append("Update modelofornecedor ");
					sql.append("Set fornecedor=?,produto=?,tel=?,end=?");
					sql.append("Where codigo=? ");
					
					Connection conexao = Conexao.conectar();
					PreparedStatement comando = conexao.prepareStatement(sql.toString());
					
					comando.setInt(5, f.getCodigo());
					comando.setString(1,f.getFornecedor());
					comando.setString(2,f.getProduto());
					
					comando.setString(3,f.getTel());
					comando.setString(4,f.getEnd());
					
					
					comando.executeUpdate();
				}
				
				
				

				
				public ArrayList<ModeloFornecedor> listar()throws SQLException{
					
					StringBuilder sql = new StringBuilder();
					sql.append("Select codigo,fornecedor,produto,tel,end ");
					sql.append("From modelofornecedor ");
				   //sql.append("Order by celular asc ");//em ordem decrescente
					
					Connection conexao = Conexao.conectar();
					PreparedStatement comando = conexao.prepareStatement(sql.toString());
					
					
					ResultSet resultado = comando.executeQuery();
					
					ArrayList<ModeloFornecedor>lista = new ArrayList<ModeloFornecedor>();
					
					while(resultado.next()) {
						ModeloFornecedor f = new ModeloFornecedor();
						f.setCodigo(resultado.getInt("codigo"));
						f.setFornecedor(resultado.getString("fornecedor"));
						f.setProduto(resultado.getString("produto"));
						f.setTel(resultado.getString("tel"));
						f.setEnd(resultado.getString("end"));
						
						lista.add(f);
					}
					return lista;
				}
}
