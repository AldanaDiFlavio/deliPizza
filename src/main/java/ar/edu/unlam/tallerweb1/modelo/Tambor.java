package ar.edu.unlam.tallerweb1.modelo;

import java.util.Random;

public class Tambor {

	private Integer valor;

	public void girar() {
		Random r = new Random();
		valor = r.nextInt(7) + 1;
	}

	public Integer getValor() {
		return valor;
	}

}
