package br.com.ajocar.Ajocar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ajocar.Ajocar.model.Car;
import br.com.ajocar.Ajocar.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	
	private CarRepository repository;

	public void saveCars(List<Car> cars) {
	
		
	}

}
