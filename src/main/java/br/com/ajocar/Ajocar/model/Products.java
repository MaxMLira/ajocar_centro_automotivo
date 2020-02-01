package br.com.ajocar.Ajocar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Integer quantity;
	private Double preco;

	public Products() {

	}

	public Products(Integer id, String name, Integer quantity, Double preco) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.preco = preco;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getPreco() {
		return this.preco;
	}

}
