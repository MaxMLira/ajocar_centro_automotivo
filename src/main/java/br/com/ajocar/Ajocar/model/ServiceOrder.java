package br.com.ajocar.Ajocar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String serviceObservation;
	private Double piecesTotal;
	private Double serviceCost;
	private Double totalWork;
	public List<Product> products = new ArrayList<>();

	public ServiceOrder() {

	}

	public ServiceOrder(Integer id, String serviceObservation, Double piecesTotal, Double serviceCost,
			Double totalWork) {

		this.id = id;
		this.serviceObservation = serviceObservation;
		this.piecesTotal = piecesTotal;
		this.serviceCost = serviceCost;
		this.totalWork = totalWork;

	}

	public void setServiceObservation(String serviceObservation) {
		this.serviceObservation = serviceObservation;
	}

	public String getServiceObservation() {
		return this.serviceObservation;
	}

	public void setPiecesTotal(Double piecesTotal) {
		this.piecesTotal = piecesTotal;
	}

	public Double getPiecesTotal() {
		return this.piecesTotal;
	}

	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}

	public Double getServiceCost() {
		return this.serviceCost;
	}

	public void setTotalWork(Double totalWork) {
		this.totalWork = totalWork;
	}

	public Double getTotalWork() {
		return this.totalWork;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
