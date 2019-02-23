package bean;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import util.JSUtil;
import dao.ServicoDAO;
import modelo.ModeloServico;

@ManagedBean(name ="MBServico")//faz referencia a classe
@ViewScoped


public class ServicoBean {


		private ModeloServico servico; 
		private ArrayList<ModeloServico>itens;
		private ArrayList<ModeloServico>itensFiltrados;
		
		public ModeloServico getServico() {
			return servico;
		}

		public void setServico(ModeloServico servico) {
			this.servico = servico;
		}

		public ArrayList<ModeloServico> getItens() {
			return itens;
		}

		public void setItens(ArrayList<ModeloServico> itens) {
			this.itens = itens;
		}

		public ArrayList<ModeloServico> getItensFiltrados() {
			return itensFiltrados;
		}

		public void setItensFiltrados(ArrayList<ModeloServico> itensFiltrados) {
			this.itensFiltrados = itensFiltrados;
		}
	
		
		
		@PostConstruct 
		public void prepararPesquisa() {
			
			  try {
				ServicoDAO sdao = new ServicoDAO();
				itens=sdao.listar();	
				
			} catch (SQLException e) {
				e.printStackTrace();
				JSUtil.addMensagemErro("ex.getMessage()");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void prepararNovo() {
			servico = new ModeloServico();
		}

		public void novo() {
			
			try {
				ServicoDAO sdao = new ServicoDAO();
				sdao.salvar(servico);
				
				
				itens = sdao.listar();
							
				JSUtil.addMensagemSucesso("Dados salvos com sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JSUtil.addMensagemErro("ex.getMessage()");
				e.printStackTrace();
			}
		}
		

		
	public void excluir() {
		try {
			ServicoDAO sdao = new ServicoDAO();
			sdao.excluir(servico);
			
			
			itens = sdao.listar();	
			
			JSUtil.addMensagemSucesso("Dados excluidos com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JSUtil.addMensagemErro("Não é possivel excluir Dados que estejam associados em outras Tabelas");
			e.printStackTrace();
		}
	}



	public void editar() {
	try {
		ServicoDAO sdao = new ServicoDAO();
		sdao.editar(servico);
		
		
		itens = sdao.listar();
		
		JSUtil.addMensagemSucesso("Dados atualizado com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro("Não é possivel editar fornecedor que tenha produto associado"+"ex.getMessage()");
		e.printStackTrace();
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
