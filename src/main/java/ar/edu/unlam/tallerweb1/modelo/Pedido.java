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


@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Pedido")
	private Long id;	
	private String solicitante;
	private String direccion;
	private Integer telefono;
//	private Date fecha;
	private Integer cantidad;
	private Boolean estado;	
	
	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "pedidos_pizzas", joinColumns = { @JoinColumn(name = "id_Pedido") }, inverseJoinColumns = { @JoinColumn(name = "id_Pizza") })
	private List<Pizza> listaPizzas = new LinkedList<Pizza>();
			
	public Pedido() {
		super();

	}
	
	public Pedido(Long id, String solicitante, String direccion, Integer telefono, Integer cantidad, Boolean estado,
			List<Pizza> listaPizzas) {
		super();
		this.id = id;
		this.solicitante = solicitante;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cantidad = cantidad;
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
	
	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
//	public Date getFecha() {
//		return fecha;
//	}
//
//	public void setFecha(Date fecha) {
//		this.fecha = fecha;
//	}

	
}
