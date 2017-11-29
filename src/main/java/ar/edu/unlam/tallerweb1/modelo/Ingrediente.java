package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingrediente {
	private Long id;
	private String nombre;
	private Float precio;
	private List<Pizza> listaPizza = new LinkedList<Pizza>();

	public Ingrediente() {
		super();

	}

	public Ingrediente(Long id, String nombre, Float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Ingrediente")
	public Long getId() {
		return id;
	}

	@ManyToMany(mappedBy = "listaIngrediente")
	public List<Pizza> getListaPizza() {
		return listaPizza;
	}

	public void setListaPizza(List<Pizza> listaPizza) {
		this.listaPizza = listaPizza;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

}
