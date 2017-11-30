package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Moto implements Comparable<Moto>{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Moto")
	private Long id;
	private String patente;
	private String conductor;
	private String estado;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	@OneToMany(mappedBy = "moto", fetch = FetchType.EAGER)
	private List<Pedido> listaPedido = new LinkedList<Pedido>();

	public void setlistaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}
	
	public List<Pedido> getlistaPedido() {
	        return listaPedido;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getConductor() {
		return conductor;
	}
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	@Override
	public int compareTo(Moto o) {
		  int resultado=0;
	        if (this.listaPedido.size()<o.listaPedido.size()) {   resultado = -1;      }
	        else if (this.listaPedido.size()>o.listaPedido.size()) {    resultado = 1;      }
	        else {
	            if (this.listaPedido.size()<o.listaPedido.size()) {  resultado = -1;    }
	            else if (this.listaPedido.size()>o.listaPedido.size()) {   resultado = 1;   }
	            else {   resultado = 0;   }
	        }
	        return resultado;
	    }
		
}
