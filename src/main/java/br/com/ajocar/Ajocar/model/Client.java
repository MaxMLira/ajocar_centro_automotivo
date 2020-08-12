package br.com.ajocar.Ajocar.model;

import br.com.ajocar.Ajocar.dto.ClientDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @JsonBackReference
    @OneToMany(mappedBy = "client")
    private List<Car> cars = new ArrayList<>();
    @JsonBackReference
    @OneToMany(mappedBy = "client")
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

    public Integer getId() {
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

    public List<Car> update(Client clientUpdate) {
        this.name = clientUpdate.getName();
        this.tel = clientUpdate.getTel();
        this.address = this.address.update(clientUpdate.getAddress());
        return this.updateCar(clientUpdate.getCars());
    }

    private List<Car> updateCar(List<Car> cars) {
        List<Car> carNovos = new LinkedList<>();
        for (Car carrosBanco : this.cars) {
            for (Car car : cars) {
                if (carrosBanco.getId() == car.getId() ){
                    carrosBanco.setBoard(car.getBoard());
                    carrosBanco.setKmActually(car.getKmActually());
                    carrosBanco.setColor(car.getColor());
                    carrosBanco.setFuel(car.getFuel());
                    carrosBanco.setVehicle(car.getVehicle());
                }else if(car.getId() == null){
                    carNovos.add(car);
                }
            }
        }
        if (!carNovos.isEmpty()){
            Set<Car> set = new HashSet<>(carNovos);
            carNovos.clear();
            carNovos.addAll(set);
            this.cars.forEach(car-> carNovos.add(car));
            return carNovos;
        }

        return this.cars;
    }

}
