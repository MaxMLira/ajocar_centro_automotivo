package br.com.ajocar.Ajocar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajocar.Ajocar.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	
}
