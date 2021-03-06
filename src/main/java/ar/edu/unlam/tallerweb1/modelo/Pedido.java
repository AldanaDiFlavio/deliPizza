package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Pedido")
	private Long id;
	private String solicitante;
	private String direccion;
	private Integer telefono;
	private Integer demora;
	private Integer precio;
	private String estado;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pedidos_pizzas", joinColumns = { @JoinColumn(name = "id_Pedido") }, inverseJoinColumns = {
			@JoinColumn(name = "id_Pizza") })
	private List<Pizza> listaPizzas = new LinkedList<Pizza>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Moto", nullable = true)
	private Moto moto;

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public Pedido() {
		super();

	}

	public Pedido(Long id, String solicitante, String direccion, Integer telefono, Integer cantidad, String estado,
			List<Pizza> listaPizzas) {
		super();
		this.id = id;
		this.solicitante = solicitante;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estado = estado;
		this.listaPizzas = listaPizzas;
	}

	public List<Pizza> getListaPizzas() {
		return listaPizzas;
	}

	public void setListaPizzas(List<Pizza> listaPizzas) {
		this.listaPizzas = listaPizzas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getDemora() {
		return demora;
	}

	public void setDemora(Integer demora) {
		this.demora = demora;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

}
