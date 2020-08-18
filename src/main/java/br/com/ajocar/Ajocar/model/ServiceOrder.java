package br.com.ajocar.Ajocar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

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
	private Boolean isDone;
	@JsonBackReference
	@OneToMany(mappedBy="serviceOrder")
	private List<Product> products = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	@OneToOne
	private Car car;
	@Column
	private LocalDate creatIn;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ServiceOrder() {
		this.isDone = Boolean.FALSE;
	}

	public ServiceOrder(Integer id, String serviceObservation,Double serviceCost) {

		this.id = id;
		this.serviceObservation = serviceObservation;
		this.serviceCost = serviceCost;
		this.isDone = Boolean.FALSE;
	
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

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}


	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getCreatIn() {
		return creatIn;
	}

	public void setCreatIn(LocalDate creatIn) {
		this.creatIn = creatIn;
	}

	public List<Product> update(ServiceOrder serviceUpd) {
		serviceUpd.calcPiecesTotal();
		serviceUpd.calcTotalWork();
		this.piecesTotal = serviceUpd.getPiecesTotal();
		this.serviceObservation = serviceUpd.getServiceObservation();
		this.piecesTotal =  serviceUpd.getPiecesTotal();
		this.serviceCost = serviceUpd.getServiceCost();
		this.totalWork = serviceUpd.getTotalWork();
		this.car = serviceUpd.getCar();
		this.setIsDone( serviceUpd.getIsDone());
		return this.updateProducts(serviceUpd.getProducts());
	}
	private List<Product> updateProducts(List<Product> products){
		List<Product> productsNews = new LinkedList<>();
		for (Product productDataBase : this.products) {
			for (Product product : products) {
				if (productDataBase.getId() == product.getId() ){
					productDataBase.setName(product.getName());
					productDataBase.setPrice(product.getPrice());
					productDataBase.setQuantity(product.getQuantity());
				}else if(product.getId() == null){
					productsNews.add(product);
				}
			}
		}
		if (!productsNews.isEmpty()){
			Set<Product> set = new HashSet<>(productsNews);
			productsNews.clear();
			productsNews.addAll(set);
			this.products.forEach(product-> productsNews.add(product));
			return productsNews;
		}

		return this.products;
	}
}
