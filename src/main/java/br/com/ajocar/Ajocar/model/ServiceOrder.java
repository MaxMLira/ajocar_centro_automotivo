package br.com.ajocar.Ajocar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	@JsonBackReference
	@OneToMany(mappedBy="serviceOrder")
	private List<Product> products = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	@OneToOne
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ServiceOrder() {

	}

	public ServiceOrder(Integer id, String serviceObservation,Double serviceCost) {

		this.id = id;
		this.serviceObservation = serviceObservation;
		this.serviceCost = serviceCost;	
	
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
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
		this.calcPiecesTotal();
		this.calcTotalWork();
	}
	
	private void calcPiecesTotal() {
		this.piecesTotal = 0.0;
		if(!this.products.isEmpty()) {
			this.products.forEach(product->{
				this.piecesTotal += (product.getPrice() * product.getQuantity());
			});
		}else {
			this.piecesTotal = 0.0;
		}
	}
	private void calcTotalWork() {
		this.totalWork = piecesTotal + serviceCost;
	}


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
