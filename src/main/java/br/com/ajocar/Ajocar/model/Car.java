package br.com.ajocar.Ajocar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Car implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private String vehicle;
	private String board;
	private String color;
	private String fuel;
	private Integer kmActually;
	
	public Car() {
		
	}
	
	public Car(Integer id, String vehicle, String board,String color,String fuel,Integer kmActually) {
		this.id = id;
		this.vehicle = vehicle;
		this.board = board;
		this.color = color;
		this.fuel = fuel;
		this.kmActually = kmActually;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getVehicle() {
		return this.vehicle;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getBoard() {
		return this.board;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return this.color;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getFuel() {
		return this.fuel;
	}

	public void setKmActually(Integer kmActually) {
		this.kmActually = kmActually;
	}

	public Integer getKmActually() {
		return this.kmActually;
	}

}
