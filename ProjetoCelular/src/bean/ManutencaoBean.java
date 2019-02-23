package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import dao.FornecedorDAO;
import dao.ManutencaoDAO;

import modelo.ModeloFornecedor;
import modelo.ModeloManutencao;
import util.JSUtil;

@ManagedBean(name ="MBManu")
@ViewScoped
public class ManutencaoBean {

	private ModeloManutencao manu; 
	private ArrayList<ModeloManutencao>itens;
	private ArrayList<ModeloManutencao>itensFiltrados;
	private ArrayList<ModeloFornecedor>comboFornecedores;
	
	public ArrayList<ModeloFornecedor> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<ModeloFornecedor> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public ModeloManutencao getManu() {
		return manu;
	}

	public void setManu(ModeloManutencao manu) {
		this.manu = manu;
	}

	public ArrayList<ModeloManutencao> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ModeloManutencao> itens) {
		this.itens = itens;
	}

	public ArrayList<ModeloManutencao> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<ModeloManutencao> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	
	
	
	@PostConstruct 
	public void prepararPesquisa() {
		
		  try {
			ManutencaoDAO mdao = new ManutencaoDAO();
			itens=mdao.listar();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSUtil.addMensagemErro("ex.getMessage()");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void prepararNovo() {
		manu = new ModeloManutencao();
		
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
			ManutencaoDAO mdao = new ManutencaoDAO();
			mdao.salvar(manu);
			
			
			itens = mdao.listar();
						
			JSUtil.addMensagemSucesso("Dados salvos com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JSUtil.addMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	

	
public void excluir() {
	try {
		ManutencaoDAO mdao = new  ManutencaoDAO();
		mdao.excluir(manu);
		
		
		itens = mdao.listar();	
		
		JSUtil.addMensagemSucesso("Dados excluidos com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro("Não é possivel excluir Dados que estejam associados em outras Tabelas");
		e.printStackTrace();
	}
}



public void prepararEditar() {
	
	try {
		manu = new ModeloManutencao();
		FornecedorDAO dao = new FornecedorDAO();
		comboFornecedores = dao.listar();
	} catch (SQLException e) {
		JSUtil.addMensagemErro("ex.getMessage()");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void editar() {
try {
	ManutencaoDAO mdao = new ManutencaoDAO();
	mdao.editar(manu);
	
	
	itens = mdao.listar();
	
	JSUtil.addMensagemSucesso("Dados atualizado com sucesso");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	JSUtil.addMensagemErro("Erro ao editar Dados"+e);
	e.printStackTrace();
}
}



}
