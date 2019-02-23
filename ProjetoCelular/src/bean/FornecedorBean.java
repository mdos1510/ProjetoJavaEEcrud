package bean;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.JSUtil;
import dao.FornecedorDAO;
import modelo.ModeloFornecedor;


@ManagedBean(name ="MBFo")
@ViewScoped

public class FornecedorBean {


	private ModeloFornecedor fornecedor; 
	private ArrayList<ModeloFornecedor>itens;
	private ArrayList<ModeloFornecedor>itensFiltrados;
	
	public ModeloFornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(ModeloFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public ArrayList<ModeloFornecedor> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ModeloFornecedor> itens) {
		this.itens = itens;
	}
	public ArrayList<ModeloFornecedor> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(ArrayList<ModeloFornecedor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}	

@PostConstruct 
	
	
	public void prepararPesquisa() {
		
		  try {
			FornecedorDAO fdao = new FornecedorDAO();
			itens=fdao.listar();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSUtil.addMensagemErro("e.getMessage()");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



public void prepararNovo() throws SQLException {
	
	fornecedor = new ModeloFornecedor();
	FornecedorDAO fdao = new FornecedorDAO();
	
}


public void novo() {
	
	try {
		FornecedorDAO fdao = new FornecedorDAO();
		fdao.salvar(fornecedor);
		
		
		itens = fdao.listar();
					
		JSUtil.addMensagemSucesso("Dados salvos com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro( "ex.getMessage()");
		e.printStackTrace();
	}
}


public void excluir() {
	try {
		FornecedorDAO fdao = new FornecedorDAO();
		fdao.excluir(fornecedor);
		
		
		itens = fdao.listar();	
		
		JSUtil.addMensagemSucesso("Dados excluido com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro("Não é possivel excluir Dados que estejam associados em outras Tabelas");
		e.printStackTrace();
	}
}


public void editar() {
try {
     FornecedorDAO fdao = new FornecedorDAO();
	fdao.editar(fornecedor);
	
	
	itens = fdao.listar();
	
	JSUtil.addMensagemSucesso("Dados atualizado com sucesso");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	JSUtil.addMensagemErro("Erro ao editar Dados"+e);
	e.printStackTrace();
}
}

	
	
}
