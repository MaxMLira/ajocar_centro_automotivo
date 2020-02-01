package br.com.ajocar.Ajocar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Integer id;
	private String address;
	private String number;
	private String distric;
	private String state;

	public Address() {

	}

	public Address(Integer id, String address, String number, String distric, String state) {
		this.id = id;
		this.address = address;
		this.number = number;
		this.distric = distric;
		this.state = state;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return this.number;
	}

	public void setDistric(String distric) {
		this.distric = distric;
	}

	public String getDistric() {
		return this.distric;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

}
