package br.com.ajocar.Ajocar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ajocar.Ajocar.model.Car;
import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.repositories.CarRepository;

@Service
public class CarService {

	@Autowired

	private CarRepository repository;

	public String saveCars(List<Car> cars, Client client) {
		for (Car car : cars) {
			if (car.getVehicle().isEmpty()) {
				return "Você deve informar o nome";
			}
			if (car.getBoard().isEmpty()) {
				return "Você deve informar a placa";
			}
			if (car.getColor().isEmpty()) {
				return "Você deve infomar a cor do veículo!";
			}
			if (car.getFuel().isEmpty()) {
				return "Você deve informar o combustível do veículo";
			}
			if (car.getKmActually() == null) {
				return "Preencha a kilometragem do veículo";
			}
			car.setClient(client);
			
		}
		repository.saveAll(cars);
		return "Carros cadastrados com sucesso!";
	}

	public void deleteCar(Integer id) {
		repository.deleteById(id);
	}
}
