package br.com.ajocar.Ajocar.model;

import br.com.ajocar.Ajocar.dto.ClientDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@JsonBackReference
	@OneToOne(cascade= CascadeType.ALL)
	private Address address;
	@JsonBackReference
	@OneToMany(mappedBy="client")
	private List<Car> cars = new ArrayList<>();
	@JsonBackReference
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

	public static Client toModel(ClientDto clientDto) {
		Client client = new Client();
		client.setName(clientDto.getName());
		client.setTel(clientDto.getTel());
		Address address = new Address(null,clientDto.getAddress(),clientDto.getNumber(),
				clientDto.getDistric(),clientDto.getState());
		client.setAddress(address);
		int sizeVehicle = clientDto.getVehicle().length;
		int sizeKm = clientDto.getVehicle().length;
		int sizeColor = clientDto.getVehicle().length;
		int sizeBoard = clientDto.getVehicle().length;
		int sizeFuel = clientDto.getVehicle().length;
		List<Car> cars = new ArrayList<>();
		for(int i =0; i< sizeVehicle;i++){
			Car car = new Car();
			String vehicle = clientDto.getVehicle()[i];
			for (int j=0; j<sizeBoard;j++){
				String board = clientDto.getBoard()[j];
				for (int k=0; k<sizeKm;k++){
					String km = clientDto.getKm()[k];
					for (int l=0; l<sizeColor;l++){
						String color = clientDto.getColor()[l];
						for (int f=0; f<sizeFuel;f++){
							String fuel = clientDto.getFuel()[f];
							car.setVehicle(vehicle);
							car.setFuel(fuel);
							car.setColor(color);
							car.setKmActually(Integer.parseInt(km));
							car.setBoard(board);
							cars.add(car);
						}
					}
				}
			}
		}
		client.setCars(cars);

		return client;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public Integer getId(){
		return this.id;
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
