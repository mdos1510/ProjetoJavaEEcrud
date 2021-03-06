package modelo;

public class ModeloConserto {
	private int codigo;
	private String defeito;
	private double gastos;
	private String garantia;
	private ModeloServico servico = new ModeloServico();
	private ModeloFornecedor fornecedor = new ModeloFornecedor();
	
	
	public ModeloFornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(ModeloFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getGarantia() {
		return garantia;
	}
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	public double getGastos() {
		return gastos;
	}
	public void setGastos(double gastos) {
		this.gastos = gastos;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDefeito() {
		return defeito;
	}
	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	public ModeloServico getServico() {
		return servico;
	}
	public void setServico(ModeloServico servico) {
		this.servico = servico;
	}
	

}
