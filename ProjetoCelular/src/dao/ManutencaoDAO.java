package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import conexao.Conexao;
import modelo.ModeloCompra;
import modelo.ModeloFornecedor;
import modelo.ModeloManutencao;




public class ManutencaoDAO {
	
	
			public void salvar(ModeloManutencao m)throws SQLException {

				StringBuilder sql = new StringBuilder();
				sql.append("insert into modelomanutencao ");
				sql.append("(defeito,gastos,garantia,compra,fornecedor) ");
				sql.append("Values(?,?,?,?,?)");
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setString(1,m.getDefeito());
				comando.setDouble(2,m.getGastos());
				comando.setString(3,m.getGarantia());
				comando.setInt(4,m.getCompra().getCodigo());
				comando.setInt(5,m.getFornecedor().getCodigo());
				
				comando.executeUpdate();
			}

			
			
			public ArrayList<ModeloManutencao> listar()throws SQLException{
				
				StringBuilder sql = new StringBuilder();
				sql.append("Select m.codigo, m.defeito,m.gastos,m.garantia,cc.codigo,cc.celular,f.fornecedor ");
				sql.append("From modelomanutencao m ");
				sql.append("INNER JOIN modelocompra cc ON cc.codigo = m.compra ");//em ordem decrescente
				sql.append("INNER JOIN modelofornecedor f ON f.codigo = m.fornecedor ");
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
				
				ResultSet resultado = comando.executeQuery();
				
				ArrayList<ModeloManutencao>lista = new ArrayList<ModeloManutencao>();
				
				while(resultado.next()) {
					ModeloCompra cc = new ModeloCompra();
					cc.setCodigo(resultado.getInt("cc.codigo"));
					cc.setCelular(resultado.getString("cc.celular"));
					
					ModeloFornecedor f = new ModeloFornecedor();
					f.setFornecedor(resultado.getString("f.fornecedor"));
					
					ModeloManutencao m = new ModeloManutencao();
					m.setCodigo(resultado.getInt("m.codigo"));
					m.setDefeito(resultado.getString("m.defeito"));
					m.setGastos(resultado.getInt("m.gastos"));
					m.setGarantia(resultado.getString("m.garantia"));
					
					m.setCompra(cc);//chave estrangeira
					m.setFornecedor(f);
					
					lista.add(m);
				}
				return lista;
			}

			
			public void excluir(ModeloManutencao m)throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("Delete from modelomanutencao ");
				sql.append("Where codigo=? ");
				
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setInt(1, m.getCodigo());
				comando.executeUpdate();
			}
			
			
			
			public void editar(ModeloManutencao m)throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("Update modelomanutencao ");
				sql.append("Set defeito=?, gastos=?, garantia=?, compra=?, fornecedor=? ");
				sql.append("Where codigo=?");
				
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
				comando.setInt(6, m.getCodigo());
				comando.setString(1,m.getDefeito());
				comando.setDouble(2, m.getGastos());
				comando.setString(3,m.getGarantia());
				comando.setInt(4, m.getCompra().getCodigo());
				comando.setInt(5, m.getFornecedor().getCodigo());
				
				
				
				comando.executeUpdate();
			}
				
			
			
			
		
					public static void main(String[] args) throws SQLException {
						
                     /*
						/
						ModeloManutencao m = new ModeloManutencao();
						
						m.setDefeito("Tela");
						m.setGastos(50);
						
						//chave estrangeira
						ModeloCompra c = new ModeloCompra();
						c.setCodigo(1);
						m.setCompra(c);
						
						ManutencaoDAO mdao = new ManutencaoDAO();
						mdao.salvar(m);

						}
					*/
					
					
						
						try {
							
							ManutencaoDAO mdao = new ManutencaoDAO();
							ArrayList<ModeloManutencao>lista = mdao.listar();
							
							
							for(ModeloManutencao m: lista) {
							System.out.println("\nCodigo de servico: " + m.getCodigo());
							System.out.println("Defeito: " + m.getDefeito());
							System.out.println("Gastos: " + m.getGastos());
						
							System.out.println("cod da Compra: " +m.getCompra().getCodigo());
							System.out.println("Celular: " +m.getCompra().getCelular());
							System.out.println("Fornecedor: " +m.getFornecedor().getFornecedor());
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.print("erro ao buscar");
							e.printStackTrace();
						}
					
								
					
					/*	
					
						 
						
						ModeloManutencao m = new ModeloManutencao();
						m.setCodigo(1);
						m.setDefeito("Placa");
						
						m.setGastos(100.50);
						
						ModeloCompra cc = new ModeloCompra(); //alterar chave estrangeira
						cc.setCodigo(2);
						m.setCompra(cc);
						
						ManutencaoDAO mdao = new ManutencaoDAO();
						try {
							mdao.editar(m);
							System.out.print("editado com sucesso");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.print("erro ao editar ");
							e.printStackTrace();
						}	
						
						
						*/
						
					}		

}
