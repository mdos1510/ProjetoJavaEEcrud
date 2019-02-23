package modelo;

public class ModeloVenda {

	private int codigo;
	private String data;
	private String cliente;
	private String tel;
	private double preco;
	private ModeloCompra compra = new ModeloCompra();
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public ModeloCompra getCompra() {
		return compra;
	}
	public void setCompra(ModeloCompra compra) {
		this.compra = compra;
	}
	
	
	
	
	
	
	
	
}