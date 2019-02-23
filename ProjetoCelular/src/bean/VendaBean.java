package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.VendaDAO;
import modelo.ModeloVenda;
import util.JSUtil;

@ManagedBean(name = "MBVenda")
@ViewScoped

public class VendaBean {

	
	private ModeloVenda venda;
	private ArrayList<ModeloVenda>itens;
	private ArrayList<ModeloVenda>itensFiltrados;
	
	
	public ModeloVenda getVenda() {
		return venda;
	}



	public void setVenda(ModeloVenda venda) {
		this.venda = venda;
	}



	public ArrayList<ModeloVenda> getItens() {
		return itens;
	}



	public void setItens(ArrayList<ModeloVenda> itens) {
		this.itens = itens;
	}



	public ArrayList<ModeloVenda> getItensFiltrados() {
		return itensFiltrados;
	}



	public void setItensFiltrados(ArrayList<ModeloVenda> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}


	
	
	
	
	
	
@PostConstruct 
	public void prepararPesquisa() {
		
		  try {
			VendaDAO vdao = new VendaDAO();
			itens=vdao.listar();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSUtil.addMensagemErro("e.getMessage()");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



public void prepararNovo() throws SQLException {
	
	venda = new ModeloVenda();
	VendaDAO vdao = new VendaDAO();
	
}


public void novo() {
	
	try {
		VendaDAO vdao = new VendaDAO();
		vdao.salvar(venda);
		
		
		itens = vdao.listar();
					
		JSUtil.addMensagemSucesso("Dados salvos com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro( "ex.getMessage()");
		e.printStackTrace();
	}
}


public void excluir() {
	try {
		VendaDAO vdao = new VendaDAO();
		vdao.excluir(venda);
		
		
		itens = vdao.listar();	
		
		JSUtil.addMensagemSucesso("Dados excluido com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro("Não é possivel excluir Dados que estejam associados em outras Tabelas");
		e.printStackTrace();
	}
}


public void editar() {
try {
	VendaDAO vdao = new VendaDAO();
	vdao.editar(venda);
	
	
	itens = vdao.listar();
	
	JSUtil.addMensagemSucesso("Dados atualizado com sucesso");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	JSUtil.addMensagemErro("Erro ao editar Dados"+e);
	e.printStackTrace();
}
}





}
