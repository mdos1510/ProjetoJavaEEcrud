package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.CompraDAO;
import modelo.ModeloCompra;
import util.JSUtil;

@ManagedBean(name ="MBCompra")
@ViewScoped
public class CompraBean {

	private ModeloCompra compra;
    private ArrayList<ModeloCompra>itens;
	private ArrayList<ModeloCompra>itensFiltrados;
	
	public ModeloCompra getCompra() {
		return compra;
	}

	public void setCompra(ModeloCompra compra) {
		this.compra = compra;
	}

	public ArrayList<ModeloCompra> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ModeloCompra> itens) {
		this.itens = itens;
	}

	public ArrayList<ModeloCompra> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<ModeloCompra> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	
	@PostConstruct //faz iniciar um evento como mostrar uma lista sem que precisse usar botoes
	
	public void prepararPesquisa() {
		
		  try {
			CompraDAO cdao = new CompraDAO();
			itens=cdao.listar();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSUtil.addMensagemErro("ex.getMessage()");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void prepararNovo() {
		compra = new ModeloCompra();
	 }

	public void novo() {
		
		try {
			CompraDAO cdao = new CompraDAO();
			cdao.salvar(compra);
			
			
			itens = cdao.listar();
						
			JSUtil.addMensagemSucesso("Dados salvos com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JSUtil.addMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
		
public void excluir() {
	try {
		CompraDAO cdao = new CompraDAO();
		cdao.excluir(compra);
		
		
		itens = cdao.listar();	
		
		JSUtil.addMensagemSucesso("Dados excluidos com sucesso");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JSUtil.addMensagemErro("Não é possivel excluir Dados que estejam associados em outras Tabelas");
		e.printStackTrace();
	}
}

public void editar() {
try {
	CompraDAO cdao = new CompraDAO();
	cdao.editar(compra);
	
	
	itens = cdao.listar();
	
	JSUtil.addMensagemSucesso("Dados atualizado com sucesso");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	JSUtil.addMensagemErro("Não é possivel editar dados que tenha produto associado"+"ex.getMessage()");
	e.printStackTrace();
}
}
	
}
