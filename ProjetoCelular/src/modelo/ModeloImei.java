package modelo;

public class ModeloImei {

	private int codigo;
	private String vendedor;
	private String tel;
	private String imei1;
	private String imei2;
	private String imei3;
	private String imei4;
	private ModeloCompra comprai = new ModeloCompra();
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImei1() {
		return imei1;
	}
	public void setImei1(String imei1) {
		this.imei1 = imei1;
	}
	public String getImei2() {
		return imei2;
	}
	public void setImei2(String imei2) {
		this.imei2 = imei2;
	}
	public String getImei3() {
		return imei3;
	}
	public void setImei3(String imei3) {
		this.imei3 = imei3;
	}
	public String getImei4() {
		return imei4;
	}
	public void setImei4(String imei4) {
		this.imei4 = imei4;
	}
	public ModeloCompra getComprai() {
		return comprai;
	}
	public void setComprai(ModeloCompra comprai) {
		this.comprai = comprai;
	}
	
}
