import java.util.ArrayList;

public class Item {
	
	private int valor;
	private int valorTemp; //buffer 
	private Estado estado;
	private int nLeitores;
	
	private ArrayList<Transacao> tAtual;
	
	public Item(int valor) {
		this.valor = valor;
		this.valorTemp = valor;
		estado = Estado.UNLOCK;
		tAtual = new ArrayList<Transacao>();
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getValorTemp() {
		return this.valorTemp;
	}

	
	public void setValorTemp(int valorTemp) {
		this.valorTemp = valorTemp;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void addnLeitores() {
		nLeitores++;
	}
	
	public void subnLeitores() {
		nLeitores--;
	}
	
	public int getnLeitores() {
		return nLeitores;
	}

	public void setnLeitores(int nLeitores) {
		this.nLeitores = nLeitores;
	}
	

	public ArrayList<Transacao> gettAtual() {
		return tAtual;
	}



}
