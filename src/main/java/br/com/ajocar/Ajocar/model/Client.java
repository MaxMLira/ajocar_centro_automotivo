package br.com.ajocar.Ajocar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String name;
	private String tel;
	
	@OneToOne(cascade= CascadeType.ALL)
	private Address address;
	
	@OneToMany(mappedBy="client")
	private List<Car> cars = new ArrayList<>();
	@OneToMany(mappedBy="client")
	private List<ServiceOrder> serviceOrders = new ArrayList<>();

	public Client() {

	}

	public Client(Integer id, String name, String tel, Address address) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel() {
		return this.tel;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return this.address;
	}

	public List<Car> getCars() {
		return this.cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<ServiceOrder> getServiceOrders() {
		return this.serviceOrders;
	}

	public void setServiceOrders(List<ServiceOrder> serviceOrders) {
		this.serviceOrders = serviceOrders;
	}
}
