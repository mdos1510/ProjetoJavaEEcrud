package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ImeiDAO;
import modelo.ModeloImei;
import util.JSUtil;

@ManagedBean(name = "MBMe")
@ViewScoped

public class ImeiBean {

	private ModeloImei me;
	private ArrayList<ModeloImei>itens;
	private ArrayList<ModeloImei>itensFiltrados;
	
	public ModeloImei getMe() {
		return me;
	}



	public void setMe(ModeloImei me) {
		this.me = me;
	}



	public ArrayList<ModeloImei> getItens() {
		return itens;
	}



	public void setItens(ArrayList<ModeloImei> itens) {
		this.itens = itens;
	}



	public ArrayList<ModeloImei> getItensFiltrados() {
		return itensFiltrados;
	}



	public void setItensFiltrados(ArrayList<ModeloImei> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}


	
	
	
@PostConstruct 
	public void prepararPesquisa() {
		
		  try {
			ImeiDAO idao = new ImeiDAO();
			itens=idao.listar();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSUtil.addMensagemErro("e.getMessage()");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



public void prepararNovo() throws SQLException {
	
	me = new ModeloImei();
	ImeiDAO idao = new ImeiDAO();
	
}


public void novo() {
	
	try {
		ImeiDAO idao = new ImeiDAO();
		idao.salvar(me);
		
		itens = idao.listar();
					
		JSUtil.addMensagemSucesso("Dados salvos com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro( "ex.getMessage()");
		e.printStackTrace();
	}
}


public void excluir() {
	try {
		ImeiDAO idao = new ImeiDAO();
		idao.excluir(me);
		
	
		itens = idao.listar();	
		
		JSUtil.addMensagemSucesso("Dados excluido com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro("Não é possivel excluir Dados que estejam associados em outras Tabelas");
		e.printStackTrace();
	}
}


public void editar() {
try {
     ImeiDAO idao = new ImeiDAO();
	idao.editar(me);
	
	
	itens = idao.listar();
	
	JSUtil.addMensagemSucesso("Dados atualizado com sucesso");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	JSUtil.addMensagemErro("Erro ao editar Dados"+e);
	e.printStackTrace();
}
}


	
	
}
