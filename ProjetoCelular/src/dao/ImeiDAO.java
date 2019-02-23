package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import modelo.ModeloCompra;
import modelo.ModeloImei;



public class ImeiDAO {
	
	
		public void salvar(ModeloImei i)throws SQLException {

			StringBuilder sql = new StringBuilder();
			sql.append("insert into modeloimei ");
			sql.append("(vendedor,tel,imei1,imei2,imei3,imei4,comprai) ");
			sql.append("Values(?,?,?,?,?,?,?)");
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setString(1,i.getVendedor());
			comando.setString(2,i.getTel());
			comando.setString(3,i.getImei1());
			comando.setString(4,i.getImei2());
			comando.setString(5,i.getImei3());
			comando.setString(6,i.getImei4());
			comando.setInt(7, i.getComprai().getCodigo());
			
			
			
			comando.executeUpdate();
		}
		
		
			public ArrayList<ModeloImei> listar()throws SQLException{
				
				StringBuilder sql = new StringBuilder();
				sql.append("Select i.codigo,i.vendedor, i.tel,i.imei1,i.imei2,i.imei3,i.imei4, ci.codigo, ci.celular ");
				sql.append("From modeloimei i ");
				sql.append("INNER JOIN modelocompra ci ON ci.codigo = i.comprai  ");//em ordem decrescente
				
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
				
				ResultSet resultado = comando.executeQuery();
				
				ArrayList<ModeloImei>lista = new ArrayList<ModeloImei>();
				
				while(resultado.next()) {
					ModeloCompra ci= new ModeloCompra();
					ci.setCodigo(resultado.getInt("ci.codigo"));
					
					ci.setCelular(resultado.getString("ci.celular"));
					
					ModeloImei i = new ModeloImei();
					i.setCodigo(resultado.getInt("i.codigo"));
					i.setVendedor(resultado.getString("i.vendedor"));
					i.setTel(resultado.getString("i.tel"));
					i.setImei1(resultado.getString("i.imei1"));
					i.setImei2(resultado.getString("i.imei2"));
					i.setImei3(resultado.getString("i.imei3"));
					i.setImei4(resultado.getString("i.imei4"));
					i.setComprai(ci);//chave estrangeira
					
					lista.add(i);
				}
				return lista;
			}
		
		public void excluir(ModeloImei i)throws SQLException {
			StringBuilder sql = new StringBuilder();
			sql.append("Delete from modeloimei ");
			sql.append("Where codigo=? ");
			
			Connection conexao = Conexao.conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setInt(1, i.getCodigo());
			comando.executeUpdate();
		}
		
		
			public void editar(ModeloImei i)throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("Update modeloimei ");
				sql.append("Set vendedor=?, tel=?, imei1=?,imei2=?,imei3=?,imei4=?,comprai=? ");
				sql.append("Where codigo=?");
				
				Connection conexao = Conexao.conectar();
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
				comando.setInt(8, i.getCodigo());
				comando.setString(1,i.getVendedor());
				comando.setString(2,i.getTel());
				comando.setString(3,i.getImei1());
				comando.setString(4,i.getImei2());
				comando.setString(5,i.getImei3());
				comando.setString(6,i.getImei4());
				comando.setInt(7, i.getComprai().getCodigo());
				
				
				
				comando.executeUpdate();
			}
				

			
			
			//Metodo main para testar metodos
			
			public static void main(String[] args) {
			
				
				
				
				try {
					ModeloImei i = new ModeloImei();
				
				i.setVendedor("Renato Peres");	
				
				i.setTel("2454-7788");
				i.setImei1("453EE");
				i.setImei2("234GH");
				i.setImei3("#");
				i.setImei4("# ");
				
				//como passar info da chave estrangeira
				ModeloCompra ci = new ModeloCompra();
				ci.setCodigo(1);
				i.setComprai(ci);
				
				ImeiDAO idao = new ImeiDAO();
					idao.salvar(i);
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
				
				
				
				
		}
}
