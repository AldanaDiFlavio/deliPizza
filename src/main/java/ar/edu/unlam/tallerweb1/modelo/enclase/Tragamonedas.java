package ar.edu.unlam.tallerweb1.modelo.enclase;

public class Tragamonedas {
	private Tambor t1;
	private Tambor t2;
	private Tambor t3;

	public Tragamonedas(Tambor t1, Tambor t2, Tambor t3) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}

	public void activar() {
		this.t1.girar();
		this.t2.girar();
		this.t3.girar();
	}

	public boolean entregaPremio() {
		if (t1.getValor() == t2.getValor() && t2.getValor() == t3.getValor()) {
			// if(t1.equals(t2.equals(t3))){
			return true;
		}
		return false;
	}
}
