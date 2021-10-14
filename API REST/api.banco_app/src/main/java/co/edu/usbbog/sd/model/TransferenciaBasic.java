package co.edu.usbbog.sd.model;

public class TransferenciaBasic {
	
	private String cuentaApp,cuentaOrigen, cuentaDestino;
	private double valor;

	public TransferenciaBasic(String cuentaApp, String cuentaOrigen, String cuentaDestino, double valor) {
		super();
		this.cuentaApp = cuentaApp;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.valor = valor;
	}

	public String getCuentaApp() {
		return cuentaApp;
	}

	public void setCuentaApp(String cuentaApp) {
		this.cuentaApp = cuentaApp;
	}

	public String getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public String getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	

}
