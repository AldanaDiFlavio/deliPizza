package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Pizza {	
	private Long id;
	private String nombre;	
	private String imagen;
	private Integer precio;
	private Integer tcoccion;	
	private Integer cantidad;
	private Integer preciototal;
	private Boolean aniadida;
	private Boolean tamanio;
	private String aclaracion;	
	private List<Ingrediente> listaIngrediente = new LinkedList<Ingrediente>();
	
	public Pizza() {
		super();
	}
		
	public Pizza(Long id, String nombre, String imagen, Integer precio, Integer tcoccion, Boolean tamanio,
			String aclaracion, List<Ingrediente> listaIngrediente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.precio = precio;
		this.tcoccion = tcoccion;
		this.tamanio = tamanio;
		this.aclaracion = aclaracion;
		this.listaIngrediente = listaIngrediente;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Pizza")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pizza_ingrediente", joinColumns = { @JoinColumn(name = "id_Pizza") }, inverseJoinColumns = { @JoinColumn(name = "id_Ingrediente") })
	public List<Ingrediente> getListaIngrediente() {
		return listaIngrediente;
	}

	public void setListaIngrediente(List<Ingrediente> listaIngrediente) {
		this.listaIngrediente = listaIngrediente;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getTamanio() {
		return tamanio;
	}
	public void setTamanio(Boolean tamanio) {
		this.tamanio = tamanio;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getTcoccion() {
		return tcoccion;
	}
	public void setTcoccion(Integer tcoccion) {
		this.tcoccion = tcoccion;
	}

	public String getAclaracion() {
		return aclaracion;
	}

	public void setAclaracion(String aclaracion) {
		this.aclaracion = aclaracion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Boolean getAniadida() {
		return aniadida;
	}

	public void setAniadida(Boolean aniadida) {
		this.aniadida = aniadida;
	}

	public Integer getPreciototal() {
		return preciototal;
	}

	public void setPreciototal(Integer preciototal) {
		this.preciototal = preciototal;
	}
		
}
