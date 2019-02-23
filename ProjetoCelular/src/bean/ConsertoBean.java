package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import dao.ConsertoDAO;
import dao.FornecedorDAO;
import modelo.ModeloConserto;
import modelo.ModeloFornecedor;
import util.JSUtil;

@ManagedBean(name ="MBConserto")
@ViewScoped
public class ConsertoBean {

	private ModeloConserto conserto; 
	private ArrayList<ModeloConserto>itens;
	private ArrayList<ModeloConserto>itensFiltrados;
	private ArrayList<ModeloFornecedor>comboFornecedores;
	
	public ArrayList<ModeloFornecedor> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<ModeloFornecedor> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public ModeloConserto getConserto() {
		return conserto;
	}

	public void setConserto(ModeloConserto conserto) {
		this.conserto = conserto;
	}

	public ArrayList<ModeloConserto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ModeloConserto> itens) {
		this.itens = itens;
	}

	public ArrayList<ModeloConserto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<ModeloConserto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	
	
	


	
	
	
	
	
	@PostConstruct //faz iniciar um evento como mostrar uma lista sem que precisse usar botoes
	
	//Metodos que ligam java ao xhtml
	   
//prepara Pesquisa
	public void prepararPesquisa() {
		
		  try {
			ConsertoDAO csdao = new ConsertoDAO();
			itens=csdao.listar();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSUtil.addMensagemErro("ex.getMessage()");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void prepararNovo() {
		conserto = new ModeloConserto();
		FornecedorDAO dao = new FornecedorDAO();
		try {
			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void novo() {
		
		try {
			ConsertoDAO csdao = new ConsertoDAO();
			csdao.salvar(conserto);
			
			//comando para atualizar pagina automaticamente
			itens = csdao.listar();
						
			JSUtil.addMensagemSucesso("Dados salvos com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JSUtil.addMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	

	
public void excluir() {
	try {
	ConsertoDAO csdao = new ConsertoDAO();
		csdao.excluir(conserto);
		
		//comando para atualizar pagina automaticamente
		itens = csdao.listar();	
		
		JSUtil.addMensagemSucesso("Dados excluidos com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro("Não é possivel excluir Dados que estejam associados em outras Tabelas");
		e.printStackTrace();
	}
}



public void editar() {
try {
	ConsertoDAO csdao = new ConsertoDAO();
	csdao.editar(conserto);
	
	//comando para atualizar pagina automaticamente
	itens = csdao.listar();
	
	JSUtil.addMensagemSucesso("Dados atualizado com sucesso");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	JSUtil.addMensagemErro("Erro ao editar Dados"+e);
	e.printStackTrace();
}
}

public void prepararEditar() {
	
	try {
		conserto = new ModeloConserto();
		FornecedorDAO dao = new FornecedorDAO();
		comboFornecedores = dao.listar();
	} catch (SQLException e) {
		JSUtil.addMensagemErro("ex.getMessage()");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



}
